//Clase que actua como servicio, se llega a ella a través del controlador y es donde albergamos la lógica de negocio
//Cortamos la lógica de negocio que teníamos en el controlador y nos la traemos al service
package com.prueba.cev.prueba2.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.cev.prueba2.domain.Pelicula;

//Notación en la que Spring cogerá esta clase, la instanciará y la dejará en el contexto, y cuando alguien solicite una instancia
//de tipo peliculasService nos la va a inyectar ahí.
@Service
public class PeliculasService {

	List<Pelicula> peliculas = new ArrayList<Pelicula>();

	// Constructor creado con click derecho /Source/Generate constructor from
	// Superclass
	public PeliculasService() {
		// Creamos una película y la añadimos al List peliculas
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("KillBill");
		pelicula.setPuntuacion(10);
		pelicula.setSinopsis("Uma Thruman con mono amarillo y pegando sablazos");
		pelicula.setFechaEstreno(new GregorianCalendar(2003, Calendar.JANUARY, 01).getTime());
		pelicula.setDirector("Quentin Tarantino");
		peliculas.add(pelicula);
	}

	// -------- METODO GET----------// (OBTENER)

	// (OBTENER POR ID)

	public Pelicula getPelicula(int id) {
		return peliculas.get(id - 1);
	}

	// (OBTENER LISTADO)
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	// (OBTENER POR TÍTULO)
	// Array para meter los resultados que coincidan con el titulo
	public List<Pelicula> buscaPorTitulo(String titulo) {
		List<Pelicula> peliculasResultado = new ArrayList<Pelicula>();

		// Bucle for para buscar en el array las películas que coincidan con el título
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getTitulo().contains(titulo)) {
				peliculasResultado.add(pelicula);
			}
		}
		return peliculasResultado;
	}

	// --------- METODO POST ----------// (AÑADIR)

	public int add(Pelicula pelicula) {
		peliculas.add(pelicula);
		// devolvemos el tamaño del array de nuestro listado
		return peliculas.size();
	}

	// -------- METODO PUT -----------// (MODIFICACION)
	public void guarda(int id, Pelicula pelicula) {
		// método que modificará la posición del array del id
		peliculas.set(id - 1, pelicula);
	}

	// --------- METODO DELETE -------// (BORRADO) por id
	public void borra(int id) {
		peliculas.remove(id - 1);
	}

}
