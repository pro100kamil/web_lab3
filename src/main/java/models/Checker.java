package models;

public class Checker {
    public static boolean checkHit(double x, double y, int r) {
        if (x <= 0 && y >= 0 && x * x + y * y <=
                ((double) r / 2) * ((double) r / 2)) {  //вторая четверть
            return true;
        }
        if (x <= 0 && y <= 0 && x >= -r && y >= -r) {  //третья четверть
            return true;
        }
        if (x >= 0 && y <= 0 && y >= x - (double) r / 2) {  //четвёртая четверть
            return true;
        }
        return false;
    }
}
