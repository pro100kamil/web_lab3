package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="hello")
@SessionScoped
public class HelloBean implements Serializable {
    private String name = "Kamil";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSayWelcome() {
        if ("".equals(name) || name == null) { //check if null?
            return "";
        } else {
            return "Ajax message : Welcome " + name;
        }
    }
}

