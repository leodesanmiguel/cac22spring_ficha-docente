package com.leodesanmiguel.fichadocente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_degree")
    private Long id;

    @Column(name = "name_degree", nullable = false, length = 70)
    private String nameDecree;

    @Column(name = "institution",  length = 70)
    private String institution;

    @Column(name = "registry_n",  length = 15)
    private String registryNro;

    @Column(name = "type_degree")
    private Character typeDegree;

    @Column(name = "senior_year")
    private Integer seniorYear;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_staff_fk", nullable = false)
    private Staff staff;






}
