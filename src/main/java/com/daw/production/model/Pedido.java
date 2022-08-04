package com.daw.production.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cantidad")
	private String cantidad;

	@Column(name = "unidad")
	private String unidad;

	@Column(name = "stsordveri")
	private boolean stsordveri;
	
	@Column(name = "stspedveri")
	private boolean stspedveri;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore

	private Order order;

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public boolean isStsordveri() {
		return stsordveri;
	}

	public void setStsordveri(boolean stsordveri) {
		this.stsordveri = stsordveri;
	}

	public boolean isStspedveri() {
		return stspedveri;
	}

	public void setStspedveri(boolean stspedveri) {
		this.stspedveri = stspedveri;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
