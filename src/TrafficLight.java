public class TrafficLight {
    private static final String GREEN = "green";
    private static final String RED = "red";
    private int[] location;
    private Road roadAttachedTo;
    private String id;
    private String status;
    private int signalCycleTime;
    private int countDown;

    public TrafficLight(String id, Road road, int signalCycleTime) {
        this.id = "light_" + id;
        this.signalCycleTime = signalCycleTime;
        countDown = signalCycleTime;
        status = GREEN;
        int length = 1;
        this.roadAttachedTo = road;
        this.location = new int[]{this.roadAttachedTo.getLength(), 0}; // always places the traffic light at the end of the roadAttachedTo.
        this.roadAttachedTo.getLightsOnRoad().add(this); // adds this light to the road it belongs to.
    }

    public void operate() {
        if (countDown == 0) {
            countDown = signalCycleTime;
            setStatus(status.equals(RED) ? GREEN : RED);
        }
        countDown--;
    }

    public void printLightStatus() {
        System.out.printf("%s is %ss left in %s on %s at location (%s, %s).%n", this.getId(), this.countDown, this.getStatus(), this.getRoadAttachedTo().getId(), this.location[0], this.location[1]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSignalCycleTime() {
        return signalCycleTime;
    }

    public void setSignalCycleTime(int signalCycleTime) {
        this.signalCycleTime = signalCycleTime;
    }

    public Road getRoadAttachedTo () {
        return this.roadAttachedTo;
    }

    public int[] getLocation() {
        return this.location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void setRoadAttachedTo (Road road) {
        this.roadAttachedTo = road;
    }
}
