package models;

import database.HibernateManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@MyBean
@ManagedBean(name = "groups", eager = true)
@ApplicationScoped
public class CollectionGroupsBean {
    //нужно использовать потокобезопасные коллекции,
    // потому что взаимодействия с коллекциями могут происходить в разных потоках.
    private final CopyOnWriteArrayList<GroupOfPoints> groups;
//    private final Map<String, GroupOfPoints> map = new TreeMap<>();
    private final ConcurrentMap<String, GroupOfPoints> map = new ConcurrentHashMap<>();
    private final HibernateManager hibernateManager;

    public CollectionGroupsBean() {
        hibernateManager = new HibernateManager();
        // чтобы он работал, не забывать прокидывать порт!!!

        groups = new CopyOnWriteArrayList<>(hibernateManager.getGroups());
        for (GroupOfPoints group : groups) {
            map.put(group.getName(), group);
        }
    }

    public void add(GroupOfPoints group) {
        groups.add(group);
        map.put(group.getName(), group);

        hibernateManager.addGroup(group);
    }

    public void clear() {
        map.clear();
        groups.clear();

        hibernateManager.clearGroups();
    }

    public Map<String, GroupOfPoints> getMap() {
        return map;
    }

    public List<GroupOfPoints> getGroups() {
        return groups;
    }
}
