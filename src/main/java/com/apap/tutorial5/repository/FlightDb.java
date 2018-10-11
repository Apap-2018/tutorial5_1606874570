package com.apap.tutorial5.repository;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightDb
 * @author debora
 */
public interface FlightDb extends JpaRepository<FlightModel, Long> {
	FlightModel findByFlightNumber(String flightNumber);
	FlightModel findById(long id);

}
