package com.leodesanmiguel.fichadocente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long id;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Column(name = "tel_alternative", nullable = false, length = 20)
    private String telAlternative;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_staff_fk", referencedColumnName = "id_staff",  nullable = false)
    private Staff staff;






}
