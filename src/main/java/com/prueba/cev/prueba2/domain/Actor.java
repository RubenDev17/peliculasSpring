package com.prueba.cev.prueba2.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Actor {

	@Id
	@GeneratedValue
	Long id;
	
	String nombre;
	//Relación N a M el actor podrá tener N películas y la película podrá tener M actores, por ello usaremos la notación @ManyToMany y un listado ya que van a ser
	//varias películas
	@ManyToMany
	List<Pelicula> peliculas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	
}
