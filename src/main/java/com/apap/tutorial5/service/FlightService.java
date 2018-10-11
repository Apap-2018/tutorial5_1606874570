package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

/**
 * FlightService
 * @author debora
 */

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(long id);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	void updateFlight(String flightNumber, FlightModel flight);
}
