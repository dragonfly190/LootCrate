package me.LootCrate.com.Methods;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.LootCrate.com.Primal;

public class Methods {

	@SuppressWarnings("unchecked")
	public static List<ItemStack> items = (List<ItemStack>) Primal.cf.getList("InventoryLoot");
	public static int duration = Primal.cf.getInt("Repeating-Timer");
	public static void drop(Player p) {
		Location loc = new Location(Bukkit.getWorld("world"), 0, 0, 0);
		Random rand = new Random();
		loc.setX(rand.nextInt(8000));
		loc.setY(100);
		loc.setZ(rand.nextInt(8000));		 
		loc.setY(Bukkit.getWorld("world").getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()).getY());
		loc.getBlock().setType(Material.CHEST);
		Block bb = loc.getBlock();
		bb.setType(Material.CHEST);
		Chest chest = (Chest) bb.getState();
		
        Inventory inv = chest.getInventory();
        
        for(ItemStack stack : items){
        	  inv.addItem(stack);
        	}
		
		Bukkit.broadcastMessage(ChatColor.RED + "A tornado has appeared in the world, and left valuble supplies!");
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes(
				'&', "&7Coords: X: &c" + loc.getBlockX() + "&7 Y:&c " + loc.getBlockY() + "&7 Z: &c" + 
						loc.getBlockZ()));
	}
}
