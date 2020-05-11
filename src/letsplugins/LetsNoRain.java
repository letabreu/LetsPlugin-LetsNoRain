package letsplugins;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import letsplugins.command.DisablePlugin;
import letsplugins.command.EnablePlugin;
import letsplugins.command.StatusPlugin;

public class LetsNoRain extends JavaPlugin {

	protected static PluginDescriptionFile pdfFile = null;

	public void onEnable() {
		final FileConfiguration config = this.getConfig();
		pdfFile = this.getDescription();
		 
		config.addDefault("statusPlugin", true);
		config.options().copyDefaults(true);
		saveConfig();	
		
		Bukkit.getConsoleSender().sendMessage(((getConfig().getString("Messages.Informations.MSG_HEADER_VERSION").replaceAll("(&([a-f0-9]))", "\u00A7$2")) + pdfFile.getVersion() + " is enabled!"));
		
		getCommand("disablenorain").setExecutor(new DisablePlugin(this));
		getCommand("enablenorain").setExecutor(new EnablePlugin(this));
		getCommand("statusnorain").setExecutor(new StatusPlugin(this));

		getServer().getPluginManager().registerEvents(new Event(this), this); 
	}

	public void onDisable() {
		pdfFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage(((getConfig().getString("Messages.Informations.MSG_HEADER_VERSION").replaceAll("(&([a-f0-9]))", "\u00A7$2")) + pdfFile.getVersion() + " is disable!"));
	}	
}
