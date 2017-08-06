package com.funkemunky.Riots.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.funkemunky.Riots.StaffMode;

public class HideStaffCmd implements CommandExecutor {

	private StaffMode core;
	public HideStaffCmd(StaffMode core) {
		this.core = core;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("hide.staff") || !(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}
		
		if(args.length != 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /hidestaff");
			return true;
		}
		
		Player p = (Player) sender;
		if(!core.hiddenStaff.contains(p)) {
			if(!core.staffOnline.isEmpty()) {
				for(Player staff : core.inStaffMode) {
					if(!staff.getUniqueId().equals(p.getUniqueId())) {
						p.hidePlayer(staff);
					}
				}
			}
			core.hiddenStaff.add(p);
			p.sendMessage(ChatColor.GREEN + "You have hidden staff!");
		} else {
			if(!core.staffOnline.isEmpty()) {
				for(Player staff : core.inStaffMode) {
					if(!staff.getUniqueId().equals(p.getUniqueId())) {
						p.showPlayer(staff);
					}
				}
			}
			core.hiddenStaff.remove(p);
			p.sendMessage(ChatColor.GREEN + "Now showing staff!");
		}
		
		return true;
	}

}
