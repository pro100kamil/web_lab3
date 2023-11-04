package models;


import database.HibernateManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "attempts", eager=true)
@ApplicationScoped
public class CollectionAttemptsBean implements Serializable {
    private final List<Attempt> attempts;
    private final LocalDateTime dateTime;
    private final HibernateManager hibernateManager;

    private Attempt currentAttempt = new Attempt("0", "0", "2");

    public CollectionAttemptsBean() {
        hibernateManager = new HibernateManager();

//        attempts = new ArrayList<>();
        attempts = hibernateManager.getAttempts();
        dateTime = LocalDateTime.now();
    }

    public void add(Attempt attempt) {
        attempts.add(attempt);

        hibernateManager.addAttempt(attempt);
    }

    public void clear() {
        attempts.clear();

        hibernateManager.clearAttempts();
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void addFromForm() {
        currentAttempt.updateIsHIt();
        add(currentAttempt);
        currentAttempt = new Attempt(currentAttempt.getX(),
                currentAttempt.getY(), currentAttempt.getR());
    }

    public void addFromCanvas() {
        String strX = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String strY = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String strR = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");

        add(new Attempt(strX, strY, strR));
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