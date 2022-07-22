package com.daw.production.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "urlimage")
	private String urlimage;

	public Product() {

	}

	public Product(String nombre, String categoria, String urlimage) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.urlimage = urlimage;
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", urlimage=" + urlimage + "]";
	}

}
