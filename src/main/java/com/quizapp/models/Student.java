package com.quizapp.models;

import javax.persistence.*;

@Entity
@Table(name = "students", schema = "public")
public class Student {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(
			strategy = GenerationType.AUTO,
			generator = "student_generator"
	)
	@SequenceGenerator(
			name = "student_generator",
			sequenceName = "student_seq", allocationSize = 1
	)

	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name="student_role")
	private String role;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="student")
	private Session session;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
