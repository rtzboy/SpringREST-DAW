package com.daw.production.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "numorden")
	private String numorden;

	@Column(name = "fecha")
	private String fecha;

	@Column(name = "estado")
	private boolean estado;

	public Order() {
	}

	public Order(String numorden, String fecha, boolean estado) {
		this.numorden = numorden;
		this.fecha = fecha;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public String getNumorden() {
		return numorden;
	}

	public void setNumorden(String numorden) {
		this.numorden = numorden;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", numorden=" + numorden + ", fecha=" + fecha + ", estado=" + estado + "]";
	}

}
