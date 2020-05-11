package letsplugins.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import letsplugins.LetsNoRain;

public class StatusPlugin implements CommandExecutor {

	LetsNoRain plugin;

	public StatusPlugin(LetsNoRain instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (sender instanceof Player) {
			if (p.hasPermission("norain.admin")) {
				if ((plugin.getConfig().getBoolean("statusPlugin") == true)) {
					p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_ENABLE").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
				} else {
					p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_DISABLE").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
				}
			} else {
				p.sendMessage(plugin.getConfig().getString("Messages.Status.MSG_ALREADY_DISABLED").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
			}
		} else {
			p.sendMessage(plugin.getConfig().getString("Messages.Permissions.MSG_ONLY_PLAYERS").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		}

		return true;
	}
}
