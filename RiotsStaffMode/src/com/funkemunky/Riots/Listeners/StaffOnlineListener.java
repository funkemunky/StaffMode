package com.funkemunky.Riots.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.funkemunky.Riots.StaffMode;

public class StaffOnlineListener implements Listener {
	
	private StaffMode core;
	public StaffOnlineListener(StaffMode core) {
		this.core = core;
	}
	
	//Adds staff to online list on join.
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!core.staffOnline.contains(p) && p.hasPermission("riots.staff")) {
			core.staffOnline.add(p);
		}
	}
	
	//Removes staff from online list on quit.
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission("riots.staff") && core.staffOnline.contains(p)) {
			e.getPlayer().setGameMode(GameMode.SURVIVAL);
			core.staffOnline.remove(p);
		}
	}

}
