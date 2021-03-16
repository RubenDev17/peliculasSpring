package com.prueba.cev.prueba2.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cev.prueba2.domain.Actor;
import com.prueba.cev.prueba2.repository.ActorRepository;

@RestController
public class ActorController {

	//Como no vamos a necesitar el servicio ya que no vamos a necesitar lógica para las funciones que queremos implementar
	//inyectamos el repositorio directamente en el controller
	ActorRepository ActorRepository;
	
	
	//Para poder inyectar, deberemos crear el constructor también si no no funcionarña
	public ActorController(ActorRepository ActorRepository) {
		this.ActorRepository = ActorRepository;
	}


	@GetMapping("/actores")
	//Devolvemos listado completo de estrenos
	List<Actor> getAll(){
		return ActorRepository.findAll();
	}
	
	
	@PostMapping("actores")
	Actor altaActor(@RequestBody Actor actor) {
		return ActorRepository.save(actor);
	}
}
