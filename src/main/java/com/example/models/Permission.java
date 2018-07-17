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
public class Permission {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @NonNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST)
    private Set<Role> roles;

    @Override
    public int hashCode() {
        if (permissionId != null) {
            return permissionId.hashCode();
        } else if (name != null) {
            return name.hashCode();
        }

        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof Permission))
            return false;

        Permission anotherPermission = (Permission) another;

        return (anotherPermission.permissionId != null && (anotherPermission.permissionId == this.permissionId))
                || (anotherPermission.permissionId == null && anotherPermission.name != null && (anotherPermission.name.equals(this.name)));
    }

}
