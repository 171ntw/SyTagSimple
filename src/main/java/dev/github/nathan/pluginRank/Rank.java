package dev.github.nathan.pluginRank;

public enum Rank {

    ADMIN("§4§lAdmin ", "pluginrank.admin"),
    MEMBER("§e§lMember ", "pluginrank.member");

    private String display;
    private String permission;

    Rank(String display, String permission) {
        this.display = display;
        this.permission = permission;
    }

    public String getDisplay() {
        return display;
    }

    public String getPermission() {
        return permission;
    }

    public static Rank getRankByPermission(String permission) {
        for (Rank rank : values()) {
            if (rank.getPermission().equals(permission)) {
                return rank;
            }
        }
        return MEMBER;
    }
}