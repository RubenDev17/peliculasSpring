//Clase para errores personalizados.

//Con esta clase podremos implementar los errores sin tener que poner explícitamente en el método que tiene que lanzarla

package com.prueba.cev.prueba2.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Debe extender de RuntimeException

//Notación para que Spring sepa que tiene que devolver un código de estado.

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomError extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public CustomError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
