package com.subhankar.repository;

import com.subhankar.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT e from Employee e WHERE e.emailId LIKE %?1%")
    Page<Employee> findByEmailId(String userName, Pageable pageable);

    @Query("SELECT e from Employee e WHERE e.emailId LIKE %?1%" +
            "OR e.contactNumber LIKE %?1%")
    Employee findByEmailIdOrContactNumber(String userName);

    @Query("SELECT e from Employee e WHERE e.name LIKE %?1%")
    Page<Employee> findByName(String name, Pageable pageable);

    @Query("SELECT e from Employee e WHERE " +
            "e.employeeCode LIKE %?1%" +
            "OR e.id LIKE %?1%" +
            "OR e.name LIKE %?1%" +
            "OR e.emailId LIKE %?1%" +
            "OR e.contactNumber LIKE %?1%")
    Page<Employee> getDataByAnyInput(String keyword, Pageable pageable);

    Employee findByEmployeeCode(String empCode);

    Page<Employee> findByEmployeeCode(String employeeCode, Pageable pageable);
}
