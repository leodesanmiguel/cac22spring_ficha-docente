package com.leodesanmiguel.fichadocente.controllers;

import com.leodesanmiguel.fichadocente.entity.Staff;
import com.leodesanmiguel.fichadocente.services.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class StaffController {

    @Autowired
    private IStaffService serviceDocente;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Staff saveUnStaff(@RequestBody Staff docente){
        return serviceDocente.saveStaff(docente);
    }

    @GetMapping("/listado")
    public List<Staff> listOfStaff(){
        return serviceDocente.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getUnStaffId(@PathVariable("id") Long staffId,
                                               @RequestBody Staff docente){
        // Busca el docente por el id. si lo encuentra Pone OK. Sino pone No encorntrado
        return serviceDocente.getStaffById(staffId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Staff> getUnStaffDni(@PathVariable("dni") Integer staffDni,
                                            @RequestBody Staff docente){
        // Busca el docente por el id. si lo encuentra Pone OK. Sino pone No encorntrado
        return serviceDocente.getStaffByDni(staffDni)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<Staff> getUnStaffCuit(@PathVariable("cuit") Long staffCuit,
                                               @RequestBody Staff docente){
        // Busca el docente por el id. si lo encuentra Pone OK. Sino pone No encorntrado
        return serviceDocente.getStaffByCuit(staffCuit)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateUnStaff(@PathVariable("id") Long staffId,
                                               @RequestBody Staff docente){
        // solo puede actualizar nombre, apellido fecha de nacimiento y sexo.
        return serviceDocente.getStaffById(staffId)
                .map(docenteGuardado -> {
                    docenteGuardado.setName(docente.getName());
                    docenteGuardado.setLastName(docente.getLastName());
                    docenteGuardado.setBirthDate(docente.getBirthDate());
                    docenteGuardado.setSex(docente.getSex());
                    Staff docenteActualizado = serviceDocente.updateStaff(docenteGuardado);
                    return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);
                }).orElseGet(()-> ResponseEntity.notFound().build());
   }

   @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUnStaff(@PathVariable("id") Long staffId){
        serviceDocente.deleteStaff(staffId);
        return new ResponseEntity<String>("El docente fue eliminado exitosamente", HttpStatus.OK);
   }

}
