import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficLightTest {
    Road road = new Road("0", 1, 5, new int[]{0, 0});
    TrafficLight light = new TrafficLight("0", road, 5);

    @Test
    void shouldReturnCorrectAttributes() {
        assertEquals("light_0", light.getId());
        assertEquals(5, light.getSignalCycleTime());
        assertEquals(road, light.getRoadAttachedTo());
    }

    @Test
    void shouldReturnGreenStatus() {
        light.operate();
        assertEquals("green", light.getStatus());
    }

    @Test
    void shouldReturnRedStatus() {
        for (int i = 0; i < 6; i++) {
            light.operate();
        }
        assertEquals("red", light.getStatus());
    }

    @Test
    void shouldBeAtTheEndOfRoad() {
        assertEquals(5, light.getLocation()[0]);
    }
}
