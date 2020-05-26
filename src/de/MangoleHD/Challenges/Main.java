package de.MangoleHD.Challenges;

import de.Iclipse.IMAPI.Util.Dispatching.Dispatcher;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static de.Iclipse.IMAPI.IMAPI.*;
import static de.MangoleHD.Challenges.Data.langDE;
import static de.MangoleHD.Challenges.Data.langEN;

public class Main extends JavaPlugin {

    @Override
    public void onLoad(){
        super.onLoad();
        Data.instance = this;
        loadChallengesLobby();
    }

    @Override
    public void onEnable(){
        super.onEnable();
        loadResourceBundles();
        Data.spawn = new Location(Bukkit.getWorld("world"),-3.5,95,183.5);
    }

    @Override
    public void onDisable(){
        super.onDisable();

    }

    public void loadChallengesLobby(){
        if (new File(Bukkit.getWorldContainer().getAbsolutePath() + "/world").exists()) {
            deleteFile(new File(Bukkit.getWorldContainer().getAbsolutePath() + "/world"));
        }
        File from = new File("/home/IMNetzwerk/BuildServer/ChallengesLobby_world/region");
        File to = new File(Data.instance.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() + "/world/region");
        if (to.exists()) {
            to.delete();
        }
        try {
            copyFilesInDirectory(from, to);
            Files.copy(new File("/home/IMNetzwerk/BuildServer/ChallengesLobby_world/level.dat").toPath(), new File(Data.instance.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() + "/world/level.dat").toPath(), StandardCopyOption.REPLACE_EXISTING);
            copyFilesInDirectory(new File("/home/IMNetzwerk/BuildServer/ChallengesLobby_world/maps"), new File(Data.instance.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() + "/world/maps"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadResourceBundles() {
        try {
            HashMap<String, ResourceBundle> langs = new HashMap<>();
            langDE = ResourceBundle.getBundle("i18n.langDE");
            langEN = ResourceBundle.getBundle("i18n.langEN");
            langs.put("DE", langDE);
            langs.put("EN", langEN);
            Data.dsp = new Dispatcher(this,
                    langs);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            de.Iclipse.IMAPI.Data.dispatching = false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Reload oder Bundle not found!");
            de.Iclipse.IMAPI.Data.dispatching = false;
        }
    }

}
