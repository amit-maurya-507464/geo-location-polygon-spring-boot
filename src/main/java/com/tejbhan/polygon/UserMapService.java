package com.tejbhan.polygon;

import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapService {

    @Autowired
    private UserMapRepository userMapRepository;

    public UserMap saveUserMap(UserMap userMap) {
        return userMapRepository.save(userMap);
    }

    public List<UserMap> getAllUserMaps() {
        return userMapRepository.findAll();
    }

    public List<Long> getUserIdForLocation(Point location) {
        return userMapRepository.findUserIdsByLocation(location);
    }

}
