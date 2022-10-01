package com.bussin.SpringDirections.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bussin.SpringDirections.models.PlannedRoute;

@Repository
public interface PlannedRouteRepository
        extends JpaRepository<PlannedRoute, UUID> {

    /**
     * Probably shouldn't be here
     */
//    Optional<PlannedRoute> computeInBetweenRoutes(String plannedFrom, String plannedTo);

    /**
     * Get all planned routes after the date and time specified.
     * Easiest to call with LocalDateTime.now() to get only planned routes in
     * the future
     * @param localDateTime Local date time to get results after
     * @return Planned Routes after the specified time
     */
    List<PlannedRoute> getPlannedRouteByDateTimeAfter(LocalDateTime localDateTime);
}
