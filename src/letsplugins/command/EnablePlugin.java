package letsplugins.command;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import letsplugins.LetsNoRain;

public class EnablePlugin implements CommandExecutor {

	LetsNoRain plugin;

	public EnablePlugin(LetsNoRain instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
			
		if (p.hasPermission("norain.admin") == true) {
			if (sender instanceof Player) {
				if ((plugin.getConfig().getBoolean("statusPlugin") == false)) {

					p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_ENABLE").replaceAll("(&([a-f0-9]))",
							"\u00A7$2"));

					plugin.getConfig().set("statusPlugin", true);
					plugin.saveConfig();

					World world = p.getPlayer().getWorld();
					world.setWeatherDuration(0);
					world.setStorm(false);
					world.setThundering(false);
					world.setThunderDuration(0);

				} else {
					p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_ALREADY_ENABLED")
							.replaceAll("(&([a-f0-9]))", "\u00A7$2"));
				}
			} else {
				p.sendMessage(plugin.getConfig().getString("Messages.Permissions.MSG_ONLY_PLAYERS")
						.replaceAll("(&([a-f0-9]))", "\u00A7$2"));
			}
		} else {
			p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_NOT_ALLOWED_COMMAND" + cmd.getName())
					.replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		}

		return true;
	}
}