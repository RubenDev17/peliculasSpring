//Clase creada en el paquete dominio donde irán todos los elementos del dominio

package com.prueba.cev.prueba2.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Clase pelicula que manejará el controlador

//Con la notación @Entity estamos relacionando esta clase con una tabla en base de datos 
@Entity
public class Pelicula {
	
	//Deberemos obligatoriamente si la clase es una entidad, hacer que esta clase tenga un id el cual referenciaremos
	//con la notación @Id para que JPA sepa que ese atributo va a ser el id de la clase y con @GeneratedValue, le estaremos diciendo
	//a JPA que genere el valor automáticamente y sea autoincremental NO OLVIDARNOS DE GETTER Y SETTER TRAS DEFINIR EL ID
	@Id
	@GeneratedValue
	Long id;
	
	//Si queremos modificar el nombre del campo en la base de datos y que sea diferente en la tabla, lo podremos hacer a través de @Column(name = "nombreBbdd")
	//deberemos implementar uno por cada campo que queramos modificar en la tabla de la bbbdd.
	@Column(name = "titulo")
	String titulo;
	int puntuacion;
	String sinopsis;
	String director;
	Date fechaEstreno;
	
	//Queremos acceder a los datos de estreno mediante la entidad película, para ello debemos crear un campo estreno dentro de película, y pasarle
	//la notación @OneToOne pero esta vez con (mappedBy = "pelicula") diciendo que esta relación OneToOne la va a gestionar estreno, pero que va a corresponder al 
	//campo película que hay dentro de estreno.
	@OneToOne(mappedBy = "pelicula")
	//Para evitar errores de consulta circiular (bucles infinitos), deberemos poner @JsonManagedReference en donde tengamos mappedBy
	@JsonManagedReference
	Estreno estreno;
	
	//Referencia a listado de reviews debido a que esta entidad se relaciona con review con la relación 1 a N, por lo que esta entidad puede tener N reviews
	//Como es una relación 1 a N debemos decir que esta es el OneToMany de la relación, review es el ManyToOne. Para que no se cree una clave foránea tambien en 
	//esta entidad, debemos decirle que la relación va mapeada mediante el atributo película de review.
	@OneToMany(mappedBy = "pelicula")
	//Para evitar problemas de consulta circular usamos como en la relación 1 a 1 el @JsonManagedReference (no foránea) y el @JsonBackReference (foránea)
	@JsonManagedReference
	List<Review> reviews;
	
	//Como tenemos la relación creada desde actores, le pasamos el atributo que mapea la relación en actores al mapped By para que la entidad película lo sepa
	@ManyToMany(mappedBy = "peliculas")
	//@JsonBackReference y ManagedReference no funciona en esta relación solo en las de OneToMany y ManyToOne. En este caso debemos ignorar el campo películas de Actor
	//para evitar la consulta circular
	@JsonIgnoreProperties({"peliculas"})
	List<Actor> actores;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public Estreno getEstreno() {
		return estreno;
	}
	public void setEstreno(Estreno estreno) {
		this.estreno = estreno;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Actor> getActores() {
		return actores;
	}
	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	
	


}
