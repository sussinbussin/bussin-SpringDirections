package com.bussin.SpringDirections.services;

import com.bussin.SpringDirections.repositories.PlannedRouteRepository;
import com.bussin.SpringDirections.models.PlannedRoute;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.Optional;

@Service
@Slf4j
public class PlannedRouteService {
    private GeoApiContext context;
    private final PlannedRouteRepository plannedRouteRepository;

    @Autowired
    public PlannedRouteService(@Value("${gmapsapiKey}") String apiKey,
            PlannedRouteRepository plannedRouteRepository) {
        context = new GeoApiContext.Builder().apiKey(apiKey)
                .maxRetries(2)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .build();
        this.plannedRouteRepository = plannedRouteRepository;
    }

    public Optional<PlannedRoute> computeInBetweenRoutes(String plannedFrom, String plannedTo){
        final DirectionsResult directionsResult;

        DirectionsApiRequest req = DirectionsApi.getDirections(context, plannedFrom, plannedTo);
        LocalDateTime instant = LocalDateTime.now();
        try {
            directionsResult = req
            .mode(TravelMode.WALKING)
            //inbetweens are all walking for now
            .departureTimeNow()
            .await();
            if (directionsResult.routes != null){
                // PlannedRoute plannedRoute = new PlannedRouteBuilder()
                // //.addParameter("UUID", UUID)
                // .addParameter("plannedFrom", plannedFrom)
                // .addParameter("plannedTo", plannedTo)
                // .addParameter("dateTime", instant)
                // .addParameter("directionsRoutes", directionsResult.routes)
                final PlannedRoute plannedRoute = new PlannedRoute(/*how get UUID */null, plannedFrom, plannedTo, instant, directionsResult.routes)
                return Optional.of(plannedRoute);
            }
        } catch (ApiException e){
            e.printStackTrace();
            return null;
        }     catch (InterruptedException e){
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return Optional.empty();

    }

}
