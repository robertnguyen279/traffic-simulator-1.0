public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s
    private static final int NEXT_ROAD_INDEX = 0;
    String id; // unique identifier
    static float length; // number of segments occupied, 1 for ease of prototype.
    private static float breadth;
    private int speed; //segments moved per turn
    private int[] position; // position on current road
    private Road currentRoad; // current Road object


    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        this.currentRoad = currentRoad;
        length = 1; // cars made 1m long for prototype.
        breadth = (float) (length * 0.5);
        speed = 0;
        this.position = new int[]{0, 0};
        this.currentRoad.getCarsOnRoad().add(this); //add this car to the road its on.
    }

    public Car() {
        id = "000";
        length = 1;
        breadth = length * 0.5f;
        speed = 0;
        position = new int[]{1, 0};
    }

    // This method only works for horizontal roads
    public void move() {
        boolean isTrafficLightRedWhenCarCome = !this.currentRoad.getLightsOnRoad().isEmpty() && this.position[0] == this.currentRoad.getLightsOnRoad().get(0).getLocation()[0] - 1 && this.currentRoad.getLightsOnRoad().get(0).getStatus().equals("red");
        boolean isPedestrianWalkedWhenCarCome = (this.currentRoad.getPedestrianCrossing() != null) && this.currentRoad.getPedestrianCrossing().getLocation()[0] - 1 == this.position[0] && this.currentRoad.getPedestrianCrossing().getStatus().equals("walked"); // Only works for horizontal roads, vehicles must stop in front of crossing.
        if (isTrafficLightRedWhenCarCome || isPedestrianWalkedWhenCarCome) {
            this.speed = STOPPED;
        } else {
            this.speed = this.currentRoad.getSpeedLimit();
            if ((this.currentRoad.getEndLocation()[0] - 1 == this.getPosition()[0]) && !this.currentRoad.getConnectedRoads().isEmpty()) {
                this.currentRoad.getCarsOnRoad().remove(this);
                this.currentRoad = this.currentRoad.getConnectedRoads().get(NEXT_ROAD_INDEX);
                this.currentRoad.getCarsOnRoad().add(this);
                this.position = this.currentRoad.getStartLocation();
            } else if ((this.currentRoad.getEndLocation()[0] - 1 > this.getPosition()[0]) || (this.currentRoad.getConnectedRoads().isEmpty() && this.currentRoad.getEndLocation()[0] - 1 == this.getPosition()[0])) {
                this.position[0] = (this.position[0] + this.speed);
            } else {
                this.speed = STOPPED;
            }
        }


    }

    public void printCarStatus() {
        System.out.printf("%s going: %dm/s on %s at position: (%s, %s).%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getPosition()[0], this.getPosition()[1]);
    }

    public float getLength() {
        return length;
    }

    public void setLength(int length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        Car.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

