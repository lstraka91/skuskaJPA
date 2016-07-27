package sk.tsystems.gamestudio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Player {
	@Id
	@GeneratedValue
	@Column(name="id_user")
	private int id;
	private String name;
	private String password;
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(name="date_registration")
	private Date date_register;
	
	
	public Date getDate_register() {
		return date_register;
	}
	public void setDate_register(Date date_register) {
		this.date_register = date_register;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	public Hrac(String name) {
//		super();
//		this.name = name;
//	}

}
