package com.prueba.cev.prueba2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.cev.prueba2.domain.Pelicula;

//Notación @Repository indica a Spring que esta interfaz va a ser un repositorio
@Repository
// Deberemos pasarle por parámetro en el extends, la entidad sobre la que actua este repositorio y el tipo de dato que es el identificador de la entidad (Long)
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
	
	//Método que es llamado desde el service y tiene que ser devuelto a él, desde aquí llamamos a las funciones de JPA que será el encargado de 
	//trabajar con los datos y devolver la respuesta, esta irá al service y del service al controlador.
	List<Pelicula> findByTituloContaining(String titulo);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(String titulo,
			Integer puntuacionMinima, Integer puntuacionMax);

	List<Pelicula> findByPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(Integer puntuacionMinima,
			Integer puntuacionMax);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqual(String titulo,
			Integer puntuacionMinima);

	List<Pelicula> findByPuntuacionGreaterThanEqual(Integer puntuacionMinima);

	List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

	List<Pelicula> findByPuntuacionLessThanEqual(Integer puntuacionMax);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionLessThanEqual(String titulo, Integer puntuacionMax);

	//Obtener listado de películas desde una entidad que está referenciada dentro de la entidad película (review, actor...)
	List<Pelicula> findByActores_nombre(String nombre);
	
	//Obtener el resultado ordenado por la puntuación en orden descendente
	List<Pelicula> findByActores_nombreOrderByPuntuacionDesc(String nombre);
	
	//Consulta HQL con ella estamos aportando una consulta diferente a lo que nos aporta JPA (Se suele usar para actualización de datos de manera masiva)
	@Query("Select pelicula from Pelicula pelicula where pelicula.titulo=:titulo")
	List<Pelicula> findByTituloHibernate(@Param("titulo") String titulo);
	
	//Consultas en SQL nativo (No se suele hacer para hacer Select, se suele usar para hacer actualizaciones de bases de datos)
	@Query(nativeQuery = true, value = "select * from pelicula p where p.titulo=:titulo")
	List<Pelicula> findByTituloSql(@Param("titulo") String titulo);


}
