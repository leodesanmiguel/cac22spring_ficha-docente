package com.leodesanmiguel.fichadocente.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "user_key",  length = 20)
    private String userKey;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "lastname", nullable = false, length = 40)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "level", nullable = false)
    private Integer userLevel;

    @PastOrPresent
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;



}
