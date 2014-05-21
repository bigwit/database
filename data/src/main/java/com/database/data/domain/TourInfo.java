package com.database.data.domain;

import javax.persistence.*;

@Entity
public class TourInfo {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "name_place")
	private String namePlace;

	@Column(name = "travel_info")
	private String travelInfo;

	@Column(name = "flight_info")
	private String flightInfo;

	@Column(name = "hotel_info")
	private String hotelInfo;

	public TourInfo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTravelInfo() {
		return travelInfo;
	}

	public void setTravelInfo(String travelInfo) {
		this.travelInfo = travelInfo;
	}

	public String getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo) {
		this.flightInfo = flightInfo;
	}

	public String getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	@Override
	public String toString() {
		return "[tour info name: " + name + ", namePlace: " + namePlace
				+ ", travelInfo: " + travelInfo + ", flightInfo: " + flightInfo
				+ ", hotelInfo: " + hotelInfo + "]";
	}

	public String getNamePlace() {
		return namePlace;
	}

	public void setNamePlace(String namePlace) {
		this.namePlace = namePlace;
	}

}
