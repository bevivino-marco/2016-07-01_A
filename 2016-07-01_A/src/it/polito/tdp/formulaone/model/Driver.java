package it.polito.tdp.formulaone.model;

import java.time.LocalDate;

public class Driver {

	private int driverId;
	private String driverRef;
	private int number;
	private String code;
	private String forename;
	private String surname;
	

	public Driver(int driverId, String driverRef, int number, String code, String forename, String surname) {
		super();
		this.driverId = driverId;
		this.driverRef = driverRef;
		this.number = number;
		this.code = code;
		this.forename = forename;
		this.surname = surname;
	
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverRef() {
		return driverRef;
	}

	public void setDriverRef(String driverRef) {
		this.driverRef = driverRef;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return String.format("Driver %s   %s %s\n", driverId, forename, surname);
	}



	

}
