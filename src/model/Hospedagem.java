package model;

import java.util.Date;

public class Hospedagem {

	private long id;
	private String nomeHotel;
	private String endereço;
	private String estado;
	private float valorIntegral;
	private Date checkIn;
	private Date checkOut;
	private String numeroHotel;
	public Cliente cliente;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeHotel() {
		return nomeHotel;
	}
	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public float getValorIntegral() {
		return valorIntegral;
	}
	public void setValorIntegral(float valorIntegral) {
		this.valorIntegral = valorIntegral;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public String getNumeroHotel() {
		return numeroHotel;
	}
	public void setNumeroHotel(String numeroHotel) {
		this.numeroHotel = numeroHotel;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	


}