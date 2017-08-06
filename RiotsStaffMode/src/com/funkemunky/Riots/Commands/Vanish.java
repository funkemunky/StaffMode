package com.funkemunky.Riots.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.funkemunky.Riots.StaffMode;

public class Vanish implements CommandExecutor {

	private StaffMode core;
	public Vanish(StaffMode core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player) || !sender.hasPermission("riots.staff") || !sender.hasPermission("essentials.vanish")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this.");
			return true;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			if(core.isInStaffMode(p)) {
				if(core.isVanished(p)) {
					ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 8);
					ItemMeta vanishMeta = vanish.getItemMeta();
					vanishMeta.setDisplayName(ChatColor.RED + "Vanish");
					vanishMeta.setLore(core.vanishLore());
					vanish.setItemMeta(vanishMeta);
					
					p.getInventory().setItem(8, vanish);
					p.updateInventory();
					core.setVanished(p, false);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour vanish has been &cdisabled&e."));
				} else {
					ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 10);
					ItemMeta vanishMeta = vanish.getItemMeta();
					vanishMeta.setDisplayName(ChatColor.RED + "Vanish");
					vanishMeta.setLore(core.vanishLore());
					vanish.setItemMeta(vanishMeta);
					
					p.getInventory().setItem(8, vanish);
					p.updateInventory();
					core.setVanished(p, true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour vanish has been &aenabled&e."));
				}
			} else {
				if(core.isVanished(p)) {
					core.setVanished(p, false);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour vanish has been &cdisabled&e."));
				} else {
					core.setVanished(p, true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYour vanish has been &aenabled&e."));
				}
			}
		} else {
			if(args.length == 1) {
				Player argsPlayer = Bukkit.getPlayer(args[0]);
				if(core.isInStaffMode(argsPlayer)) {
					if(core.isVanished(p)) {
						ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 8);
						ItemMeta vanishMeta = vanish.getItemMeta();
						vanishMeta.setDisplayName(ChatColor.RED + "Vanish");
						vanishMeta.setLore(core.vanishLore());
						vanish.setItemMeta(vanishMeta);
						
						p.getInventory().setItem(8, vanish);
						p.updateInventory();
						core.setVanished(argsPlayer, false);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSet " + argsPlayer.getName() +"'s vanish to &cfalse&e."));
						argsPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&oYou have been put into vanish by " + p.getName() + "."));
					} else {
						ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 10);
						ItemMeta vanishMeta = vanish.getItemMeta();
						vanishMeta.setDisplayName(ChatColor.RED + "Vanish");
						vanishMeta.setLore(core.vanishLore());
						vanish.setItemMeta(vanishMeta);
						
						p.getInventory().setItem(8, vanish);
						p.updateInventory();
						core.setVanished(argsPlayer, true);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSet " + argsPlayer.getName() +"'s vanish to &atrue&e."));
						argsPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&oYou have been taken out of vanish by " + p.getName() + "."));
					}
				} else {
					if(core.isVanished(p)) {
						core.setVanished(argsPlayer, false);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSet " + argsPlayer.getName() +"'s vanish to &cfalse&e."));
						argsPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&oYou have been put into vanish by " + p.getName() + "."));
					} else {
						core.setVanished(argsPlayer, true);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eSet " + argsPlayer.getName() +"'s vanish to &atrue&e."));
						argsPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&oYou have been taken out of vanish by " + p.getName() + "."));
					}
				}
				return true;
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError: &7Too many arguments! &9&oUse /vanish <player>"));
		}
		return true;
	}

}
