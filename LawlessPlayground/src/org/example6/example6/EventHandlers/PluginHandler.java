package org.example6.example6.EventHandlers;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Server;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.example6.example6.example6;
import org.example6.example6.EventHandlers.HandlerTypes.PluginEnableHandler;
import com.onarandombox.MultiverseCore.MultiverseCore;

public class PluginHandler 
	implements PluginEnableHandler {

	@Override
	public void run(PluginEnableEvent event) {
	}

	public static void RegisterOtherPlugins(Server server)
	{
		if (example6.getPlug().getEconomy() == null)
		{
			RegisteredServiceProvider<Economy> rsp = server.getServicesManager().getRegistration(Economy.class);
			if (rsp.getProvider()==null)
			{
				System.out.println("Couldn't find Economy plugin");
			}
			else
			{
			example6.getPlug().setEconomy(rsp.getProvider());
			}
		}
		if (example6.getPlug().getMvcore() == null)
		{
			example6.getPlug().setMvcore(
					(MultiverseCore) server.getPluginManager().getPlugin("Multiverse-Core")
					);
		}
	}
}
