package letsplugins;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Event implements Listener {
	
	LetsNoRain plugin;

	public Event(LetsNoRain instance) {
		plugin = instance;
	}

	@EventHandler
	public void noRain(WeatherChangeEvent e) {

		if ((plugin.getConfig().getBoolean("statusPlugin"))) {
			if (e.toWeatherState() == true) {
				e.setCancelled(true);
				Bukkit.broadcastMessage(plugin.getConfig().getString("Messages.Events.MSG_RAIN_CANCELLED").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
			}
		}
	}

}