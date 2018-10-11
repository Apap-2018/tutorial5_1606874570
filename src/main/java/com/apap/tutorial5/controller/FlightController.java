package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.service.FlightService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FlightController
 * @author debora
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		System.out.println("masuk " + licenseNumber);
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.GET)
    private String updateFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
        FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        return "update-flight";
    }

    @RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.POST)
    private String updateFlight(@PathVariable(value = "flightNumber") String flightNumber, @ModelAttribute FlightModel flight) {
        flightService.updateFlight(flightNumber, flight);
        return "update";
    }
    
    @RequestMapping(value= "/flight/view", method = RequestMethod.GET)
	private String viewFlight(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		if (flight!=null) {
			model.addAttribute("flight", flight);
			model.addAttribute("pilot", flight.getPilot());
			return "view-flight";
		}
		return "not-found";
	}
    
    @RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"addRow"})
	public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		if (pilot.getPilotFlight() == null) {
            pilot.setPilotFlight(new ArrayList<FlightModel>());
        }
		pilot.getPilotFlight().add(new FlightModel());
		
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute PilotModel pilot, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    pilot.getPilotFlight().remove(rowId.intValue());
	    
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
}
