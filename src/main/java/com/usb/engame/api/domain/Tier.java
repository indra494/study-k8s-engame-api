package com.usb.engame.api.domain;

public enum Tier {
    BRONZE(0, "브론즈"),
    SILVER(100, "실버"),
    GOLD(300, "골드"),
    PLATINUM(600, "플래티넘"),
    DIAMOND(1000, "다이아몬드");

    private final int minScore;
    private final String displayName;

    Tier(int minScore, String displayName) {
        this.minScore = minScore;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Tier fromScore(long totalScore) {
        Tier result = BRONZE;
        for (Tier tier : values()) {
            if (totalScore >= tier.minScore) {
                result = tier;
            }
        }
        return result;
    }
}
