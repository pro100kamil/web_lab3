package models;

import java.util.ArrayList;
import java.util.List;


public class GroupOfPoints {
    private final String name;
    private final List<Attempt> attempts = new ArrayList<>();

    public GroupOfPoints(String name) {
        this.name = name;
    }

    public void add(Attempt attempt) {
        attempts.add(attempt);
    }

    public String getName() {
        return name;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }
}
