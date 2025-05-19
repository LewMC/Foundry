package net.lewmc.foundry;

import org.bukkit.Bukkit;

public class FoundryCompat {
    /**
     * Checks the version of Foundry is compatible with your plugin.
     * @param min String - The minimum version of Foundry you need.
     * @return boolean - Is the version a match
     */
    public boolean checkMin(String min) {
        String currentVersion = Bukkit.getPluginManager()
                .getPlugin("Foundry")
                .getPluginMeta()
                .getVersion();

        return compareVersions(this.stripSuffix(currentVersion), this.stripSuffix(min)) >= 0;
    }

    /**
     * Checks the version of Foundry is no greater than the supplied version.
     * @param min String - The minimum version of Foundry you need.
     * @return boolean - Is the version a match
     */
    public boolean checkMax(String min) {
        String currentVersion = Bukkit.getPluginManager()
                .getPlugin("Foundry")
                .getPluginMeta()
                .getVersion();

        return compareVersions(this.stripSuffix(currentVersion), this.stripSuffix(min)) <= 0;
    }

    /**
     * Compares two versions strings.
     * @param v1 String - 1
     * @param v2 String - 2
     * @return int - The difference
     */
    private int compareVersions(String v1, String v2) {
        String[] parts1 = v1.split("\\.");
        String[] parts2 = v2.split("\\.");

        int length = Math.max(parts1.length, parts2.length);
        for (int i = 0; i < length; i++) {
            int p1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
            int p2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;

            if (p1 != p2) {
                return Integer.compare(p1, p2);
            }
        }
        return 0;
    }

    /**
     * Strips '-SNAPSHOT' or other suffixes from version strings.
     * @param version String - The version string
     * @return String - The stripped string.
     */
    private String stripSuffix(String version) {
        return version.split("-")[0];  // Keeps only the part before "-"
    }
}
