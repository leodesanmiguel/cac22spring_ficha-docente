package com.leodesanmiguel.fichadocente.services;

import com.leodesanmiguel.fichadocente.entity.Staff;
import java.util.List;
import java.util.Optional;

public interface IStaffService {
    Staff saveStaff(Staff docente);
    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(Long id);
    Optional<Staff> getStaffByDni(Integer dni);
    Optional<Staff> getStaffByCuit(Long cuit);
    Staff updateStaff(Staff docenteActualizado);
    void deleteStaff(Long id);

}
