package br.com.frigidev.library.dto;

public class LoginCredentials {
	private String email;
	private String password;

	public LoginCredentials() {
	}

	public LoginCredentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}