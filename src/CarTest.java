import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    Road road = new Road("0", 1, 5, new int[]{0, 0});
    Road road2 = new Road("1", 1, 2, new int[]{5, 0});
    TrafficLight light = new TrafficLight("0", road, 5);
    PedestrianCrossing pedestrianCrossing = new PedestrianCrossing("0", 3, road);
    Car car = new Car("0", road);

    @Test
    void shouldHaveCorrectAttributes() {
        assertEquals("car_0", car.getId());
        assertEquals(road, car.getCurrentRoad());
        assertArrayEquals(new int[]{0, 0}, car.getPosition());
    }

    @Test
    void shouldMoveCorrectly() {
        car.move();
        assertEquals(1, car.getSpeed());
        assertArrayEquals(new int[]{1, 0}, car.getPosition());
    }

    @Test
    void shouldStopOnRed() {
        for (int i = 0; i < 7; i++) {
            light.operate();
            light.setStatus("red");
            car.move();
        }
        assertEquals(0, car.getSpeed());
    }

    @Test
    void shouldMoveOnGreen() {
        for (int i = 0; i < 5; i++) {
            light.operate();
            light.setStatus("green");
            car.move();
        }
        assertEquals(1, car.getSpeed());
    }

    @Test
    void shouldStopWhenPredestrianCrossingIsWalked() {

        for (int i = 0; i < 5; i++) {
            pedestrianCrossing.launch();
            pedestrianCrossing.setStatus("walked");
            car.move();
        }
        assertEquals(0, car.getSpeed());
    }

    @Test
    void shouldGoWhenPedestrianCrossingIsFree() {
        for (int i = 0; i < 5; i++) {
            pedestrianCrossing.launch();
            pedestrianCrossing.setStatus("free");
            car.move();
        }
        assertEquals(1, car.getSpeed());
    }

    @Test
    void shouldGoToRoadTwoWhenFinishedRoadOne() {
        ArrayList<Road> connectedRoads = new ArrayList<>();
        connectedRoads.add(road2);
        road.setConnectedRoads(connectedRoads);
        while (car.getPosition()[0] < road.getEndLocation()[0]) {
            light.operate();
            pedestrianCrossing.launch();
            car.move();
        }

        assertEquals("road_1", car.getCurrentRoad().getId());
        assertArrayEquals(new int[]{5, 0}, car.getPosition());
    }

}
