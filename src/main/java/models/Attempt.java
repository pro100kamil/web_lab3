package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Attempt implements Serializable {
    private double x;
    private double y;
    private int r;
    private boolean isHit;
    private LocalDateTime dateTime;

    public Attempt() {
        super();
    }

    public Attempt(String x, String y, String r) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.r = Integer.parseInt(r);

        dateTime = LocalDateTime.now();
        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    public Attempt(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;

        dateTime = LocalDateTime.now();
        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void updateIsHIt() {
        isHit = Checker.checkHit(this.x, this.y, this.r);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return Double.compare(attempt.x, x) == 0 && Double.compare(attempt.y, y) == 0 && r == attempt.r && isHit == attempt.isHit && Objects.equals(dateTime, attempt.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, isHit, dateTime);
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                ", dateTime=" + dateTime +
                '}';
    }
}
