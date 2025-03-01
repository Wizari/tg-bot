package com.gmail.wizaripost.tgbot.db.services;


import com.gmail.wizaripost.tgbot.db.repository.LocationRepository;
import com.gmail.wizaripost.tgbot.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

//    public void deleteLocation(Location location) {
//        locationRepository.delete(location);
//    }
}
