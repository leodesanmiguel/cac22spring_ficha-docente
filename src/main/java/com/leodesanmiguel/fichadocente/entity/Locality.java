package com.leodesanmiguel.fichadocente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "locality")
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locality")
    private Long id;

    @Column(name = "name_locality", length = 50)
    private String nameLocation;

    @Column(name = "post_zip", nullable = false)
    private Integer postZip;

    @OneToMany(mappedBy = "locality", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Address> addresses;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_district_fk", referencedColumnName = "id_district", nullable = false)
    private District district;






}
