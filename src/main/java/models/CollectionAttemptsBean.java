package models;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "attempts")
@ApplicationScoped
public class CollectionAttemptsBean implements Serializable {
    private final List<Attempt> attempts;
    private final LocalDateTime dateTime;

    private Attempt currentAttempt = new Attempt("-3", "0", "2");

    public CollectionAttemptsBean() {
        attempts = new ArrayList<>();
        dateTime = LocalDateTime.now();

//        add(new Attempt("2", "0", "2"));
//        add(new Attempt("0", "0", "2"));
//        add(new Attempt("-2", "2", "2"));
//        add(new Attempt("0.5", "-0.5", "2"));
//        add(new Attempt("-0.5", "0.5", "2"));
    }

    public void add() {
        currentAttempt.updateIsHIt();
        attempts.add(currentAttempt);
        currentAttempt = new Attempt(currentAttempt.getX(),
                currentAttempt.getY(), currentAttempt.getR());
    }

    public void clear() {
        attempts.clear();
    }

    public void addFromCanvas() {
        String strX = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String strY = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String strR = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");

        add(new Attempt(strX, strY, strR));
    }

    public void add(Attempt attempt) {
        attempts.add(attempt);
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Attempt getCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(Attempt currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionAttemptsBean that = (CollectionAttemptsBean) o;
        return Objects.equals(attempts, that.attempts) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempts, dateTime);
    }

    @Override
    public String toString() {
        return "CollectionAttempts{" +
                "attempts=" + attempts +
                ", dateTime=" + dateTime +
                '}';
    }
}
