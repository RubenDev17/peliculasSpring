package com.prueba.cev.prueba2.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cev.prueba2.domain.Review;
import com.prueba.cev.prueba2.repository.ReviewRepository;

@RestController
public class ReviewController {

	
	//Como no vamos a necesitar el servicio ya que no vamos a necesitar lógica para las funciones que queremos implementar
	//inyectamos el repositorio directamente en el controller
	ReviewRepository reviewRepository;
	
	
	//Para poder inyectar, deberemos crear el constructor también si no no funcionarña
	public ReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}


	@GetMapping("/reviews")
	//Devolvemos listado completo de estrenos
	List<Review> getAll(){
		return reviewRepository.findAll();
	}
	
	
	@PostMapping("reviews")
	Review altaReview(@RequestBody Review review) {
		return reviewRepository.save(review);
	}
}
