package org.example6.example6;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;
import org.example6.example6.EventHandlers.HandlerTypes.Handler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;

public class EventManager implements Listener {
	ArrayList<PlayerTeleportHandler> OnTeleport = new ArrayList<PlayerTeleportHandler>();
	ArrayList<EntityDamageByEntityHandler> OnEntityDamageByEntity = new ArrayList<EntityDamageByEntityHandler>();
	ArrayList<EntityDeathHandler> OnEntityDeath = new ArrayList<EntityDeathHandler>();
	
	public EventManager(example6 plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void OnTeleport(PlayerTeleportEvent event)
	{
		for (Handler r : OnTeleport)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		for (Handler r : OnEntityDamageByEntity)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityDeath(EntityDeathEvent event)
	{
		for (Handler r : OnEntityDeath)
		{
			r.run(event);
		}
	}
}
