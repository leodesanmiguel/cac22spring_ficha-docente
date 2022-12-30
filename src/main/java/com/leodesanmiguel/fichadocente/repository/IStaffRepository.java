package com.leodesanmiguel.fichadocente.repository;

import com.leodesanmiguel.fichadocente.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByLastName(String lastName);

    Optional<Staff> findByDni(Integer dni);
    Optional<Staff> findByCuit(Long cuit);
}
