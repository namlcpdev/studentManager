package com.apidemo.Service.impl;

import com.apidemo.Service.ICourseService;
import com.apidemo.dto.CourseDTO;
import com.apidemo.models.Course;
import com.apidemo.models.CourseStudent;
import com.apidemo.models.Student;
import com.apidemo.repositories.CourseRepository;
import com.apidemo.repositories.CourseStudentRepository;
import com.apidemo.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;
    @Transactional
    @Override
    public CourseDTO create(CourseDTO dto) throws Exception {
        Course course = new Course();
        Course courseCheckExits = courseRepository.findCourseByName(dto.getName());
        if (!ObjectUtils.isEmpty(courseCheckExits)) {
            throw new Exception("source name not duplication");
        }
        course.setName(dto.getName());
        List<Student> studentIds = dto.getStudentList();
        List<Student> studentList = new ArrayList<>();
        for (Student student: studentIds) {
            Student studentCheck = studentRepository.findStudentById(student.getId());
            if (ObjectUtils.isEmpty(studentCheck)) {
                throw new Exception("Student not found");
            }
            if (studentList.contains(studentCheck)) {
                throw new Exception("Student not duplication");
            }
            studentList.add(studentCheck);
        }
        course.setQuantity(studentList.stream().count());
        courseRepository.save(course);
        for (Student student: studentList) {
            CourseStudent courseStudent = new CourseStudent();
            courseStudent.setCourse(course);
            courseStudent.setStudent(student);
            courseStudentRepository.save(courseStudent);
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setQuantity(course.getQuantity());
        courseDTO.setStudentList(studentList);
        return courseDTO;
    }

    @Override
    public CourseDTO update(CourseDTO dto) throws Exception {
        Course course = courseRepository.findCourseById(dto.getId());
        if (ObjectUtils.isEmpty(course)) {
            throw new Exception("Course not exits");
        }
        List<CourseStudent> courseStudentList = courseStudentRepository.findCourseStudentByCourseId(dto.getId());
        List<Student> studentList = new ArrayList<>();
        HashMap<Long,Student> studentHashMap = new HashMap<>();
        for (CourseStudent courseStudent :courseStudentList) {
            studentHashMap.put(courseStudent.getStudent().getId(),courseStudent.getStudent());
        }
        for (Student student: dto.getStudentList()) {
            Student studentCheck = studentRepository.findStudentById(student.getId());
            if (ObjectUtils.isEmpty(studentCheck)) {
                throw new Exception("Student not found");
            }
            if (!ObjectUtils.isEmpty(studentHashMap.get(studentCheck.getId()))) {
                throw new Exception("Student not duplication");
            }
            studentList.add(studentCheck);
        }

        for (Student student: studentList) {
            CourseStudent courseStudent = new CourseStudent();
            courseStudent.setCourse(course);
            courseStudent.setStudent(student);
            courseStudentRepository.save(courseStudent);
        }
        course.setQuantity(course.getQuantity() + studentList.stream().count());
        courseRepository.save(course);
        for (CourseStudent courseStudent :courseStudentList) {
            studentList.add(courseStudent.getStudent());
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setQuantity(course.getQuantity());
        courseDTO.setStudentList(studentList);
        return courseDTO;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courseList = courseRepository.findAllCourse();
        return courseList;
    }

    @Override
    public CourseDTO getDetail(Long id) throws Exception {
        Course course = courseRepository.findCourseById(id);
        if (ObjectUtils.isEmpty(course)) {
            throw new Exception("Course not exits");
        }
        List<CourseStudent> courseStudentList = courseStudentRepository.findCourseStudentByCourseId(id);
        List<Student> studentList = new ArrayList<>();
        for (CourseStudent courseStudent: courseStudentList) {
            studentList.add(courseStudent.getStudent());
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setQuantity(course.getQuantity());
        courseDTO.setStudentList(studentList);
        return courseDTO;
    }
}
