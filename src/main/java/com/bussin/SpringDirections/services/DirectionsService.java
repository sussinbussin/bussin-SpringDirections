package com.bussin.SpringDirections.services;

import com.bussin.SpringDirections.exception.CannotConnectException;
import com.bussin.SpringDirections.models.DistanceResult;
import com.bussin.SpringDirections.repositories.PlannedRouteRepository;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DirectionsService {
    private final PlannedRouteRepository plannedRouteRepository;
    private static final String DISTANCE_URL = "https://maps.googleapis" +
            ".com/maps/api/distancematrix/json";


    private GeoApiContext geoApiContext;

    @Autowired
    private void setGeoApiContext(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    @Autowired
    public DirectionsService(PlannedRouteRepository plannedRouteRepository) {
        this.plannedRouteRepository = plannedRouteRepository;
    }

//    public Optional<PlannedRoute> computeInBetweenRoutes(String plannedFrom, String plannedTo){
//        final DirectionsResult directionsResult;
//
//        DirectionsApiRequest req = DirectionsApi.getDirections(context, plannedFrom, plannedTo);
//        LocalDateTime instant = LocalDateTime.now();
//        try {
//            directionsResult = req
//            .mode(TravelMode.WALKING)
//            //inbetweens are all walking for now
//            .departureTimeNow()
//            .await();
//            if (directionsResult.routes != null){
//                 PlannedRoute plannedRoute = new PlannedRouteBuilder()
//                 //.addParameter("UUID", UUID)
//                 .addParameter("plannedFrom", plannedFrom)
//                 .addParameter("plannedTo", plannedTo)
//                 .addParameter("dateTime", instant)
//                 .addParameter("directionsRoutes", directionsResult.routes)
//                final PlannedRoute plannedRoute = new PlannedRoute(/*how get UUID */null, plannedFrom, plannedTo, instant, directionsResult.routes)
//                return Optional.of(plannedRoute);
//            }
//        } catch (ApiException e){
//            e.printStackTrace();
//            return null;
//        }     catch (InterruptedException e){
//            e.printStackTrace();
//            return null;
//        } catch (IOException e){
//            e.printStackTrace();
//            return null;
//        }
//        return Optional.empty();
//    }

    public DistanceResult getDistanceBetween(String plannedFrom,
                                      String plannedTo) {
        /**
         * We know:
         * Google maps API returns distance and duration in integer (Nice)
         * https://developers.google.com/maps/documentation/distance-matrix/start
         */

        /**
         * Call the API, extract the distance and duration
         */
        try{
            DistanceMatrix distanceMatrix =
                    DistanceMatrixApi.getDistanceMatrix(geoApiContext,
                            new String[]{plannedFrom},
                            new String[]{plannedTo}).await();

            return new DistanceResult(plannedFrom, plannedTo,
                    distanceMatrix.rows[0].elements[0].distance.inMeters,
                    distanceMatrix.rows[0].elements[0].duration.inSeconds);
        } catch (IOException | InterruptedException | ApiException e) {
            throw new CannotConnectException(e);
        }

    }
}
