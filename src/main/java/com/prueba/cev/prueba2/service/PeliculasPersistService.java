//Clase creada para manejarnos con el repositorio y no tocar el peliculasService que habíamos creado para el array

package com.prueba.cev.prueba2.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.cev.prueba2.domain.Pelicula;
import com.prueba.cev.prueba2.repository.PeliculaRepository;

@Service
public class PeliculasPersistService {
	
	//Inyección del repositorio en el servicio para trabajar con él
	PeliculaRepository peliculaRepository;

	//Constructor a partir de Fields creado para servicio pasándole como parámetro el repositorio
	public PeliculasPersistService(PeliculaRepository peliculaRepository) {
		this.peliculaRepository = peliculaRepository;
	}
	
	// ---------- METODO GET (OBTENER PELÍCULA POR ID CON JPA)
	//Método que buscará una película por el id, obteniéndola desde el repositorio.
	public Pelicula getPelicula(Long id) {
		return peliculaRepository.getOne(id);
	}
	
	// OBTENER LISTADO COMPLETO DE PELÍCULAS CON JPA
	public List<Pelicula> getPeliculas(){
		return peliculaRepository.findAll();
	}
	
	
	// --------- METODO POST (AGREGAR PELÍCULA AL LISTADO)
	public Long add(Pelicula pelicula) {
		
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
		return peliculaGuardada.getId();
	}
	
	// MODIFICAR PELÍCULAS 
	//Debemos asegurarnos de que se guarde en la instancia que tiene la id que recibimos por parámetro, para ello, obtendremos el id de la película y lo 
	//guardaremos en una nueva instancia de película. Tras ello iremos seteando el resto de campos de la nueva instancia con los de la que nos viene por parámetro.
	//Guardamos la nueva instancia de película.
	public void guarda(Long id, Pelicula pelicula) {
		Pelicula peliculaGuardada = peliculaRepository.getOne(id);
		peliculaGuardada.setDirector(pelicula.getDirector());
		peliculaGuardada.setFechaEstreno(pelicula.getFechaEstreno());
		peliculaGuardada.setPuntuacion(pelicula.getPuntuacion());
		peliculaGuardada.setSinopsis(pelicula.getSinopsis());
		peliculaGuardada.setTitulo(pelicula.getTitulo());
		peliculaRepository.save(peliculaGuardada);	
	}
	//-------------- BORRAR PELÍCULAS CON JPA ----------------/
	//Solo tenemos el id, por lo que no podemos borrar directamente la película, para ello deberemos obtener 
	//en el parámetro de borrado la película por el id que nos viene
	public void borra(Long id) {
		peliculaRepository.delete(peliculaRepository.getOne(id));
		
	}
	
	public Optional<Pelicula> findById(Long id){
		return peliculaRepository.findById(id);
	}
	
	//Método al que llama el controlador de película. En él estamos creando un método de tipo lista el cual nos 
	//devuelve el método del repositorio.
	public List<Pelicula> findByTituloContaining(String titulo){
		return peliculaRepository.findByTituloContaining(titulo);
	}

}
