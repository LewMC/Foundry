package net.lewmc.foundry.utils;

import com.tchristofferson.configupdater.ConfigUpdater;
import net.lewmc.foundry.Foundry;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Foundry's update utility.
 */
public class UpdateUtil {
    private final Foundry plugin;
    private final LogUtil log;

    /**
     * Constructor for UpdateUtil class.
     * @param plugin Reference to the main Essence class.
     */
    public UpdateUtil(Foundry plugin) {
        this.plugin = plugin;
        this.log = new LogUtil(plugin);
    }

    /**
     * Checks versions using LewMC's API.
     */
    public void versionCheck() {
        if (this.plugin.getConfig().getBoolean("update-check")) {
            try {
                URL url;
                if (this.plugin.getDescription().getVersion().contains("SNAPSHOT")) {
                    log.warn("SNAPSHOT > You are running a snapshot build of "+this.plugin.pluginId+".");
                    log.warn("SNAPSHOT > This build may include bugs and is not recommended on production servers.");
                    log.warn("SNAPSHOT > If you find any issues please report them to github.com/lewmc/"+this.plugin.pluginId+".");
                    this.log.info("");
                    url = new URL("https://service.lewmc.net/latest-version/?resource="+this.plugin.pluginId+"-snapshot&format=simpleversion");
                } else {
                    url = new URL("https://service.lewmc.net/latest-version/?resource="+this.plugin.pluginId+"&format=simpleversion");
                }
                Scanner s = new Scanner(url.openStream());
                if (s.hasNextLine()) {
                    String response = s.nextLine();
                    if (response.isEmpty()) {
                        log.severe("Unable to perform update check: There was no response from the server.");
                        this.log.info("");
                    } else if (response.equals(this.plugin.getDescription().getVersion())) {
                        log.info("You are running the latest version of "+this.plugin.pluginId+".");
                        this.log.info("");
                    } else {
                        log.warn("UPDATE > There's a new version of "+this.plugin.pluginId+" available.");
                        log.warn("UPDATE > Your version: "+this.plugin.getDescription().getVersion()+" - latest version: "+response);
                        log.warn("UPDATE > You can download the latest version from lewmc.net/"+this.plugin.pluginId);
                        this.log.info("");
                        this.plugin.settings.hasPendingUpdate = true;
                    }
                } else {
                    log.severe("Unable to perform update check: There was no response from the server.");
                    this.log.info("");
                }
            } catch (MalformedURLException e) {
                log.severe("Unable to perform update check: MalformedURLException - "+e);
                this.log.info("");
            } catch (IOException e) {
                log.warn("Unable to perform update check: IOException - "+e);
                this.log.info("");
            }
        } else {
            log.warn("Unable to perform update check: Update checking is disabled.");
            this.log.info("");
        }
    }

    /**
     * Updates Foundry's configuration.
     */
    public void updateFoundry() {
        File configFile = new File(this.plugin.getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(plugin, "config.yml", configFile);
        } catch (IOException e) {
            this.log.warn("Unable to update configuration: "+e);
        }
    }

    /**
     * Updates a single file.
     * @param fileName String - The name of the file relative to /plugins/foundry/pluginName-data
     * @param resource String - The resource relative to your plugin.
     * @return boolean - If the operation was successful.
     */
    public boolean updateFile(String fileName, String resource) {
        File file = new File(this.plugin.getDataFolder() + File.separator + this.plugin.pluginId + "-data" + File.separator + fileName);
        if (!file.exists()) {
            this.plugin.saveResource(resource, false);
            return true;
        } else {
            try {
                ConfigUpdater.update(plugin, resource, file);
                return true;
            } catch (IOException e) {
                this.log.warn("Unable to update file '" + file + "' because "+e);
                return false;
            }
        }
    }
}
