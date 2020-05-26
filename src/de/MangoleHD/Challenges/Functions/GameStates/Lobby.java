package de.MangoleHD.Challenges.Functions.GameStates;

import de.MangoleHD.Challenges.Data;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static de.MangoleHD.Challenges.Data.gameState;
import static de.MangoleHD.Challenges.Data.spawn;

public class Lobby implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.teleport(spawn);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (gameState == GameState.Lobby) {
                if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
                    event.setDamage(0);
                    player.setHealth(20);
                    player.teleport(spawn);
                    player.playSound(player.getLocation(), Sound.ENTITY_COW_DEATH, 1, 1);
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onStarve(FoodLevelChangeEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.DEFAULT) || event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) {
            if (gameState == GameState.Lobby) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrop(PlayerDropItemEvent event) {
        if (gameState == GameState.Lobby) {
            event.setCancelled(true);
        }
    }
}
