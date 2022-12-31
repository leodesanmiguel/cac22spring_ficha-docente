package com.leodesanmiguel.fichadocente.controllers;

import com.leodesanmiguel.fichadocente.dto.UserDto;
import com.leodesanmiguel.fichadocente.entity.User;
import com.leodesanmiguel.fichadocente.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@Valid  @PathVariable Long id) {
        if (!userService.existeUsuario(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontró el usuario " + id);
        }
        UserDto usr = userService.obtenerUnUsuario(id);
        User user = new User();

        user.setId(id);
        user.setUserKey(usr.getUserKey());
        user.setUserName(usr.getUserName());
        user.setName(usr.getName());
        user.setLastName(usr.getLastName());
        user.setEmail(usr.getEmail());
        user.setUserLevel(usr.getUserLevel());
        user.setStartDate(usr.getStartDate());
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    // Muesras del uso de @PathVariable
    @GetMapping(value = "/cargar/{user}/{nombre}/{apellido}/{correo}")
    public String getUser(@Valid @PathVariable String user,
                          @Valid @PathVariable String nombre,
                          @Valid @PathVariable String apellido,
                          @Valid @PathVariable String correo) {
        return "Podria crear usuario :" + user + " Nombre completo: " + nombre + ", " + apellido + " Email: " + correo;

    }

    // Muesras del uso de @RequestParam
    @GetMapping(value = "/cargar")
    public String getUser(@Valid @RequestParam String user,
                          @Valid @RequestParam String nombre,
                          @Valid @RequestParam String apellido,
                          @Valid @RequestParam int nivel,
                          @Valid @RequestParam String correo) {
        return "Podria crear usuario :" + user + " de Nivel: " + nivel + " -  Nombre completo: " + nombre + ", " + apellido + " Email: " + correo;

    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editUser(@Valid @PathVariable Long id,
                                      @Valid @RequestParam("email") String email,
                                      @Valid @RequestParam("name") String name,
                                      @Valid @RequestParam("lastName") String lastName
                                      ){

        UserDto usr= new UserDto();
        usr.setId(id);
        usr.setEmail(email);
        usr.setName(name);
        usr.setLastName(lastName);
        return new ResponseEntity<>(userService.editarUser(usr), HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/crear")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/listado")
    public ResponseEntity<?> getListUser() {
        return new ResponseEntity<>(userService.obtenerUsuarios(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/listadito")
    public List<UserDto> listadoUsuarios() {
        return userService.obtenerUsuarios();
    }

    @DeleteMapping(value = "/eliminarid/{id}")
    public ResponseEntity<?> deleteUserId(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(userService.borrarUsuario(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminarname/{name}")
    public ResponseEntity<?> deleteUserId(@Valid @PathVariable String name) {
        return new ResponseEntity<>(userService.borrarUsuario(name), HttpStatus.OK);
    }

    @GetMapping("/Mostrame/{id}")
    public ResponseEntity<?> obtenerUser1(@Valid @PathVariable Long id) {

        return new ResponseEntity<>(userService.obtenerUnUsuario(id), HttpStatus.ACCEPTED);
    }
    @GetMapping("/Obtener/{name}")
    public ResponseEntity<?> obtenerUser1(@Valid  @PathVariable String name) {
        return new ResponseEntity<>(userService.obtenerUnUsuario(name), HttpStatus.ACCEPTED);
    }



}
