package model;

public class Cliente {

	private long id;
	private String cpf;
	private String nomeCompleto;
	private String rua;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String email;
	private String numero;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}


public String toString() {
    return 
            "Cpf: " + cpf + '\'' +
            ", Nome Completo:  '" + nomeCompleto + '\'' +
            ", Rua: " + rua + '\'' +
            ", Bairro: '" + bairro + '\'' +
            ", CEP: " + cep + '\'' +
            ", Cidade: " + cidade + '\'' +
            ", Estado:" + estado + '\'' +
            ", Email:" + email + '\'' +
            ", Numero Telefone: " + numero
            ;
}

}