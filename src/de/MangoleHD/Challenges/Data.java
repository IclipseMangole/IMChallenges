package de.MangoleHD.Challenges;

import de.Iclipse.IMAPI.Util.Dispatching.Dispatcher;
import de.MangoleHD.Challenges.Functions.Challenges.Challenge;
import de.MangoleHD.Challenges.Functions.GameStates.GameState;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ResourceBundle;

public class Data {

     public static Plugin instance;
     public static ResourceBundle langDE;
     public static ResourceBundle langEN;
     public static Dispatcher dsp;
     public static Location spawn;
     public static GameState gameState;
     public static Challenge challenge;
}
