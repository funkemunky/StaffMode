package com.funkemunky.Riots.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.funkemunky.Riots.StaffMode;

import net.md_5.bungee.api.ChatColor;

public class StaffModeOnJoin implements Listener {
	
	private StaffMode core;
	public StaffModeOnJoin(StaffMode core) {
		this.core = core;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(core.isStaff(p) || !core.isVanished(p)) {
			p.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You were automatically put in vanish since you joined!");
			core.setVanished(p, true);
		}
	}

}
