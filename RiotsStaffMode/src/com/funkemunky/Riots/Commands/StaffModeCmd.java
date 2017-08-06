package com.funkemunky.Riots.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.funkemunky.Riots.StaffMode;

public class StaffModeCmd implements CommandExecutor {
	
	private StaffMode core;
	public StaffModeCmd(StaffMode core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("riots.staff") || !(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this.");
			return true;
		}
		if(args.length == 0) {
			Player p = (Player) sender;
			
			if(!core.inStaffMode.contains(p)) {
				core.setStaffMode(p, true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour staffmode has been &aenabled&e."));
			} else {
				core.setStaffMode(p, false);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour staffmode has been &cdisabled&e."));
			}
		}
		return true;
	}

}
