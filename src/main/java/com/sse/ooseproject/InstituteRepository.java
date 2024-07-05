package com.sse.ooseproject;

import com.sse.ooseproject.models.Course;
import com.sse.ooseproject.models.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

    @Query("SELECT providesStudySubject FROM Institute inst GROUP BY providesStudySubject")
    List<String> listStudySubjects();

    @Query("SELECT cr FROM Course cr JOIN cr.chair ch JOIN ch.institute inst WHERE inst.providesStudySubject = :studySubject")
    List<Course> getCoursesByStudySubject(@Param("studySubject") String studySubject);

}
