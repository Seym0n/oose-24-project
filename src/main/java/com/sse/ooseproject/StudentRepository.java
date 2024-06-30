package com.sse.ooseproject;

import com.sse.ooseproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT st FROM Student st WHERE st.matNr = :matNr")
    List<Student> findStudentByMatNr(@Param("matNr") long matNr);

}
