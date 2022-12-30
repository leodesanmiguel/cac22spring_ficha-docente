package com.leodesanmiguel.fichadocente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /*private final String regularPassword = "^(?=.*[0-9])"
                + "(?=.*[az])(?=.*[AZ])"
                + "(?=.*[@#$%^&-+=])"
                + "(?=\\S+$).{8,20}$";
      private final String regularName = "^(?=.*[az])(?=.*[AZ])"
                + "(?=\\S+$).{3,20}$";
    */
    private Long id;

    @NotBlank
    @NotEmpty
    @Size(max = 20, message = " DTO: El username debe tener como máximo 20 caracteres")
    //@Pattern(regexp = regularName, message = " el pattern dice que esta mal")
    private String userName;

    @NotEmpty
    @Size(min = 8, max = 20, message = " DTO: La clave o password debe tener mínimo 8 máximo 20 caracteres")
    //@Pattern(regexp = regularPassword, message = " la expresiuon regular dice que estqa mal")
    private String userKey;

    @NotBlank
    @NotEmpty
    @Size(max = 40, message = "DTO: El Nombre del usuario debe tener como máximo 20 caracteres")
    private String name;

    @NotBlank
    @NotEmpty
    @Size(max = 40, message = "DTO: El Apellido del usuario debe tener como máximo 20 caracteres")
    private String lastName;

    @NotBlank
    @NotEmpty
    @Size(max = 50, message = "DTO: El correo electrónico  debe como máximo 50 caracteres")
    @Email(message = "DTO: No tiene formato de correo electrónico. ")
    private String email;

    @Min(value = 1, message = "DTO: El valor mínimo debe ser 1.")
    private Integer userLevel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate startDate;


}
