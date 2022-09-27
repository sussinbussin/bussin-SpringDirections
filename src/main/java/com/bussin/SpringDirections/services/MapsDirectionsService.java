package com.bussin.SpringDirections.services;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MapsDirectionsService {

    private GeoApiContext context;

    @Autowired
    public MapsDirectionsService(@Value("${gmapsapiKey}") String apiKey) {
        context = new GeoApiContext.Builder().apiKey(apiKey)
                .maxRetries(2)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    private DirectionsRoute[] getRoutes(String origin, String destination){
        final DirectionsResult result;

        //DirectionsApiRequest req = DirectionsApi.getDirections(context, origin, destination);
        DirectionsApiRequest req = DirectionsApi.newRequest(context);
        try{
            result = req
            .mode(null)
            //mode is default driving
            .origin(origin)
            .destination(destination)
            .departureTimeNow()
            .await();
            return result.routes;
        } catch (ApiException e){
            e.printStackTrace();
            return null;
        } catch (InterruptedException e){
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    

   
    
}

