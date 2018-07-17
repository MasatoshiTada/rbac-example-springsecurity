package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author kawasima
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long roleId;

    @NonNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ROLE_PERMISSION")
    private Set<Permission> permissions;

    @Override
    public int hashCode() {
        if (roleId != null) {
            return roleId.hashCode();
        } else if (name != null) {
            return name.hashCode();
        }

        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof Role))
            return false;

        Role anotherRole = (Role) another;

        return anotherRole.roleId != null && (anotherRole.roleId == this.roleId);
    }

    @Override
    public String toString() {
        return name;
    }
}
