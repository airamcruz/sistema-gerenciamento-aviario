package br.com.airamcruz.projeto.integrador.model;

import java.util.Date;

public class BoletimSanitarioModel {
	private int id;
	private Date data;
	private String parecer;
	private AviarioModel aviarioModel;
	
	public BoletimSanitarioModel() {
		super();
	}
	
	public BoletimSanitarioModel(int id) {
		super();
		this.id = id;
	}
	
	public BoletimSanitarioModel(int id, Date data, String parecer, AviarioModel aviarioModel) {
		super();
		this.id = id;
		this.data = data;
		this.parecer = parecer;
		this.aviarioModel = aviarioModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public AviarioModel getAviarioModel() {
		return aviarioModel;
	}

	public void setAviarioModel(AviarioModel aviarioModel) {
		this.aviarioModel = aviarioModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoletimSanitarioModel other = (BoletimSanitarioModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
