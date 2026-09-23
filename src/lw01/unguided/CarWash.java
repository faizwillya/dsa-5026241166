package lw01.unguided;

public class CarWash extends WashService {

    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }

    public String label() {
        return "Car";
    }

    public int calculateCharge() {
        int days = getDays();

        if (days <= 3) {
            return (days * 35000) + 15000;
        }

        return (3 * 35000) + ((days - 3) * 25000) + 15000;
    }
}