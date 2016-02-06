package me.LootCrate.com;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.LootCrate.com.Methods.Methods;

public class SetLootCommand implements CommandExecutor {

	public static String Prefix = ChatColor.YELLOW + "[LootCrate]";

	public void set(Player p) {
		Inventory inv = p.getInventory();
		ArrayList<ItemStack> arr = new ArrayList<ItemStack>();
		for(ItemStack item : inv.getContents()) {
			ItemStack air = new ItemStack(Material.AIR);
			if(item != null && item != air) {
				arr.add(item);
			}
		}
		Primal.cf.set("InventoryLoot", arr);
		Primal.saveConfigs();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("startdrops")) {
			if(p.hasPermission("lootcrate.start")) {
				if(Methods.items == null) {
					p.sendMessage(Prefix + ChatColor.RED + " You haven't set the loot for your chests yet!");
				}else {
					Primal.startLoot(p);
					p.sendMessage(Prefix + ChatColor.GREEN+ " LootCrates Activated!");
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("stopdrops")) {
			if(p.hasPermission("lootcrate.stop")) {
				Primal.stopLoot();
				p.sendMessage(Prefix + ChatColor.RED + " LootCrates Deactivated!");
			}
		}
		if(cmd.getName().equalsIgnoreCase("setloot")) {
			if(p.hasPermission("lootcrate.setloot")) {
				set(p);
				p.sendMessage(Prefix + ChatColor.GOLD + " The Items have been set in the chests!");
				p.getInventory().clear();
			}
		}
		return true;
	}
}
