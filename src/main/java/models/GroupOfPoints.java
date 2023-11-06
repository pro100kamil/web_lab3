package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupOfPoints implements Serializable {
    //    create sequence groups_sequence start 1 increment 2;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_generator")
    @SequenceGenerator(name = "groups_generator", sequenceName = "groups_sequence", allocationSize = 1)
    private int id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<Attempt> attempts = new ArrayList<>();

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

    public void setName(String name) {
        this.name = name;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }
}
