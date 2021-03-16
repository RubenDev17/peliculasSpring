package com.prueba.cev.prueba2.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Estreno {

	@Id
	@GeneratedValue
	Long id;
	

	//Esta entidad es la que va a gestionar la relación por lo que le vamos a meter un campo que sea de tipo película:
	//Tendremos que asignar este campo como clave foránea, para ello usamos la notación @OneToOne
	//Para evitar errores de consulta circular (bucle infinito) debemos poner en donde no tengamos mappedBy @JsonBackReference
	
	@OneToOne
	@JsonBackReference
	Pelicula pelicula;
	
	String lugar;
	Instant fecha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Instant getFecha() {
		return fecha;
	}
	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
}
