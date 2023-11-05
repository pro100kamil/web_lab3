package models;

import database.HibernateManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ManagedBean(name = "groups", eager=true)
@ApplicationScoped
public class CollectionGroupsBean {
    private final List<GroupOfPoints> groups = new ArrayList<>();
    private final Map<String, GroupOfPoints> map = new TreeMap<>();
    private final HibernateManager hibernateManager = new HibernateManager();

    public CollectionGroupsBean() {
//        GroupOfPoints group = new GroupOfPoints("first group");
//        group.add(new Attempt("3", "1", "2"));
//        group.add(new Attempt("3", "-1", "2"));
//        add(group);
//
//        add(new GroupOfPoints("second group"));
    }

    public void add(GroupOfPoints group) {
        groups.add(group);
        map.put(group.getName(), group);

        hibernateManager.addGroup(group);
    }

    public Map<String, GroupOfPoints> getMap() {
        return map;
    }

    public List<GroupOfPoints> getGroups() {
        return groups;
    }
}
