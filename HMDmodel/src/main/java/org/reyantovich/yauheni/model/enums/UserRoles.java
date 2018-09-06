package org.reyantovich.yauheni.enums;

public enum UserRoles {

    ADMIN("Admin"),
    REGISTERED("Registered"),
    UNREGISTERED("Unregistered");

    private String role;

    private UserRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return role;
    }
}
