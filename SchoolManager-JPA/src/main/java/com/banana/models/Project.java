package com.banana.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO) // Caso de TABLE_PER_CLASS
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "proyectos")
    /*@JoinTable(
            name = "proyectos_estudiantes",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "estudiante_id")}
    )*/
    private List<Student> estudiantes = new ArrayList<>();
}
