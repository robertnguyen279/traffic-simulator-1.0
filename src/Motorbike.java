public class Motorbike extends Car {
    public Motorbike(String id) {
        this.id = ("bike_" + id);
        length = (int) (super.getLength() * 0.5f);
    }
}