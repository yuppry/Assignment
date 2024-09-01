package com.assignment.salary.repository;

import com.assignment.salary.model.SalarySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends JpaRepository<SalarySurvey, Long> {

    @Query("SELECT s FROM SalarySurvey s WHERE (:gender IS NULL OR s.gender = :gender) AND (:jobTitle IS NULL OR s.jobTitle = :jobTitle) " +
            "AND (:salaryGte IS NULL OR s.salary >= :salaryGte) AND (:salaryLte IS NULL OR s.salary <= :salaryLte) " +
            "AND (:salaryGt IS NULL OR s.salary > :salaryGt) AND (:salaryLt IS NULL OR s.salary < :salaryLt) " +
            "AND (:salaryEq IS NULL OR s.salary = :salaryEq)")
    List<SalarySurvey> findSalarySurveyByGenderAndJobTitleAndSalary(@Param("gender")String gender, @Param("jobTitle") String jobTitle,
                                                                    @Param("salaryGte") Long salaryGte, @Param("salaryLte") Long salaryLte,
                                                                    @Param("salaryGt") Long salaryGt, @Param("salaryLt") Long salaryLt,
                                                                    @Param("salaryEq") Long salaryEq);
}
