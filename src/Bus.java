public class Bus extends Car {
    public Bus(String id) {
        this.id = ("bus_" + id);
        length = (int) (super.getLength() * 3);
    }
}
