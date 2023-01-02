package com.apidemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_cource_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "source_id")
    Course course;

    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;
}
