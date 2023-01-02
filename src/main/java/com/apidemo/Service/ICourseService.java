package com.apidemo.Service;

import com.apidemo.dto.CourseDTO;
import com.apidemo.models.Course;

import java.util.List;

public interface ICourseService {
    public abstract CourseDTO create(CourseDTO dto) throws Exception;
    public abstract CourseDTO update(CourseDTO dto) throws Exception;

    public abstract List<Course> getAll();
    public abstract CourseDTO getDetail(Long id ) throws Exception;
}
