package com.leodesanmiguel.fichadocente.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder

@Entity
@Table(name = "staff", uniqueConstraints = {@UniqueConstraint(columnNames = {"dni", "cuit"})})
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "lastname", nullable = false, length = 40)
    private String lastName;

    @Column(name = "sex")
    private Character sex;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "cuit", nullable = false)
    private Long cuit;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "staff", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Phone> phones;

    @OneToMany(mappedBy = "staff", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Address> addresses;

    @OneToMany(mappedBy = "staff", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Email> emails;

    @OneToMany(mappedBy = "staff", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Degree> degrees;


}
