package com.apidemo.repositories;

import com.apidemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public boolean existsByCode(String code);
    @Query(value = "select * from tbl_student where id = ?1 and delete_at is null ", nativeQuery = true)
    Student findStudentById(Long id);
}
