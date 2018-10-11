package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

/**
 * PilotService
 * @author debora
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);

	void addPilot(PilotModel pilot);
	void deletePilot(PilotModel pilot);
	void updatePilot(String licenseNumber, PilotModel pilot);
}
