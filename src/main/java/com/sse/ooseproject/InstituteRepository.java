package com.sse.ooseproject;

import com.sse.ooseproject.models.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {

    @Query("SELECT providesStudySubject FROM Institute inst GROUP BY providesStudySubject")
    List<String> listStudySubjects();
}
