package ru.nsu.ccfit.mikhalev.digital_library.model.role;

import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN,
    USER;
    private final Set<Role> children = new HashSet<>();


    static {
        ADMIN.children.add(USER);
    }

    public boolean includes(Role role) {
        return this.equals(role) || children.stream().anyMatch(r -> r.includes(role));
    }
}