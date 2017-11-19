package pro.lukasgorny.model;

import java.util.Set;
import javax.persistence.*;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@Entity
@Table(name = "roles")
public class Role extends Model {
    private String name;
    private Set<User> users;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
