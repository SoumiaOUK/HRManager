package com.HRManager.g01.enums;

public enum TaskPriority {
    HIGHT("Hight"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String label;

    TaskPriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
