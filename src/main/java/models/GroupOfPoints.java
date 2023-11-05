package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupOfPoints implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable = false)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Transient
    private final List<Attempt> attempts = new ArrayList<>();

    @Transient
    private final static GroupOfPoints defaultGroup = new GroupOfPoints("default group");

    public GroupOfPoints() {

    }

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

    public static GroupOfPoints getDefaultGroup() {
        return defaultGroup;
    }
}
