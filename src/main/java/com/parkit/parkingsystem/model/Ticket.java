package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
	private int id;
	private ParkingSpot parkingSpot;
	private String vehicleRegNumber;
	private double price;
	private Date inTime;
	private Date outTime;
	private boolean recurrence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getInTime() {
		return new Date(inTime.getTime());
	}

	public void setInTime(Date inTime) {
		this.inTime = new Date(inTime.getTime());
	}

	public Date getOutTime() {
		if (outTime != null) {
			return new Date(outTime.getTime());
		}
		return null;
	}

	public void setOutTime(Date outTime) {
		if (outTime != null) {
			this.outTime = new Date(outTime.getTime());
		}
	}

	public boolean getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(boolean recurrence) {
		this.recurrence = recurrence;
	}

}
