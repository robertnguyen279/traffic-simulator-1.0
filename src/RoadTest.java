import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RoadTest {
    Road road = new Road("0", 1, 5, new int[]{0, 0});
    Road road2 = new Road("1", 1, 2, new int[]{5, 2});
    TrafficLight light = new TrafficLight("0", road, 5);
    PedestrianCrossing pedestrianCrossing = new PedestrianCrossing("0", 3, road);
    Car car = new Car("0", road);

    @Test
    void shouldReturnCorrectAttributes () {
        assertEquals("road_0", road.getId());
        assertEquals(1, road.getSpeedLimit());
        assertEquals(5, road.getLength());
        assertArrayEquals(new int[]{0, 0}, road.getStartLocation());
        assertArrayEquals(new int[]{road.getStartLocation()[0] + road.getLength(), 0}, road.getEndLocation());
    }

    @Test
    void shouldReturnCarsOnRoad() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);
        road.setCarsOnRoad(cars);
        assertEquals(cars, road.getCarsOnRoad());
    }

    @Test
    void shouldReturnTrafficLights() {
        ArrayList<TrafficLight> trafficLights = new ArrayList<>();
        trafficLights.add(light);
        road.setLightsOnRoad(trafficLights);
        assertEquals(trafficLights, road.getLightsOnRoad());
    }

    @Test
    void shouldReturnPedestrianCrossing() {
        road.setPedestrianCrossing(pedestrianCrossing);
        assertEquals(pedestrianCrossing, road.getPedestrianCrossing());
    }

    @Test
    void shouldReturnConnectedRoads() {
        ArrayList<Road> connectedRoads = new ArrayList<>();
        connectedRoads.add(road2);
        road.setConnectedRoads(connectedRoads);
        assertEquals(connectedRoads, road.getConnectedRoads());
    }
}
