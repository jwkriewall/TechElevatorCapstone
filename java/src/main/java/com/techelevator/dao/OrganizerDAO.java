package com.techelevator.dao;

import java.security.Principal;

import com.techelevator.model.Organizer;

public interface OrganizerDAO {
	
	public Organizer getOrganizerInfoByUserId (int userId);
	public Organizer getOrganizerInfoByOrganizerId (int organizerId);
	public Organizer createNewOrganizer(Organizer organizer);
	public void updateOrganizerInfo(String username, int organizerId);
	public void deleteOrganizer(String username, int organizerId);
}
