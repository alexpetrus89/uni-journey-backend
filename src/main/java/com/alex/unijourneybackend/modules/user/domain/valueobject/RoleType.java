package com.alex.unijourneybackend.modules.user.domain.valueobject;

import java.util.Set;

public enum RoleType {

    ADMIN(Set.of(
        Permission.USER_READ,
        Permission.USER_WRITE,
        Permission.USER_DELETE,
        Permission.ADMIN_DISABLE,
        Permission.ADMIN_DELETE
    )),

    STUDENT(Set.of(
        Permission.USER_READ,
        Permission.STUDENT_ENROLL
    )),

    PROFESSOR(Set.of(
        Permission.USER_READ,
        Permission.PROFESSOR_GRADE
    ));

    private final Set<Permission> permissions;

    RoleType(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> permissions() {
        return permissions;
    }


}
