package lw01.unguided;

public class MotorcycleWash extends WashService {

    public MotorcycleWash(String id, int days, int units) {
        super(id, days, units);
    }

    public String label() {
        return "Motorcycle";
    }

    public int calculateCharge() {
        return (getDays() * 15000) + 5000;
    }
}
