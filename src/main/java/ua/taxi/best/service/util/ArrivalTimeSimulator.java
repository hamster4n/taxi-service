package ua.taxi.best.service.util;

import java.util.Random;

final class ArrivalTimeSimulator {

    private ArrivalTimeSimulator() {
    }

    /**
     * @return generates the time for which the machine can drive to the client
     * Subsequently, the time will be calculated from the current coordinates of not occupied cars.
     */
    static int generateArrivalTime(){
        Random random = new Random();
        return  5 + random.nextInt(25 - 5);
    }
}
