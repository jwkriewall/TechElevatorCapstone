package com.techelevator.dao;

import com.techelevator.model.Organizer;

public interface OrganizerDAO {
	
	public Organizer getOrganizerInfoByUserId (int userId);
	public Organizer getOrganizerInfoByOrganizerId (int organizerId);
}
