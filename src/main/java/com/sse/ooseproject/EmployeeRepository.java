package com.sse.ooseproject;

import com.sse.ooseproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT emp FROM Employee emp WHERE emp.staffNr = :staffNr")
    List<Employee> findEmployeeByStaffNr(@Param("staffNr") long staffNr);

}
