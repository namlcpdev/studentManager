package com.apidemo.repositories;

import com.apidemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public boolean existsByCode(String code);
}
