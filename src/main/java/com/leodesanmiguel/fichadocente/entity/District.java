package com.leodesanmiguel.fichadocente.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_district")
    private Long id;

    @Column(name = "name_district", nullable = false, length = 50)
    private String nameDistrict;

    @Column(name = "province", nullable = false, length = 25)
    private String province;

    @OneToMany(mappedBy = "district", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Locality> localities;






}
