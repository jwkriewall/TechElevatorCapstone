package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatCardDAO;
import com.techelevator.model.CatPic;

@Component
public class RestCatPicService implements CatPicService {
	
	public CatCardDAO catDao;

	@Override
	public CatPic getPic() {
		
		return catDao.get(id);
	}

}	
