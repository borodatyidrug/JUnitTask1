package borodatyidrug.lifttest;

public class Movement {
    
    private final int TIME_INCREMENT = 5;
    private static final int MAX_FLOOR = 25;
    
    private final int departureFloor;
    private final int destinationFloor;
    private int travelTime;

    private Movement(int departureFloor, int destinationFloor) {
        this.departureFloor = departureFloor;
        this.destinationFloor = destinationFloor;
    }
    
    public static Movement of(int departureFloor, int destinationFloor) {
        if (departureFloor > 0 && departureFloor <= MAX_FLOOR
                && destinationFloor > 0 && destinationFloor <= MAX_FLOOR
                && departureFloor != destinationFloor) {
            return new Movement(departureFloor, destinationFloor);
        } else {
            throw new IllegalArgumentException("Вы ввели несуществующий этаж, "
                    + "или пытаетесь отправиться на тот же этаж, на котором "
                    + "сейчас находится лифт!");
        }
    }
    
    public int getTravelTime() {
        return this.travelTime = TIME_INCREMENT * Math.abs(destinationFloor
                - departureFloor);
    }
    
    public int stoppedAt() {
        return destinationFloor;
    }
    
    @Override
    public String toString() {
        return "Этаж №" + departureFloor + " -> Этаж №" + destinationFloor;
    }
}
