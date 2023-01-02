package com.apidemo.repositories;

import com.apidemo.models.Course;
import com.apidemo.models.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    @Query(value = "select * from tbl_cource_student where source_id = ?1 and delete_at is null ", nativeQuery = true)
    List<CourseStudent> findCourseStudentByCourseId(Long courseId);


}
