package com.leodesanmiguel.fichadocente.services;

import com.leodesanmiguel.fichadocente.entity.Staff;
import com.leodesanmiguel.fichadocente.exceptions.ResourceNotFoundExceltion;
import com.leodesanmiguel.fichadocente.repository.IStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Staffservice implements IStaffService{
    @Autowired
    private IStaffRepository repoDocente;
    @Override
    public Staff saveStaff(Staff docente) {

        // No puede haber dos docentes con el mismo cuit
        Optional<Staff> docenteGuardado = repoDocente.findByCuit(docente.getCuit());
        if (docenteGuardado.isPresent()){
            throw new ResourceNotFoundExceltion("Este docente Ya Existe en ");
        }
        return repoDocente.save(docente);
    }

    @Override
    public List<Staff> getAllStaff() {
        return repoDocente.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(Long id) {
        return repoDocente.findById(id);
    }

    @Override
    public Optional<Staff> getStaffByDni(Integer dni) {
        return repoDocente.findByDni(dni);
    }

    @Override
    public Optional<Staff> getStaffByCuit(Long cuit) {
        return repoDocente.findByCuit(cuit);
    }

    @Override
    public Staff updateStaff(Staff docenteActualizado) {
        return repoDocente.save(docenteActualizado);
    }

    @Override
    public void deleteStaff(Long id) {
        repoDocente.deleteById(id);
    }
}
