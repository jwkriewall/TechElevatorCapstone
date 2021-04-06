package com.techelevator.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.OrganizerDAO;
import com.techelevator.model.Organizer;
import com.techelevator.model.User;


@CrossOrigin
@RestController
public class OrganizerController {
	
	private OrganizerDAO organizerDAO;
	
	OrganizerController(OrganizerDAO organizerDAO){
		this.organizerDAO = organizerDAO;
	};
	
<<<<<<< HEAD
	@RequestMapping(path="/organizers", method = RequestMethod.GET)
	public Organizer getOrganizerInfoByUserId(@RequestBody User user) {
		return organizerDAO.getOrganizerInfoByUserId( Math.toIntExact(user.getId()) );
=======
	@RequestMapping(path="/organizers/{organizerId}", method = RequestMethod.GET)
	public Organizer getOrganizerInfoByOrganizerId(@PathVariable Integer organizerId) {
		return organizerDAO.getOrganizerInfoByOrganizerId(organizerId);
		
	}
	
	@RequestMapping(path="/organizers/info/{userId}", method = RequestMethod.GET)
	public Organizer getOrganizerInfoByUserId(@PathVariable Integer userId) {
		return organizerDAO.getOrganizerInfoByUserId(userId);
>>>>>>> 1d60a0519f6d090aa2cd4e29b320e0f7c97e9dc5
	}
	
	@RequestMapping(path="/organizers", method=RequestMethod.POST)
	public Organizer createNewOrganizer(@RequestBody Organizer organizer) {
		return organizerDAO.createNewOrganizer(organizer);
	}
	
<<<<<<< HEAD
	@RequestMapping(path="/organizers/{organizerId}", method = RequestMethod.GET)
	public Organizer getOrganizerInfoByOrganizerId(@PathVariable Integer organizerId) {
		return organizerDAO.getOrganizerInfoByOrganizerId(organizerId);
		
=======
	@RequestMapping(path="/organizers{organizerId}", method = RequestMethod.PUT)
	public void updateOrganizerInfo(@PathVariable Integer organizerId, Principal principal) {
		organizerDAO.updateOrganizerInfo(principal.getName(), organizerId);
	}
	
	@RequestMapping(path="/organizers{organizerId}", method = RequestMethod.DELETE)
	public void deleteOrganizer(@PathVariable Integer organizerId, Principal principal) {
		organizerDAO.deleteOrganizer(principal.getName(), organizerId);
>>>>>>> 1d60a0519f6d090aa2cd4e29b320e0f7c97e9dc5
	}

}
