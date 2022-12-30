package com.leodesanmiguel.fichadocente.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leodesanmiguel.fichadocente.dto.RespUserDto;
import com.leodesanmiguel.fichadocente.dto.UserDto;
import com.leodesanmiguel.fichadocente.entity.User;
import com.leodesanmiguel.fichadocente.exceptions.UserNotFoundException;
import com.leodesanmiguel.fichadocente.repository.IUserRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    // Inyección de dependencia a través de constructor.
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // listado de todos los usuarios
    @Override
    public List<UserDto> obtenerUsuarios() {
        // Define otra lista pero de DTOs para guardar lo recuperado.
        // Es para que vaya de USER --> DTO
        // Es lo que devuelve la función.
        List<UserDto> usuariosDtos = new ArrayList<>();

        // Prepara un mapeador para la clase.
        ModelMapper mapper = new ModelMapper();

        // Recupera todos los usuarios del Repository.
        // Utiliza la clase porque va desde la DB --> CLase.
        List<User> usuarios = userRepository.findAll();


        // Mapea y Recupera los datos del USER hacia el DTO
        // Para cada USER, lo mapea y lo reasigna a su correspondiente DTO
        // usuarios.stream().forEach(usr->usuariosDtos.add(mapper.map(usr,UserDto.class)));
        for (User usr : usuarios) {
            usuariosDtos.add(mapper.map(usr, UserDto.class));
        }

        return usuariosDtos;
//        return usuarios.stream().map(elU -> new UserDto(elU.getId(), elU.getUserName(), elU.getUserKey(), elU.getName()
//                , elU.getLastName(), elU.getEmail(), elU.getUserLevel(),
//                elU.getStartDate())).collect(Collectors.toList());

    }

    @Override
    public UserDto listarUnUsuario(String name) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<User> user = userRepository.findUserByName(name);
        if(user.isPresent()){
            User u = user.get();
            UserDto userDto = mapper.convertValue(u,UserDto.class);
            return userDto;
        }
        throw new UserNotFoundException("El usuario no se encontró");
    }

    // Obtener un USER por id
    @Override
    public UserDto obtenerUnUsuario(Long id) {
        // Verificar si existe el usuario
        if (!existeUsuario(id)) return null;

        // Definir para el modelo la clase USER
        ModelMapper mapper = new ModelMapper();

        // Recuperar un USER a través del repository
        Optional<User> unUser = userRepository.findById(id);

        // Mapear lo recuperado a la clase DTO
        // Devuelve la funcion
        UserDto unUsuarioDto = mapper.map(unUser, UserDto.class);

        return unUsuarioDto;

// Anterior:
//        User usr = userRepository.findById(id).orElse(null);
//        if (usr==null){
//           return null;
//        }
    }

    // Obtener un USER por Name
    public UserDto obtenerUnUsuario(String name) {
        // Verificar si existe el usuario
        if (!existeUsuario(name)) return null;

        // Definir para el modelo la clase USER
        ModelMapper mapper = new ModelMapper();

        // Recuperar un USER a través del repository
        User unUser = userRepository.findByName(name);

        // Mapear lo recuperado a la clase DTO
        // Devuelve la funcion
        UserDto unUsuarioDto = mapper.map(unUser, UserDto.class);

        return unUsuarioDto;
    }

    @Override
    public boolean existeUsuario(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existeUsuario(String name) {
        return userRepository.existsByName(name);
    }


    @Override
    public RespUserDto borrarUsuario(Long id) {
        RespUserDto respuesta = new RespUserDto();

        // Verificar si existe el usuario
        if (!existeUsuario(id)) {
            respuesta.setMessage("no encontré el ID: " + id);
        } else {
            // Borrar directamente
            userRepository.deleteById(id);

            // Prepara la respuesta y la devuelve
            respuesta.setMessage("Se ha borrado correctamente el usuario con el ID:" + id);
        }
        return respuesta;

    }

    @Override
    public RespUserDto borrarUsuario(String name) {
        RespUserDto respuesta = new RespUserDto();

        // Verificar si existe el usuario
        if (!userRepository.existsByName(name)) {
            respuesta.setMessage("no encontré el usuario llamado: " + name);

        } else {
            User u = userRepository.findByName(name);
            userRepository.deleteById(u.getId());

            // Prepara la respuesta y la devuelve
            respuesta.setMessage("Se ha borrado correctamente el usuario con el Nombre:" + name);
        }
        return respuesta;
    }

    @Override
    public RespUserDto createUser(UserDto userDto) {
        // Definir para el modelo la clase USER
        ModelMapper modelMapper = new ModelMapper();
        RespUserDto resp = new RespUserDto();

        if (userRepository.existsByName(userDto.getUserName())) {
            resp.setMessage("Ya hay un suario con el UerrName: " + userDto.getUserName());
        } else {
            // Modelar el DTO recibido a la clase USER
            User user = modelMapper.map(userDto, User.class);

            // Redefinir cada elemento del DTO a USER
            user.setUserName(userDto.getUserName());
            user.setUserKey(userDto.getUserKey());
            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setUserLevel(1);
            user.setStartDate(LocalDate.now());

            User persistUser = userRepository.save(user);

            resp = new RespUserDto();
            resp.setUserDto(modelMapper.map(persistUser, UserDto.class));
            resp.setMessage("Guardando el ususario correctamente ...");
        }
        return resp;
    }

    @Override
    public RespUserDto editarUser(UserDto usrDto) {
        RespUserDto respuesta = new RespUserDto();
        // Verificar si existe el usuario
        if (!existeUsuario(usrDto.getId())) {
            respuesta.setMessage("no encontré el usuario con el ID: " + usrDto.getId());
        } else {
            // Borrar Trae el usuario
            Optional<User> usr = userRepository.findById(usrDto.getId());

            if (usrDto.getUserLevel() == null || usrDto.getUserLevel() < 1)
                usrDto.setUserLevel(5);
            if ((usrDto.getStartDate() == null))
                usrDto.setStartDate(LocalDate.now());
            if (usrDto.getUserName().equals(null) || usrDto.getUserName().equals(""))
                usrDto.setUserName(usrDto.getEmail());
            if (usrDto.getUserKey().equals(null) || usrDto.getUserKey().equals(""))
                usrDto.setUserKey(ponerClave(usrDto.getEmail()));
            if (usrDto.getName().equals(null) || usrDto.getName().equals(""))
                usrDto.setName("xxxxx");
            if (usrDto.getLastName().equals(null) || usrDto.getLastName().equals(""))
                usrDto.setLastName("LOLOLO");

            //usr.stream().forEach(u -> usrDto);

            // Prepara la respuesta y la devuelve
            respuesta.setMessage("Se ha borrado correctamente el usuario con el ID:" + usrDto.getId());
        }
        return respuesta;

    }

    private String ponerClave(String semilla) {
        String pas = "";
        for (int i = 0; i < semilla.length() / 2; i++) {
            int ascii = semilla.charAt(i);
            ascii = ascii + (ascii %= 10);
            pas = pas + semilla.charAt(i) + (char) ascii;
        }
        return pas;
    }


}
