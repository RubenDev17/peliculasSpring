package com.prueba.cev.prueba2.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Clase controlador para acceder al contenido del header


//Notación para definir que esta clase es un controlador
@RestController
public class BrujaAveriaController {

	@GetMapping("/brujaAveria")
	
	@PreAuthorize(value = "hasRole(ROL_ADMIN)")
	//Notación @RequestHeader para hacer peticiones de cabecera, en este caso, sí que le decimos
	//el nombre que va a llevar la variable
	String dimeNavegador(@RequestHeader(name = "user-agent") String userAgent) {
		
		return "Estás navegando en: " + userAgent + "Y LO SABES";
	}
}
