package me.LootCrate.com;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.LootCrate.com.Methods.Methods;


public class Primal extends JavaPlugin {

	public static Plugin instance;

	public static File clanFile;

	public static FileConfiguration cf;

	//inv.addItem(items.toArray(new ItemStack[items.size()]));

	public void onEnable() {
		instance = this;
		clanFile = new File(getDataFolder(), "settings.yml");
		cf = YamlConfiguration.loadConfiguration(clanFile);
		cf.addDefault("Repeating-Timer", 1200);
		saveConfigs();
		getCommand("setloot").setExecutor(new SetLootCommand());
		getCommand("startdrops").setExecutor(new SetLootCommand());
		getCommand("stopdrops").setExecutor(new SetLootCommand());
	}

	public static void saveConfigs() {
		try{
			cf.save(clanFile);
			cf.load(clanFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startLoot(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Primal.instance, new Runnable() {
			public void run() {
				Methods.drop(p);
			}
		}, 0, Methods.duration * 20);
	}

	public static void stopLoot() {
		Bukkit.getServer().getScheduler().cancelAllTasks();
	}
}
