package com.HRManager.g01.enums;

public enum TaskStatus {
    ASSIGNED("Assigned"),
    COMPLETED("Completed"),
    INPROGRESS("In Progress"),
    APPROUVED("APPROUVED"),
    CANCELED("CANCELED");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


