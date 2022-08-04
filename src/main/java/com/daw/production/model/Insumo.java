package com.daw.production.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "insumos")
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "insumo")
	private String insumo;

	@Column(name = "cantidad")
	private String cantidad;

	@Column(name = "unidad")
	private String unidad;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "refer_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore

	private Referencia referencia;

	public Long getId() {
		return id;
	}

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
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

	public Referencia getReferencia() {
		return referencia;
	}

	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

}
