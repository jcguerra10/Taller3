package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Location;

public interface LocationService {
	public void saveLocation(Location loc);
	public void editLocation(Location loc, Integer locId);
}
