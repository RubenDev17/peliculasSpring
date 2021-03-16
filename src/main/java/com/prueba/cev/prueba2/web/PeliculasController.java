//Clase creada dentro del paquete web al ser el controlador

package com.prueba.cev.prueba2.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cev.prueba2.domain.Pelicula;
import com.prueba.cev.prueba2.repository.PeliculaRepository;
import com.prueba.cev.prueba2.service.PeliculasPersistService;
import com.prueba.cev.prueba2.web.error.CustomError;

//Notacion para indicar que es un controlador de Rest
@RestController

//Controlador que crea un arrayList de peliculas para devolverlo cuando hagamos un GET
public class PeliculasController {
	
	//USO DEL LOG// Para poder hacer uso del log deberemos implementar la siguiente línea y decirle sobre la clase que lo vamos a usar
	private final Logger log = LoggerFactory.getLogger(PeliculasController.class);

	// Instancia de PeliculasService para manejar los servicios y poder llamar a sus
	// métodos.
	// La notación dice al controlador que debe obtener esta instancia del contexto
	// e inyectarla.
	@Autowired
	PeliculasPersistService peliculasPersistService;

	// Variable repository para no tener que intermediar con el servicio (NO ES EL
	// FLUJO CORRECTO.. Flujco correcto = Controller --> Service --> <--Repository)
	@Autowired
	PeliculaRepository peliculaRepository;

	// Notacion que mapeara como una peticion GET /peliculas y ejecutará el método
	// de abajo
	@GetMapping(path = "/peliculas")

	// Usamos parámetros introducidos por URL usando la notación @RequestParam
	List<Pelicula> getPeliculas(@RequestParam(required = false) String titulo,
			@RequestParam(required = false, name = "puntuacion-min") Integer puntuacionMinima,
			@RequestParam(required = false, name = "puntuacion-max") Integer puntuacionMax,
			@RequestParam(required = false, name = "actor") String actor,
			HttpServletRequest request, // Objeto de tipo HttpRequest para comprobar el rol del usuario
			Principal principal) { //Objeto que se crea cuando se logguea un usuario, dentro de él estarán todos los datos del usuario que se ha loggueado, deberemos crearlo en el 
			//código para poder llamarlo y meter los datos en un log por ejemplo.

		//Variable log para mostrar el mensaje en el log en la que obtenemos el nombre del objeto principal que creamos en el que se guardan todos los datos del usuario
		log.info("Eres: " + principal.getName());
		
		//Dejaremos al usuario hacer el filtrado solo si tiene el rol de ADMIN
		if (request.isUserInRole("ROLE_ADMIN")) {

			if (actor != null) {
				peliculaRepository.findByActores_nombreOrderByPuntuacionDesc(actor);
			}

			// Nido de ifs para contemplar diferentes casiuísticas de las peticiones en
			// relación a la puntuación máxima, mínima y el título
			if (puntuacionMinima != null) {
				if (puntuacionMax != null) {
					if (titulo != null) { // Tenemos puntuación mínima, puntuación máxima y título
						return peliculaRepository
								.findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(
										titulo, puntuacionMinima, puntuacionMax);
					} else { // Tenemos puntuación mínima, puntuación máxima y título = null
						return peliculaRepository.findByPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(
								puntuacionMinima, puntuacionMax);
					}
				} else {
					if (titulo != null) { // Tenemos puntuación mínima, puntuación máxima = null y título
						return peliculaRepository.findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqual(titulo,
								puntuacionMinima);
					} else { // Tenemos puntuación mínima, puntuación máxima = null y título = null
						return peliculaRepository.findByPuntuacionGreaterThanEqual(puntuacionMinima);
					}
				}

			} else {

				if (puntuacionMax != null) {
					if (titulo != null) { // Tenemos puntuación mínima = null, puntuación máxima y título
						return peliculaRepository.findByTituloContainingIgnoreCaseAndPuntuacionLessThanEqual(titulo,
								puntuacionMax);
					} else { // Tenemos puntuación mínima = null, puntuación máxima y título = null
						return peliculaRepository.findByPuntuacionLessThanEqual(puntuacionMax);
					}
				} else {
					if (titulo != null) { // Tenemos puntuación mínima = null, puntuación máxima = null y título
						return peliculaRepository.findByTituloContainingIgnoreCase(titulo);
					} else { // Tenemos puntuación mínima, puntuación máxima = null y título = null
						return peliculaRepository.findAll();
					}
				}

			}
		}
		else return peliculaRepository.findAll();
	}

	// Obtener película por su ID:

	// Notacion que mapeara como una peticion GET /peliculas/{id}. El id es un
	// parámetro que le pasará al método getPeliculas()
	@GetMapping(path = "/peliculas/{id}")

	// La notación PathVariable recibe int id en el método para devolver la película
	// que tenga ese id LA VARIABLE TIENE QUE LLAMARSE IGUAL QUE EL
	// PARAMETRO {id} == id
	Pelicula getPeliculas(@PathVariable Long id) {
		// Para evitar errores cuando no se encuentre el id creamos la siguiente
		// condición con su excepción perosnalizada.
		if (peliculasPersistService.findById(id).isPresent()) {
			return peliculasPersistService.findById(id).get();
		} else {
			throw new CustomError("No se encuentra esa pelicula");
		}
	}

	// --------- METODO POST ----------// (AÑADIR)

	// Notación para el método POST , con el método devolveremos un int para saber
	// qué id tiene el elemento que acabamos de dar de alta
	@PostMapping(path = "/peliculas")

	// Con la notación @RequestBody Spring cogerá todos los datos de entrada y nos
	// los meterá en la variable de tipo Película.
	Long altaPelicula(@RequestBody Pelicula pelicula) {
		// llamada al servicio donde tenemos la lógica.
		return peliculasPersistService.add(pelicula);
	}

	// --------- METODO PUT ----------// (MODIFICACION)

	// Notación para método put, para hacer el put deberemos tener el id de la
	// película por lo que deberemos devolverla
	@PutMapping(path = "/peliculas/{id}")

	// Con la notación @RequestBody Spring cogerá todos los datos de entrada y nos
	// los meterá en la variable de tipo Película.

	// @PathVariable será la variable que podremos recibir como parámetro de entrada
	// para la modificación
	Pelicula modificaPelicula(@RequestBody Pelicula pelicula, @PathVariable Long id) {
		peliculasPersistService.guarda(id, pelicula);
		return pelicula;
	}

	// --------- METODO DELETE ----------// (BORRAR)

	// Notación para el borrardo, deberemos obtener la id de las peliculas porque lo
	// que queremos es borrar un elemento en concreto
	@DeleteMapping(path = "/peliculas/{id}")
	String borrarPelicula(@PathVariable Long id) {
		peliculasPersistService.borra(id);
		return ("Ok");

	}

	// Modificar los headers de la respuesta:
	@GetMapping(path = "/peliculasHeader")
	ResponseEntity<List<Pelicula>> getPeliculasHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("MiHeaderRespuesta", "HeaderRespuesta");
		return ResponseEntity.ok().headers(headers).body(peliculasPersistService.getPeliculas());
	}

}
