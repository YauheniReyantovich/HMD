package org.reyantovich.yauheni.model.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UserRoles {

    ADMIN("Admin"),
    REGISTERED("Registered"),
    UNREGISTERED("Unregistered");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return role;
    }

    public static UserRoles get(String url) {
        return Arrays.stream(values())
                .filter(env -> env.role.equals(url))
                .findFirst()
                .orElse(UserRoles.UNREGISTERED /*default value*/);
    }
}
