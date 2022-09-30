package com.bussin.SpringDirections.controllers;

import com.bussin.SpringDirections.models.PlannedRoute;
import com.bussin.SpringDirections.services.PlannedRouteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planned")
public class PlannedRouteController {
    private final PlannedRouteService plannedRouteService;

    @Autowired
    public PlannedRouteController(PlannedRouteService plannedRouteService) {
        this.plannedRouteService = plannedRouteService;
    }

    // @Operation(summary = "Gets distance between two addresses")
    // @GetMapping("/distance")
    // public BigDecimal getDistanceBetween(@Pattern(regexp = "^[0-9]{6}$") @PathVariable String tripStart,
    //                                      @Pattern(regexp = "^[0-9]{6}$") @PathVariable String tripEnd) {
    //     return plannedRouteService.getDistanceBetween(tripStart, tripEnd);
    // }

    // @Operation(summary = "Gets best planned routes given a start and " +
    //         "destination")
    // @GetMapping("/suggestion")
    // public List<PlannedRoute> getSuggestedRoutes(@RequestParam String tripStart,
    //                                              @RequestParam String tripEnd) {
    //     return plannedRouteService.getSuggestedRoutes(tripStart, tripEnd);
    // }

}
