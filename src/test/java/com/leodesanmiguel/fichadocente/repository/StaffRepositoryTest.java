package com.leodesanmiguel.fichadocente.repository;

import com.leodesanmiguel.fichadocente.entity.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@Sql({"/schema_test.sql", "data_test.sql"})
public class StaffRepositoryTest {
    // Test para probar la persistencia.

    @Autowired
    private IStaffRepository repoDocente;

    private Staff profesor;

    @BeforeEach
    void setup() {
        profesor = Staff.builder()
                .lastName("Rodriguez")
                .name("Sebastan")
                .dni(32165487)

                .sex('F')
                .build();
    }

    @DisplayName("Test para Guardar un Docente")
    @Test
    void testGuardarDocente() {
        // Condicion --> given
        Staff docente = Staff.builder()
                .dni(2356987)

                .sex('M')
                .lastName("Lopez")
                .name("José")
                .build();
//                .birthDate(LocalDate.parse("1986-10-25"))

        // comportamiento que se va a probar --> when
        Staff docenteGuardado = repoDocente.save(docente);

        // Verficar la Salida --> then
        assertThat(docenteGuardado).isNotNull();
        assertThat(docenteGuardado.getId()).isGreaterThan(0);
    }

    @DisplayName("Test par alistar a los profesores")
    @Test
    void testListarProfesores() {
        // given
        Staff profe = Staff.builder()
                .dni(12345678)

                .sex('M')
                .lastName("Rizutti")
                .name("Adalfredo")
                .build();
        repoDocente.save(profe);
        repoDocente.save(profesor);

        //when
        List<Staff> listaDeProfes = repoDocente.findAll();

        //then
        assertThat(listaDeProfes).isNotNull();
        assertThat(listaDeProfes.size()).isEqualTo(3);

    }


}
