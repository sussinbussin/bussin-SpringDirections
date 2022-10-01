package com.bussin.SpringDirections.controllers;

import com.bussin.SpringDirections.models.DistanceResult;
import com.bussin.SpringDirections.services.DirectionsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/directions")
public class DirectionsController {
    private final DirectionsService directionsService;

    @Autowired
    public DirectionsController(DirectionsService directionsService) {
        this.directionsService = directionsService;
    }

     @Operation(summary = "Gets distance between two addresses")
     @GetMapping("/distance")
     public DistanceResult getDistanceBetween(@Pattern(regexp = "^[0-9]{6}$") @PathVariable String tripStart,
                                              @Pattern(regexp = "^[0-9]{6}$") @PathVariable String tripEnd) {
         return directionsService.getDistanceBetween(tripStart, tripEnd);
     }

//     @Operation(summary = "Gets best planned routes given a start and " +
//             "destination")
//     @GetMapping("/suggestion")
//     public List<PlannedRoute> getSuggestedRoutes(@RequestParam String tripStart,
//                                                  @RequestParam String tripEnd) {
//         return plannedRouteService.getSuggestedRoutes(tripStart, tripEnd);
//     }
}
