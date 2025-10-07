package com.crm.educational_crm_backend.service.reports;

import com.crm.educational_crm_backend.dto.reports.*;
import com.crm.educational_crm_backend.entity.attendance.Attendance;
import com.crm.educational_crm_backend.entity.fee.Fee;
import com.crm.educational_crm_backend.entity.results.Result;
import com.crm.educational_crm_backend.repository.attendance.AttendanceRepository;
import com.crm.educational_crm_backend.repository.enrollment.EnrollmentRepository;
import com.crm.educational_crm_backend.repository.exam.ExamRepository;
import com.crm.educational_crm_backend.repository.fee.FeeRepository;
import com.crm.educational_crm_backend.repository.results.ResultRepository;
import com.crm.educational_crm_backend.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final ResultRepository resultRepository;
    private final FeeRepository feeRepository;
    private final ExamRepository examRepository;
    private final com.crm.educational_crm_backend.repository.course.CourseRepository courseRepository;
    private final com.crm.educational_crm_backend.repository.subject.SubjectRepository subjectRepository;

    @Override
    public AdmissionReportResponse getAdmissionsReport() {
        int total = (int) studentRepository.count();

        Map<String, Long> byCourse = enrollmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCourse().getName(),
                        Collectors.counting()
                ));

        Map<String, Long> byMonth = studentRepository.findAll().stream()
                .filter(s -> s.getAdmissionDate() != null)
                .collect(Collectors.groupingBy(
                        s -> s.getAdmissionDate().getMonth().toString(),
                        Collectors.counting()
                ));

        return new AdmissionReportResponse(total, byCourse, byMonth);
    }

    @Override
    public AttendanceReportResponse getAttendanceReport() {
        List<Attendance> allAttendance = attendanceRepository.findAll();
        if (allAttendance.isEmpty())
            return new AttendanceReportResponse(0.0, Map.of(), Map.of());

        double avgAttendance = allAttendance.stream()
                .collect(Collectors.groupingBy(Attendance::getStudentId))
                .values()
                .stream()
                .mapToDouble(list -> {
                    long total = list.size();
                    long present = list.stream()
                            .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                            .count();
                    return total == 0 ? 0 : (present * 100.0 / total);
                })
                .average()
                .orElse(0.0);

        Map<String, Double> bySubject = allAttendance.stream()
                .collect(Collectors.groupingBy(
                        a -> subjectRepository.findById(a.getSubjectId())
                                .map(sub -> sub.getName())
                                .orElse("Unknown"),
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            long total = list.size();
                            long present = list.stream()
                                    .filter(att -> "PRESENT".equalsIgnoreCase(att.getStatus()))
                                    .count();
                            return total == 0 ? 0 : (present * 100.0 / total);
                        })
                ));

        Map<String, Double> byCourse = new HashMap<>();
        Map<UUID, UUID> studentCourseMap = enrollmentRepository.findAll().stream()
                .collect(Collectors.toMap(
                        e -> e.getStudent().getId(),
                        e -> e.getCourse().getId(),
                        (a, b) -> a
                ));

        for (Map.Entry<UUID, List<Attendance>> entry :
                allAttendance.stream().collect(Collectors.groupingBy(Attendance::getStudentId)).entrySet()) {

            UUID studentId = entry.getKey();
            UUID courseId = studentCourseMap.get(studentId);
            if (courseId == null) continue;

            List<Attendance> list = entry.getValue();
            long total = list.size();
            long present = list.stream()
                    .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus()))
                    .count();
            double percent = total == 0 ? 0 : (present * 100.0 / total);

            String courseName = courseRepository.findById(courseId)
                    .map(c -> c.getName())
                    .orElse("Unknown");

            byCourse.merge(courseName, percent, Double::sum);
        }

        Map<String, Long> courseStudentCount = studentCourseMap.values().stream()
                .map(courseId -> courseRepository.findById(courseId).map(c -> c.getName()).orElse("Unknown"))
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

        byCourse.replaceAll((course, totalPercent) -> totalPercent / courseStudentCount.getOrDefault(course, 1L));

        return new AttendanceReportResponse(avgAttendance, byCourse, bySubject);
    }

    @Override
    public ExamReportResponse getExamReport() {
        List<Result> results = resultRepository.findAll();
        if (results.isEmpty())
            return new ExamReportResponse(0, 0, Map.of(), Map.of());

        double avgScore = results.stream()
                .mapToDouble(Result::getMarksObtained)
                .average()
                .orElse(0.0);

        long passed = results.stream().filter(r -> r.getMarksObtained() >= 40).count();
        double passPercentage = (results.size() == 0) ? 0 : (passed * 100.0 / results.size());

        Map<String, Double> byCourse = results.stream()
                .collect(Collectors.groupingBy(
                        r -> examRepository.findById(r.getExamId())
                                .map(exam -> courseRepository.findById(exam.getCourseId())
                                        .map(c -> c.getName()).orElse("Unknown"))
                                .orElse("Unknown"),
                        Collectors.averagingDouble(Result::getMarksObtained)
                ));

        Map<String, Double> bySubject = results.stream()
                .collect(Collectors.groupingBy(
                        r -> examRepository.findById(r.getExamId())
                                .map(exam -> subjectRepository.findById(exam.getSubjectId())
                                        .map(s -> s.getName()).orElse("Unknown"))
                                .orElse("Unknown"),
                        Collectors.averagingDouble(Result::getMarksObtained)
                ));

        return new ExamReportResponse(avgScore, passPercentage, byCourse, bySubject);
    }

    @Override
    public FeeReportResponse getFeeReport() {
        List<Fee> allFees = feeRepository.findAll();
        if (allFees.isEmpty())
            return new FeeReportResponse(0, 0, Map.of());

        double totalCollected = allFees.stream()
                .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                .mapToDouble(Fee::getAmount)
                .sum();

        double totalPending = allFees.stream()
                .filter(f -> "PENDING".equalsIgnoreCase(f.getStatus()))
                .mapToDouble(Fee::getAmount)
                .sum();

        Map<String, Double> byCourse = allFees.stream()
                .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                .collect(Collectors.groupingBy(
                        f -> courseRepository.findById(f.getCourseId())
                                .map(c -> c.getName())
                                .orElse("Unknown"),
                        Collectors.summingDouble(Fee::getAmount)
                ));

        return new FeeReportResponse(totalCollected, totalPending, byCourse);
    }

    @Override
    public CustomReportResponse getCustomReport(String reportType, LocalDate fromDate, LocalDate toDate) {
        Map<String, Object> data = new HashMap<>();

        switch (reportType.toLowerCase()) {
            case "fee" -> {
                List<Fee> feeList = feeRepository.findByPaidDateBetween(fromDate, toDate);
                double totalCollected = feeList.stream()
                        .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                        .mapToDouble(Fee::getAmount)
                        .sum();
                double totalPending = feeList.stream()
                        .filter(f -> "PENDING".equalsIgnoreCase(f.getStatus()))
                        .mapToDouble(Fee::getAmount)
                        .sum();

                Map<String, Double> byCourse = feeList.stream()
                        .filter(f -> "PAID".equalsIgnoreCase(f.getStatus()))
                        .collect(Collectors.groupingBy(
                                f -> courseRepository.findById(f.getCourseId())
                                        .map(c -> c.getName())
                                        .orElse("Unknown"),
                                Collectors.summingDouble(Fee::getAmount)
                        ));

                data.put("totalCollected", totalCollected);
                data.put("totalPending", totalPending);
                data.put("transactionCount", feeList.size());
                data.put("byCourse", byCourse);
            }
            case "attendance" -> {
                List<Attendance> attList = attendanceRepository.findByAttendanceDateBetween(fromDate, toDate);

                Map<String, Double> bySubject = attList.stream()
                        .collect(Collectors.groupingBy(
                                a -> subjectRepository.findById(a.getSubjectId())
                                        .map(s -> s.getName())
                                        .orElse("Unknown"),
                                Collectors.collectingAndThen(Collectors.toList(), list -> {
                                    long total = list.size();
                                    long present = list.stream()
                                            .filter(att -> "PRESENT".equalsIgnoreCase(att.getStatus()))
                                            .count();
                                    return total == 0 ? 0 : (present * 100.0 / total);
                                })
                        ));

                data.put("totalRecords", attList.size());
                data.put("attendancePercentageBySubject", bySubject);
            }
            case "exam" -> {
                List<Result> resultList = resultRepository.findByExamDateBetween(fromDate, toDate);

                Map<String, Double> byCourse = resultList.stream()
                        .collect(Collectors.groupingBy(
                                r -> examRepository.findById(r.getExamId())
                                        .map(exam -> courseRepository.findById(exam.getCourseId())
                                                .map(c -> c.getName()).orElse("Unknown"))
                                        .orElse("Unknown"),
                                Collectors.averagingDouble(Result::getMarksObtained)
                        ));

                Map<String, Double> bySubject = resultList.stream()
                        .collect(Collectors.groupingBy(
                                r -> examRepository.findById(r.getExamId())
                                        .map(exam -> subjectRepository.findById(exam.getSubjectId())
                                                .map(s -> s.getName()).orElse("Unknown"))
                                        .orElse("Unknown"),
                                Collectors.averagingDouble(Result::getMarksObtained)
                        ));

                data.put("averageMarksByCourse", byCourse);
                data.put("averageMarksBySubject", bySubject);
            }
            default -> data.put("error", "Invalid report type: " + reportType);
        }

        return new CustomReportResponse(reportType, fromDate, toDate, data);
    }
}
