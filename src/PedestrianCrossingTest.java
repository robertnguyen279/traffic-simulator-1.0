import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedestrianCrossingTest {
    Road road = new Road("0", 1, 5, new int[]{0, 0});
    PedestrianCrossing pedestrianCrossing = new PedestrianCrossing("0", 3, road);

    @Test
    void shouldReturnCorrectAttributes() {
        assertEquals("pedestrian_crossing_0", pedestrianCrossing.getId());
        assertEquals(3, pedestrianCrossing.getWalkedCycleTime());
        assertEquals(road, pedestrianCrossing.getRoadAttachedTo());
    }

    @Test
    void shouldBeFree() {
        assertEquals("free", pedestrianCrossing.getStatus());
    }

    @Test
    void shouldBeWalked() {
        for (int i = 0; i < 4; i++) {
            pedestrianCrossing.launch();
        }

        assertEquals("walked", pedestrianCrossing.getStatus());
    }

    @Test
    void shouldBeAtTheMiddleOfRoad() {
        assertEquals(2, pedestrianCrossing.getLocation()[0]);
    }
}
