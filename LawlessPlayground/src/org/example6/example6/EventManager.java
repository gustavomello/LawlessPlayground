package org.example6.example6;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.EventHandlers.HandlerTypes.ChunkUnloadHandler;
import org.example6.example6.EventHandlers.HandlerTypes.CreatureSpawnHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityCombustHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityExplodeHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerChangedWorldHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerChatHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerLoginHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerRespawnHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PluginEnableHandler;

public class EventManager implements Listener {
	ArrayList<PlayerTeleportHandler> OnTeleport = new ArrayList<PlayerTeleportHandler>();
	ArrayList<PlayerQuitHandler> OnPlayerQuit = new ArrayList<PlayerQuitHandler>();
	ArrayList<PlayerJoinHandler> OnPlayerJoin = new ArrayList<PlayerJoinHandler>();
	ArrayList<PlayerChatHandler> OnPlayerChat = new ArrayList<PlayerChatHandler>();
	ArrayList<EntityDamageByEntityHandler> OnEntityDamageByEntity = new ArrayList<EntityDamageByEntityHandler>();
	ArrayList<EntityDeathHandler> OnEntityDeath = new ArrayList<EntityDeathHandler>();
	ArrayList<PlayerRespawnHandler> OnPlayerRespawn = new ArrayList<PlayerRespawnHandler>();
	ArrayList<PlayerChangedWorldHandler> OnPlayerChangedWorld = new ArrayList<PlayerChangedWorldHandler>();
	ArrayList<PlayerMoveHandler> OnPlayerMove = new ArrayList<PlayerMoveHandler>();
	ArrayList<BlockPlaceHandler> OnBlockPlace = new ArrayList<BlockPlaceHandler>();
	ArrayList<BlockBreakHandler> OnBlockBreak = new ArrayList<BlockBreakHandler>();
	ArrayList<PlayerLoginHandler> OnPlayerLogin = new ArrayList<PlayerLoginHandler>();
	ArrayList<PluginEnableHandler> OnPluginEnable = new ArrayList<PluginEnableHandler>();
	ArrayList<ChunkUnloadHandler> OnChunkUnload = new ArrayList<ChunkUnloadHandler>();
	ArrayList<EntityCombustHandler> OnEntityCombust = new ArrayList<EntityCombustHandler>();
	ArrayList<CreatureSpawnHandler> OnCreatureSpawn = new ArrayList<CreatureSpawnHandler>();
	ArrayList<EntityExplodeHandler> OnEntityExplode = new ArrayList<EntityExplodeHandler>();
	
	public EventManager(example6 plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void registerHandler(Object o)
	{
		if (o instanceof PlayerTeleportHandler)
		{
			this.OnTeleport.add((PlayerTeleportHandler) o);
		}
		
		if (o instanceof PlayerQuitHandler)
		{
			this.OnPlayerQuit.add((PlayerQuitHandler) o);
		}

		if (o instanceof PlayerJoinHandler)
		{
			this.OnPlayerJoin.add((PlayerJoinHandler) o);
		}

		if (o instanceof PlayerLoginHandler)
		{
			this.OnPlayerLogin.add((PlayerLoginHandler) o);
		}

		if (o instanceof PlayerChatHandler)
		{
			this.OnPlayerChat.add((PlayerChatHandler) o);
		}

		if (o instanceof EntityDamageByEntityHandler)
		{
			this.OnEntityDamageByEntity.add((EntityDamageByEntityHandler) o);
		}

		if (o instanceof EntityDeathHandler)
		{
			this.OnEntityDeath.add((EntityDeathHandler) o);
		}

		if (o instanceof PlayerRespawnHandler)
		{
			this.OnPlayerRespawn.add((PlayerRespawnHandler) o);
		}

		if (o instanceof PlayerChangedWorldHandler)
		{
			this.OnPlayerChangedWorld.add((PlayerChangedWorldHandler) o);
		}

		if (o instanceof PlayerMoveHandler)
		{
			this.OnPlayerMove.add((PlayerMoveHandler) o);
		}

		if (o instanceof BlockPlaceHandler)
		{
			this.OnBlockPlace.add((BlockPlaceHandler) o);
		}

		if (o instanceof BlockBreakHandler)
		{
			this.OnBlockBreak.add((BlockBreakHandler) o);
		}

		if (o instanceof PluginEnableHandler)
		{
			this.OnPluginEnable.add((PluginEnableHandler) o);
		}

		if (o instanceof ChunkUnloadHandler)
		{
			this.OnChunkUnload.add((ChunkUnloadHandler) o);
		}

		if (o instanceof EntityCombustHandler)
		{
			this.OnEntityCombust.add((EntityCombustHandler) o);
		}

		if (o instanceof CreatureSpawnHandler)
		{
			this.OnCreatureSpawn.add((CreatureSpawnHandler) o);
		}

		if (o instanceof EntityExplodeHandler)
		{
			this.OnEntityExplode.add((EntityExplodeHandler) o);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		for (PlayerRespawnHandler r : OnPlayerRespawn)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnTeleport(PlayerTeleportEvent event)
	{
		for (PlayerTeleportHandler r : OnTeleport)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		for (EntityDamageByEntityHandler r : OnEntityDamageByEntity)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityDeath(EntityDeathEvent event)
	{
		for (EntityDeathHandler r : OnEntityDeath)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnPlayerQuit(PlayerQuitEvent event)
	{
		for (PlayerQuitHandler r : OnPlayerQuit)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnPlayerChat(AsyncPlayerChatEvent event)
	{

		for (PlayerChatHandler r : OnPlayerChat)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerJoin(PlayerJoinEvent event)
	{
		for (PlayerJoinHandler r : OnPlayerJoin)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerLogin(PlayerLoginEvent event)
	{
		for (PlayerLoginHandler r : OnPlayerLogin)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerMove(PlayerMoveEvent event)
	{
		for (PlayerMoveHandler r : OnPlayerMove)
		{
			r.run(event);
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void OnBlockPlace(BlockPlaceEvent event)
	{
		for (BlockPlaceHandler r : OnBlockPlace)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent event)
	{
		for (BlockBreakHandler r : OnBlockBreak)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnPluginEnable(PluginEnableEvent event)
	{
		for (PluginEnableHandler r : OnPluginEnable)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnChunkUnload(ChunkUnloadEvent event)
	{
		for (ChunkUnloadHandler r : OnChunkUnload)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityCombust(EntityCombustEvent event)
	{
		for (EntityCombustHandler r : OnEntityCombust)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnCreatureSpawn(CreatureSpawnEvent event)
	{
		for (CreatureSpawnHandler r : OnCreatureSpawn)
		{
			r.run(event);
		}
	}
	
	@EventHandler
	public void OnEntityExplode(EntityExplodeEvent event)
	{
		for (EntityExplodeHandler r : OnEntityExplode)
		{
			r.run(event);
		}
	}
}
