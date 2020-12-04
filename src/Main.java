import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner simController = new Scanner(System.in);

        // set default value for prototype
        int roadSpawns = 2;
        int carSpawns = 1;
        int lightSpawns = 1;
        int pedestrianCrossingSpawns = 1;
        int signalCycleTime = 5;
        int walkedCycleTime = 3;

        // Create objects:
        System.out.println("Object Creation:\n------------------");
        System.out.println("Roads:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            System.out.println("Length:");
            int lengthInput = simController.nextInt();
            int speedLimitInput = 1; // set speed limit to be 1 for prototype
            int[] startLocation = i != 0 ? new int[]{roads.get(roads.size() - 1).getEndLocation()[0], 0} : new int[]{0, 0}; // Only works for horizontal roads
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, startLocation));
        }

        System.out.println("\nRoads:");

        for (Road road: roads) {
            road.printRoadInfo();
        }

        System.out.println("\nCars:");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0), signalCycleTime)); // all created lights will begin on road_0.
            lights.get(i).printLightStatus();
        }
        System.out.println();

        System.out.println("\nPedestrian Crossing:");
        ArrayList<PedestrianCrossing> pedestrianCrossings = new ArrayList<>();
        for (int i = 0; i < pedestrianCrossingSpawns; i++) {
            pedestrianCrossings.add(new PedestrianCrossing(Integer.toString(i), walkedCycleTime, roads.get(0))); // all created lights will begin on road_0.
            pedestrianCrossings.get(i).printStatus();
        }
        System.out.println();

        // set locations and connections:
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1

        //Simulation loop:
        System.out.println("Simulation:");
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = simController.nextInt();
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate();
                light.printLightStatus();
            }

            for (PedestrianCrossing pedestrianCrossing: pedestrianCrossings) {
                pedestrianCrossing.launch();
                pedestrianCrossing.printStatus();
            }

            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
