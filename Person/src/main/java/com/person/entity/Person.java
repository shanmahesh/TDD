package com.person.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;



@Data
@Entity
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	@Column(name = "firstNm")
	public String firstNm;
	@Column(name = "lastName")
	public String lastName;
	@Column(name="birthDt")
	@JsonFormat(pattern = "MM/dd/yyyy")
	public Date birthDt;
	
	@Column(name = "status")
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstNm() {
		return firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDt() {
		return birthDt;
	}
	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstNm=" + firstNm + ", lastName=" + lastName + ", birthDt=" + birthDt + "]";
	}
	
	
	
	
	
}
