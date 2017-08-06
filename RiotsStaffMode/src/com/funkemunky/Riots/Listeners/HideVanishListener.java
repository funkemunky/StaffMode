package com.funkemunky.Riots.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.funkemunky.Riots.StaffMode;

public class HideVanishListener implements Listener {

	private StaffMode core;
	public HideVanishListener(StaffMode core) {
		this.core = core;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(core.inVanish.size() > 0) {
			for(Player p : core.inVanish) {
				e.getPlayer().hidePlayer(p);
			}
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		if(core.isVanished(e.getPlayer())) {
			core.setVanished(e.getPlayer(), false);
		}
	}

}
