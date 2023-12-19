package com.tejbhan.polygon;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserMapController {

    @Autowired
    private UserMapService userMapService;

    @PostMapping
    public ResponseEntity<List<List<Double>>> createUserMap(@RequestBody UserMapDto dto) {
        GeometryFactory geometryFactory = new GeometryFactory();
        ServiceAreaDto serviceAreaDto = dto.getServiceArea();

        Polygon serviceArea = geometryFactory.createPolygon(
                serviceAreaDto.getCoordinates().stream()
                        .map(cord -> new Coordinate(cord.get(0), cord.get(1)))
                        .toArray(Coordinate[]::new)
        );

        UserMap userMap = new UserMap();
        userMap.setName(dto.getName());
        userMap.setServiceArea(serviceArea);
        userMap.setUserId(dto.getUserId());

        UserMap savedUserMap = userMapService.saveUserMap(userMap);

        List<List<Double>> coordinates = new ArrayList<>();
        for (Coordinate coordinate : savedUserMap.getServiceArea().getCoordinates()) {
            coordinates.add(Arrays.asList(coordinate.getX(), coordinate.getY()));
        }
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ServiceAreaDto>> getAllUserMaps() {
        List<UserMap> userMaps = userMapService.getAllUserMaps();
        List<ServiceAreaDto> serviceAreas = userMaps.stream()
                .map(userMap -> {
                    Polygon polygon = userMap.getServiceArea();
                    List<List<Double>> coordinates = new ArrayList<>();
                    for (Coordinate coordinate : polygon.getCoordinates()) {
                        coordinates.add(Arrays.asList(coordinate.getX(), coordinate.getY()));
                    }
                    return new ServiceAreaDto(coordinates);
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceAreas, HttpStatus.OK);
    }

    @GetMapping("/check-location")
    public ResponseEntity<List<Long>> getUserIdForLocation(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        GeometryFactory geometryFactory = new GeometryFactory();
        Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        List<Long> userIds = userMapService.getUserIdForLocation(location);
        return new ResponseEntity<>(userIds, HttpStatus.OK);
    }

}
