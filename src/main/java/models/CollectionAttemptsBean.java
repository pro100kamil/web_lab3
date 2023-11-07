package models;


import database.HibernateManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@MyBean
@ManagedBean(name = "attempts", eager = true)
@ApplicationScoped
public class CollectionAttemptsBean implements Serializable {
    //нужно использовать потокобезопасные коллекции,
    // потому что взаимодействия с коллекциями могут происходить в разных потоках.
    private final CopyOnWriteArrayList<Attempt> attempts;
    private final HibernateManager hibernateManager;

    @ManagedProperty(value = "#{groups}")
    private CollectionGroupsBean collectionGroupsBean;

    public void setCollectionGroupsBean(CollectionGroupsBean collectionGroupsBean) {
        this.collectionGroupsBean = collectionGroupsBean;
    }

    private Attempt currentAttempt = new Attempt("0", "0", "2");

    public CollectionAttemptsBean() {
        hibernateManager = new HibernateManager();
        // чтобы он работал, не забывать прокидывать порт!!!

        attempts = new CopyOnWriteArrayList<>(hibernateManager.getAttempts());
    }

    public void add(Attempt attempt) {
        attempts.add(attempt);

        String groupName = attempt.getGroup().getName();
        if (collectionGroupsBean.getMap().containsKey(groupName)) {
            collectionGroupsBean.getMap().get(groupName).add(attempt);

            attempt.setGroup(collectionGroupsBean.getMap().get(groupName));
        } else {
            collectionGroupsBean.add(attempt.getGroup());

            attempt.getGroup().add(attempt);
        }

        hibernateManager.addAttempt(attempt);
    }

    public void clear() {
        attempts.clear();

        hibernateManager.clearAttempts();

        collectionGroupsBean.clear();
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
        String groupName = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("groupName");

        Attempt attempt = new Attempt(strX, strY, strR);
        attempt.getGroup().setName(groupName);

        add(attempt);
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
        return Objects.equals(attempts, that.attempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempts);
    }

    @Override
    public String toString() {
        return "CollectionAttempts{" +
                "attempts=" + attempts +
                '}';
    }
}