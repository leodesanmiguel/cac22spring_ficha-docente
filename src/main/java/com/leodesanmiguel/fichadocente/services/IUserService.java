package com.leodesanmiguel.fichadocente.services;

import com.leodesanmiguel.fichadocente.dto.RespUserDto;
import com.leodesanmiguel.fichadocente.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> obtenerUsuarios();

    UserDto listarUnUsuario(String name);

    UserDto obtenerUnUsuario(Long id);

    UserDto obtenerUnUsuario(String name);

    boolean existeUsuario(Long id);
    boolean existeUsuario(String name);

    //    boolean existeUsuario(String userName, int tipo);
    //        // tipo: 1=name, 2=lastName, 3=userName

    RespUserDto borrarUsuario(Long id);
    RespUserDto borrarUsuario(String name);

    RespUserDto createUser(UserDto userDto);

    RespUserDto editarUser(UserDto usrDto);
}
