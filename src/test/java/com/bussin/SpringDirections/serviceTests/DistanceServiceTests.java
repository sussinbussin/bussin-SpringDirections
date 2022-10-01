package com.bussin.SpringDirections.serviceTests;

import com.bussin.SpringDirections.services.DirectionsService;
import com.bussin.SpringDirections.testConfig.H2JpaConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {H2JpaConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DistanceServiceTests {
    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";

    @Autowired
    private DirectionsService directionsService;

    /**
     * Example call, not an actual test
     */
//    @Test
//    public void testDistance() {
//        System.out.println(directionsService.getDistanceBetween("556748", "188065"));
//    }
}
