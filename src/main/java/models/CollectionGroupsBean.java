package models;

import database.HibernateManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ManagedBean(name = "groups", eager=true)
@ApplicationScoped
public class CollectionGroupsBean {
    private final List<GroupOfPoints> groups;
    private final Map<String, GroupOfPoints> map = new TreeMap<>();
    private final HibernateManager hibernateManager;

    public CollectionGroupsBean() {
        hibernateManager = new HibernateManager();
        // чтобы он работал, не забывать прокидывать порт!!!

        groups = hibernateManager.getGroups();
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

    //TODO сделать очистку и брать из бд начальное состояние

    public Map<String, GroupOfPoints> getMap() {
        return map;
    }

    public List<GroupOfPoints> getGroups() {
        return groups;
    }
}
