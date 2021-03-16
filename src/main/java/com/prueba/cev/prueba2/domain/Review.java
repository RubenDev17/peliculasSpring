package com.prueba.cev.prueba2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Review {
	
	@Id
	@GeneratedValue
	Long id;
	
	int puntuacion;
	
	String descripcion;
	
	//Estamos diciendo a JPA con @ManyToOne que el campo que esté debajo de la notación,
	//va a ser una clave foránea.
	@ManyToOne
	//Para evitar problemas de consulta circular, al ser la que contiene la clave foránea esta entidad, usaremos @JsonBackReference
	@JsonBackReference
	Pelicula pelicula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	

}
