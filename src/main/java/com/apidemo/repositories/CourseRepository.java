package com.apidemo.repositories;

import com.apidemo.models.Course;
import com.apidemo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select * from tbl_cource where id = ?1 and delete_at is null ", nativeQuery = true)
    Course findCourseById(Long id);

    @Query(value = "select * from tbl_cource where lower(cource_name) like ?1 and delete_at is null ", nativeQuery = true)
    Course findCourseByName(String name);

    @Query(value = "select * from tbl_cource where delete_at is null ", nativeQuery = true)
    List<Course> findAllCourse();
}
