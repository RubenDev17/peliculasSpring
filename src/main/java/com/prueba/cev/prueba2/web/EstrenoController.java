package com.prueba.cev.prueba2.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cev.prueba2.domain.Estreno;
import com.prueba.cev.prueba2.repository.EstrenoRepository;



@RestController
public class EstrenoController {
	
	//Como no vamos a necesitar el servicio ya que no vamos a necesitar lógica para las funciones que queremos implementar
	//inyectamos el repositorio directamente en el controller
	EstrenoRepository estrenoRepository;
	
	
	//Para poder inyectar, deberemos crear el constructor también si no no funcionarña
	public EstrenoController(EstrenoRepository estrenoRepository) {
		this.estrenoRepository = estrenoRepository;
	}


	@GetMapping("/estrenos")
	//Devolvemos listado completo de estrenos
	List<Estreno> getAll(){
		return estrenoRepository.findAll();
	}
	
	
	@PostMapping("estrenos")
	Estreno altaEstreno(@RequestBody Estreno estreno) {
		return estrenoRepository.save(estreno);
	}

}
