package org.example6.example6;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;
import org.example6.example6.EventHandlers.HandlerTypes.Handler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerChatHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerRespawnHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;

public class EventManager implements Listener {
	ArrayList<PlayerTeleportHandler> OnTeleport = new ArrayList<PlayerTeleportHandler>();
	ArrayList<PlayerQuitHandler> OnPlayerQuit = new ArrayList<PlayerQuitHandler>();
	ArrayList<PlayerJoinHandler> OnPlayerJoin = new ArrayList<PlayerJoinHandler>();
	ArrayList<PlayerChatHandler> OnPlayerChat = new ArrayList<PlayerChatHandler>();
	ArrayList<EntityDamageByEntityHandler> OnEntityDamageByEntity = new ArrayList<EntityDamageByEntityHandler>();
	ArrayList<EntityDeathHandler> OnEntityDeath = new ArrayList<EntityDeathHandler>();
	ArrayList<PlayerRespawnHandler> OnPlayerRespawn = new ArrayList<PlayerRespawnHandler>();
	ArrayList<PlayerMoveHandler> OnPlayerMove = new ArrayList<PlayerMoveHandler>();
	ArrayList<BlockPlaceHandler> OnBlockPlace = new ArrayList<BlockPlaceHandler>();
	ArrayList<BlockBreakHandler> OnBlockBreak = new ArrayList<BlockBreakHandler>();
	
	public EventManager(example6 plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		for (Handler r : OnPlayerRespawn)
		{
			r.run(event);
		}
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
	
	@EventHandler
	public void OnPlayerQuit(PlayerQuitEvent event)
	{
		for (Handler r : OnPlayerQuit)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnPlayerChat(AsyncPlayerChatEvent event)
	{

		for (Handler r : OnPlayerChat)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerJoin(PlayerJoinEvent event)
	{
		for (Handler r : OnPlayerJoin)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerMove(PlayerMoveEvent event)
	{
		for (Handler r : OnPlayerMove)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent event)
	{

		for (Handler r : OnBlockPlace)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent event)
	{

		for (Handler r : OnBlockBreak)
		{
			r.run(event);
		}
	}
}
