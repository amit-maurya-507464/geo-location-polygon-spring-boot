package com.tejbhan.polygon;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMapRepository extends JpaRepository<UserMap, Long> {

    @Query("SELECT h.userId FROM UserMap h WHERE ST_Contains(h.serviceArea, :location) = true")
    List<Long> findUserIdsByLocation(@Param("location") Point location);

}
