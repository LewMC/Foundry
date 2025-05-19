package net.lewmc.foundry;

public class FoundryConfig {
    public boolean verbose;
    public String pluginId;

    public FoundryConfig(String pluginId) {
        this.pluginId = pluginId.toLowerCase();
    }
}
