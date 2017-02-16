package com.daniel.classes;

import java.util.Scanner;

public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String role;
	
	
	public User(Integer id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public String inputPassword(String password) {
		Scanner sc = new Scanner(System.in);
		String matcher = "([A-Z]|[a-z]|[0-9]){8,26}";
		while (!password.matches(matcher)) {
			System.out.println("Wrong password. Must be 8-26 letters or digits only.");
			password = sc.nextLine();
		}
		sc.close();
		return password;

	}

	public void setPassword(String password) {
		String matcher = "([A-Z]|[a-z]|[0-9]){8,26}";
		if (password.matches(matcher)) {
			this.password = password;
		} else {
			inputPassword(password);
		}
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	public static char[][] tabCreator() {
		char[][] tab = new char[26][26];
		char a = 97;
		for (int i = 0; i < tab.length; i++) {
			char b = a;
			for (int j = 0; j < tab[i].length; j++) {
				tab[i][j] = a;
				if (a == 'z') {
					a = 97;
				} else {
					a++;
				}

			}
			a = b;
			a++;
		}
		return tab;
	}
	
	public static String encrypter(String password) {

		char[][] tab = tabCreator();

		StringBuilder keyExit = new StringBuilder();
		String key = "vigenere";
		int k = 0;
		while (keyExit.length() != password.length()) {
			keyExit.append(key.charAt(k));

			if (k == key.length() - 1) {

				k = 0;

			} else {

				k++;
			}
		}

		StringBuilder passwordExit = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			int j = 0;
			int j2 = 0;
			boolean isBig = false;
			if (Character.isDigit(password.charAt(i))) {
				passwordExit.append(10 - Character.getNumericValue(password.charAt(i)) - 1);
			} else {
				if (Character.isUpperCase(password.charAt(i))) {
					isBig = true;
					while (Character.toLowerCase(password.charAt(i)) != tab[0][j]) {

						j++;

					}
				} else {
					while (password.charAt(i) != tab[0][j]) {

						j++;

					}
				}

				while (keyExit.charAt(i) != tab[j2][0]) {

					j2++;

				}

				if (isBig == true) {
					passwordExit.append(Character.toUpperCase(tab[j2][j]));
				} else {
					passwordExit.append(tab[j2][j]);
				}

			}
		}

		return passwordExit.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	
	

}
