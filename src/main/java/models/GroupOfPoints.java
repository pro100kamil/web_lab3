package models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class GroupOfPoints implements Serializable {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(generator = "groupSequence")
    @GenericGenerator(
            name = "groupSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "group_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "2")
            }
    )
// nextId = curId + 2
    @Column(name="id", unique=true, nullable = false)
    private int id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private final List<Attempt> attempts = new ArrayList<>();

//    @Transient
//    private final static GroupOfPoints defaultGroup = new GroupOfPoints("default group");

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

//    public static GroupOfPoints getDefaultGroup() {
//        return defaultGroup;
//    }
}
