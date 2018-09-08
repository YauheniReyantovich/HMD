package org.reyantovich.yauheni.model.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UserRoles {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_REGISTERED("ROLE_REGISTERED");

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
                .orElse(UserRoles.ROLE_REGISTERED /*default value*/);
    }
}
