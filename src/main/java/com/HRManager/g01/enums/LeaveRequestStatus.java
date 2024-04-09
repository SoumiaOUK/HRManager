package com.HRManager.g01.enums;

public enum LeaveRequestStatus {
        PENDING("Pending"),
        APPROVED("Approved"),
        REJECTED("Rejected");

        private final String label;

        LeaveRequestStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
}


