package com.leodesanmiguel.fichadocente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Column(name = "street_nro", nullable = false, length = 50)
    private String streetNro;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_location",referencedColumnName = "id_locality", nullable = false)
    private Locality locality;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_staff_fk",referencedColumnName = "id_staff", nullable = false)
    private Staff staff;






}
