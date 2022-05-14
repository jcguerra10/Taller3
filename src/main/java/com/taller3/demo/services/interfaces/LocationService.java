package com.taller3.demo.services.interfaces;

import com.taller3.demo.model.prod.Location;

public interface LocationService {
	public Location saveLocation(Location loc);
	public Location editLocation(Location loc, Integer locId);
}
