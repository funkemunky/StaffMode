package com.funkemunky.Riots.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.funkemunky.Riots.StaffMode;


public class StaffAdmin implements CommandExecutor {
	
	private StaffMode core;
	public StaffAdmin(StaffMode core) {
		this.core = core;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("riot.admin") || !(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this.");
		}
		if(args.length == 0) {
			Player player = (Player) sender;
			core.getConfig().set("Inventory", player.getInventory().getContents());
			core.saveConfig();
			sender.sendMessage("Set the shit you dingaling.");
		}
		return true;
	}

}
