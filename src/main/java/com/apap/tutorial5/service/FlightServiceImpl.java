package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FlightServiceImpl
 * @author debora
 */
@Service
@Transactional

public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public void deleteFlight(long id) {
		FlightModel flight = flightDb.findById(id);
		flightDb.delete(flight);
	}
	
	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}

	@Override
	public void updateFlight(String flightNumber, FlightModel flight) {
		FlightModel oldFlight = this.getFlightDetailByFlightNumber(flightNumber);
		oldFlight.setDestination(flight.getDestination());
		oldFlight.setFlightNumber(flight.getFlightNumber());
		oldFlight.setOrigin(flight.getOrigin());
		oldFlight.setTime(flight.getTime());
	}
}
