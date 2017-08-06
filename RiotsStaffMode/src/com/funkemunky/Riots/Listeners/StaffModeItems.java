package com.funkemunky.Riots.Listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.funkemunky.Riots.StaffMode;



public class StaffModeItems implements Listener {
	
	private StaffMode core;
	public StaffModeItems(StaffMode core) {
		this.core = core;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack itemInHand = p.getItemInHand();
		Inventory rm = Bukkit.createInventory(null, 54, "Silent Chest");
		
		if(core.isInStaffMode(p)) {
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(itemInHand.getType().equals(Material.WATCH) && itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Random Teleport")) {
					
					if(core.toTeleportTo.size() > 0) {
						Random r = new Random();
						int index = r.nextInt(core.toTeleportTo.size());
						p.teleport(core.toTeleportTo.get(index).getLocation());
						p.sendMessage(ChatColor.YELLOW + "Teleported to " + ChatColor.WHITE + core.toTeleportTo.get(index).getDisplayName());
					} else {
						p.sendMessage(ChatColor.RED + "Prevented teleportation since there is nobody to teleport to.");
					}
 				}
				
				if(itemInHand.getType().equals(Material.INK_SACK) && itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Vanish")) {
					Bukkit.dispatchCommand(p, "vanish");
				}
				if(itemInHand.getType().equals(Material.SKULL_ITEM) && itemInHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Staff Online")) {
				    core.staffOnlineGUI.open(p);
				}
			}
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getClickedBlock().getType().equals(Material.CHEST) || e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
					Chest chest = (Chest) e.getClickedBlock().getState();
					p.sendMessage(ChatColor.YELLOW + "Opened chest silently.");
					rm.setContents(chest.getInventory().getContents());
					p.openInventory(rm);
					e.setCancelled(true);
				}
			}
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(core.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(core.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryDrop(PlayerDropItemEvent e) {
		if(core.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInvPickup(PlayerPickupItemEvent e) {
		if(core.isInStaffMode(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInvMove(InventoryClickEvent e) {
		if(core.isInStaffMode((Player) e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			if(core.isInStaffMode((Player) e.getDamager())) {
				Player p = (Player) e.getDamager();
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Get out of staff mode to pvp!");
				return;
			}
			if(core.isVanished((Player) e.getDamager())) {
				Player p = (Player) e.getDamager();
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Get out of vanish to pvp!");
				return;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		ItemStack itemInHand = p.getItemInHand();
		if(core.inStaffMode.contains(p)) {
			if(itemInHand.getType().equals(Material.IRON_FENCE)) {
				if(e.getRightClicked() instanceof Player) {
					Player clicked = (Player) e.getRightClicked();
					Bukkit.dispatchCommand(p, "freeze " + clicked.getName());
				}
			}
			if(itemInHand.getType().equals(Material.BOOK)) {
				if(e.getRightClicked() instanceof Player) {
					Player clicked = (Player) e.getRightClicked();
					Damageable clickedd = clicked;
					Inventory rm = Bukkit.createInventory(null, 54, clicked.getName());
					rm.setContents(clicked.getInventory().getContents());
					for(int i = 36; i < 45 ; i++) {
						rm.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 5));
					}
					ItemStack health = new ItemStack(Material.SPECKLED_MELON, 1);
					ItemMeta healthMeta = health.getItemMeta();
					healthMeta.setDisplayName(ChatColor.RED.toString() + clickedd.getHealth() / 2 + "♥");
					health.setItemMeta(healthMeta);
					ItemStack hunger = new ItemStack(Material.COOKED_CHICKEN, 1);
					ItemMeta hungerMeta = health.getItemMeta();
					hungerMeta.setDisplayName(ChatColor.RED.toString() + clicked.getFoodLevel() + " Hunger");
					hunger.setItemMeta(hungerMeta);
					ItemStack potions = new ItemStack(Material.POTION, 1);
					ItemMeta potionsMeta = potions.getItemMeta();
					ArrayList<String> effects = new ArrayList<String>();
					effects.add(" ");
					for(PotionEffect effect : clicked.getActivePotionEffects()) {
						if(clicked.getActivePotionEffects() != null) {
							effects.add(ChatColor.WHITE + effect.getType().getName() + " " + effect.getDuration());
						}
					}
					
				    potionsMeta.setLore(effects);
				    potionsMeta.setDisplayName(ChatColor.RED + "Potion Effects");
				    potions.setItemMeta(potionsMeta);
				    
					rm.setItem(45, health);
					rm.setItem(46, hunger);
					rm.setItem(47, potions);
					
					p.openInventory(rm);
				}
			}
		}
		e.setCancelled(true);
	}

}
