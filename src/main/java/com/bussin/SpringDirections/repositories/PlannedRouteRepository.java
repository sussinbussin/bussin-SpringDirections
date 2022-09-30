package com.bussin.SpringDirections.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bussin.SpringDirections.models.PlannedRoute;

@Repository
public interface PlannedRouteRepository
        extends JpaRepository<PlannedRoute, UUID> {

    Optional<PlannedRoute> computeInBetweenRoutes(String plannedFrom, String plannedTo);
}
