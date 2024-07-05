package com.sse.ooseproject;

import com.sse.ooseproject.models.Enrollment;
import com.sse.ooseproject.models.EnrollmentId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findByStudentId(@Param("studentId") long studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.semester = :semester")
    List<Enrollment> findByStudentIdAndSemester(@Param("studentId") long studentId, @Param("semester") String semester);

    @Transactional
    @Modifying
    @Query("DELETE FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId AND e.semester = :semester")
    void deleteEnrollment(@Param("studentId") long studentId, @Param("courseId") long courseId, @Param("semester") String semester);
}
