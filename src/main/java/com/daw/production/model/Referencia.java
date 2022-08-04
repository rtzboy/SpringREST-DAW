package com.daw.production.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "referencias")
public class Referencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "refer")
	private String refer;

	@Column(name = "fecha")
	private String fecha;

	@Column(name = "send")
	private boolean send;

	@Column(name = "ctrlqual")
	private boolean ctrlqual;

	@Column(name = "mixed")
	private boolean mixed;

	@Column(name = "homog")
	private boolean homog;

	@Column(name = "calor")
	private boolean calor;

	@Column(name = "pasteu")
	private boolean pasteu;

	@Column(name = "refrig")
	private boolean refrig;

	@Column(name = "reposo")
	private boolean reposo;

	@Column(name = "congel")
	private boolean congel;

	@Column(name = "embalaje")
	private boolean embalaje;

	@Column(name = "freeze")
	private boolean freeze;

	@Column(name = "finished")
	private boolean finished;

	public Referencia() {
	}

	public Referencia(String refer, String fecha, boolean send, boolean ctrlqual, boolean mixed, boolean homog,
			boolean calor, boolean pasteu, boolean refrig, boolean reposo, boolean congel, boolean embalaje,
			boolean freeze, boolean finished) {
		super();
		this.refer = refer;
		this.fecha = fecha;
		this.send = send;
		this.ctrlqual = ctrlqual;
		this.mixed = mixed;
		this.homog = homog;
		this.calor = calor;
		this.pasteu = pasteu;
		this.refrig = refrig;
		this.reposo = reposo;
		this.congel = congel;
		this.embalaje = embalaje;
		this.freeze = freeze;
		this.finished = finished;
	}

	public long getId() {
		return id;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public boolean isCtrlqual() {
		return ctrlqual;
	}

	public void setCtrlqual(boolean ctrlqual) {
		this.ctrlqual = ctrlqual;
	}

	public boolean isMixed() {
		return mixed;
	}

	public void setMixed(boolean mixed) {
		this.mixed = mixed;
	}

	public boolean isHomog() {
		return homog;
	}

	public void setHomog(boolean homog) {
		this.homog = homog;
	}

	public boolean isCalor() {
		return calor;
	}

	public void setCalor(boolean calor) {
		this.calor = calor;
	}

	public boolean isPasteu() {
		return pasteu;
	}

	public void setPasteu(boolean pasteu) {
		this.pasteu = pasteu;
	}

	public boolean isRefrig() {
		return refrig;
	}

	public void setRefrig(boolean refrig) {
		this.refrig = refrig;
	}

	public boolean isReposo() {
		return reposo;
	}

	public void setReposo(boolean reposo) {
		this.reposo = reposo;
	}

	public boolean isCongel() {
		return congel;
	}

	public void setCongel(boolean congel) {
		this.congel = congel;
	}

	public boolean isEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(boolean embalaje) {
		this.embalaje = embalaje;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@Override
	public String toString() {
		return "Referencia [id=" + id + ", refer=" + refer + ", fecha=" + fecha + ", send=" + send + ", ctrlqual="
				+ ctrlqual + ", mixed=" + mixed + ", homog=" + homog + ", calor=" + calor + ", pasteu=" + pasteu
				+ ", refrig=" + refrig + ", reposo=" + reposo + ", congel=" + congel + ", embalaje=" + embalaje
				+ ", freeze=" + freeze + ", finished=" + finished + "]";
	}

}
