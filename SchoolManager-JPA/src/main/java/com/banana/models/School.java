package com.banana.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "escuela")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "escuela")
//    @JoinColumn(name = "school_id")
    private List<Student> estudiantes;

    /*public School(Long id, String name) {
        this.id = id;
        this.name = name;
    }*/

    public void addStudent(Student estudiante) {
        estudiantes.add(estudiante);
    }

    public void removeStudent(Student estudiante) {
        estudiantes.remove(estudiante);
    }

}
