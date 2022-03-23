package com.company.task02.entity;

public enum Group {

    ANTIBIOTICS("Antibiotics"),
    PAINKILLERS("Painkillers"),
    VITAMINS("Vitamins"),
    ANTIVIRAL("Antiviral"),
    ANTIHISTAMINES("Antihistamines"),
    GROUP_REQUIRED ("Group required");

    private String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Group getGroup(String name) {
        for (Group group : Group.values()) {
            if (name.equalsIgnoreCase(group.getName())) {
                return group;
            }
        }
        throw new IllegalArgumentException("no suitable group found for name " + name);// todo
    }
}
