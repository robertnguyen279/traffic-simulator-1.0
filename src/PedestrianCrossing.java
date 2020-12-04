public class PedestrianCrossing {
    private String id;
    private int[] location;
    private int walkedCycleTime;
    private int countdown;
    private final Road roadAttachedTo;
    private String status;

    public PedestrianCrossing(String id, int walkedCycleTime, Road roadAttachedTo) {
        this.id = "pedestrian_crossing_" + id;
        this.roadAttachedTo = roadAttachedTo;
        this.walkedCycleTime = walkedCycleTime;
        countdown = walkedCycleTime;
        this.location = new int[]{(roadAttachedTo.getEndLocation()[0] - roadAttachedTo.getStartLocation()[0]) / 2, (roadAttachedTo.getEndLocation()[1] - roadAttachedTo.getStartLocation()[1]) / 2}; // Always place pedestrian crossing at middle of the road.
        status = "free";
        this.roadAttachedTo.setPredestrianCrossing(this);
    }

    public void launch() {
        if (countdown == 0) {
            setStatus(status.equals("free") ? "walked" : "free");
            countdown = walkedCycleTime;
        }
        countdown--;
    }

    public void printStatus() {
        System.out.printf("%s is %s left in %s on %s at position (%s, %s).%n", this.getId(), this.countdown ,this.status, this.getRoadAttachedTo().getId(), this.location[0], this.location[1]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public int getWalkedCycleTime() {
        return walkedCycleTime;
    }

    public void setWalkedCycleTime(int walkedCycleTime) {
        this.walkedCycleTime = walkedCycleTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }
}
