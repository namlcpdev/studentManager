package com.apidemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_cource")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cource_name")
    private String name;

    @Column(name = "quantity_cource")
    private Long quantity;
}
