package com.leodesanmiguel.fichadocente;

import com.leodesanmiguel.fichadocente.dto.RespUserDto;
import com.leodesanmiguel.fichadocente.dto.UserDto;
import com.leodesanmiguel.fichadocente.services.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FichaDocenteApplicationTests {


	@Autowired
	private IUserService userService;

	@Test
	void crearUsuarioTestOk() {

		//arrange
		UserDto userDto = new UserDto();
		userDto.setId(3000L);
		userDto.setUserName("UsuarioTest");
		userDto.setUserKey("AB3456lm-Test");
		userDto.setName("Sr. Tester");
		userDto.setLastName("Usuario");
		userDto.setEmail("tester_usuario@gmail.com");
		userDto.setUserLevel(1);
		userDto.setStartDate(LocalDate.now());

		RespUserDto dtoExpected = new RespUserDto(userDto, "El usuario fue creado!!");
		//act
		RespUserDto dtoReal =  userService.createUser(userDto);
		//assert
		assertEquals(dtoExpected, dtoReal);
	}
}
