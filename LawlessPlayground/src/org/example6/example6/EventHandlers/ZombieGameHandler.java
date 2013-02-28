package org.example6.example6.EventHandlers;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.example6.example6.example6;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.BlockBreakHandler;
import org.example6.example6.EventHandlers.HandlerTypes.BlockPlaceHandler;
import org.example6.example6.EventHandlers.HandlerTypes.CreatureSpawnHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityCombustHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDamageByEntityHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityDeathHandler;
import org.example6.example6.EventHandlers.HandlerTypes.EntityExplodeHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerMoveHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerQuitHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerRespawnHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerTeleportHandler;
import org.example6.example6.Tasks.MobMovement;
import org.example6.example6.Tasks.ZombiesCheckupTask;
import org.example6.example6.Utils.EntityManager;

public class ZombieGameHandler 
	implements BlockBreakHandler,
	BlockPlaceHandler,
	EntityDamageByEntityHandler,
	EntityDeathHandler,
	PlayerMoveHandler,
	EntityCombustHandler,
	CreatureSpawnHandler,
	EntityExplodeHandler,
	PlayerTeleportHandler,
	PlayerJoinHandler,
	PlayerQuitHandler,
	PlayerRespawnHandler {

	@Override
	public void run(PlayerTeleportEvent event) {
		if (!event.getTo().getWorld().equals(event.getFrom().getWorld()))
		{
			if (event.getTo().getWorld().equals(
					example6.getWorldManager().getZombieWorld()
					))
			{
				this.handlePlayerEnter(event.getPlayer());
			}
			else if (event.getFrom().getWorld().equals(
					example6.getWorldManager().getZombieWorld()
					))
			{
				this.handlePlayerExit(event.getPlayer());
			}
		}
	}

	@Override
	public void run(PlayerQuitEvent event) {
		if (event.getPlayer().getWorld().equals(
				example6.getWorldManager().getZombieWorld()
				))
		{
			this.handlePlayerExit(event.getPlayer());
		}
	}

	@Override
	public void run(PlayerJoinEvent event) {
		if (event.getPlayer().getWorld().equals(
				example6.getWorldManager().getZombieWorld()
				))
		{
			this.handlePlayerEnter(event.getPlayer());
		}
	}

	@Override
	public void run(PlayerRespawnEvent event) {
		if (event.getPlayer().getWorld().equals(
				example6.getWorldManager().getZombieWorld()
				))
		{
			this.handlePlayerEnter(event.getPlayer());
		}
	}

	@Override
	public void run(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (!p.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (event.getFrom().getBlock().equals(event.getTo().getBlock()))
				return;
		
		double noticeChance = 1.5d;
		if (p.isSneaking())
			noticeChance = 0.25d;
		if (p.isSprinting())
			noticeChance = 5d;
		
		Random r = new Random();
		if (r.nextDouble() * 100 <= noticeChance)
			MobMovement.handleNoise(p, 40);
	}
	/*
	@Override
	public void run(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		if (!p.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		MobMovement.handleNoise(p, 50);
	}
*/
	@Override
	public void run(EntityDeathEvent event) {
		Entity e = event.getEntity();//
		if (!e.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (e instanceof Zombie)
		{
			if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent)
			{
				Entity attacker = ((EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
				if (event.getEntity() instanceof Zombie
						&& attacker instanceof Player)
				{
					event.getDrops().clear();
					this.handleZombieDeath((Player)attacker);
					MobMovement.handleNoise(e, 80);
					return;
				}
			}
		}

		if (e instanceof Player)
		{
			this.handlePlayerDeath((Player) event.getEntity());
		}
	}

	@Override
	public void run(EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();//
		
		if (!e.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		Entity attacker = event.getDamager();
		if (e instanceof Player
				&& attacker instanceof Zombie)
		{
			this.handleInfection((Player)e);
			return;
		}
	}

	@Override
	public void run(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		if (!p.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (p.isSneaking())
			return;
		
		MobMovement.handleNoise(p, 40);
	}

	@Override
	public void run(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if (!p.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (p.isSneaking())
			return;
		
		MobMovement.handleNoise(p, 50);
	}

	@Override
	public void run(EntityCombustEvent event) {
		Entity e = event.getEntity();
		if (!e.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (!e.getType().equals(EntityType.ZOMBIE))
			return;
		
		event.setCancelled(true);
	}

	@Override
	public void run(CreatureSpawnEvent event) {
		Entity e = event.getEntity();
		if (!e.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		if (event.getSpawnReason().equals(SpawnReason.CUSTOM))
			return;
		
		if (e.getType().equals(EntityType.SQUID))
		{
			event.setCancelled(true);
			return;
		}
		
		if (e.getType().equals(EntityType.ZOMBIE))
		{
			SpawnBonusZombies(e);
			return;
		}
		
		if (e.getType().equals(EntityType.CREEPER))
		{
			SpawnBonusZombies(e);
			return;
		}
		
		if (e.getType().equals(EntityType.SPIDER)
				&& e.getLocation().getBlockY() <= 66)
		{
			SpawnBonusZombies(e);
			return;
		}
		
		if (e.getLocation().getBlockY() <= 66
				&& e.getWorld().getHighestBlockAt(e.getLocation()).getY() <= 74
				&& (new Random()).nextBoolean())
		{
			Location newLoc = e.getWorld().getHighestBlockAt(e.getLocation()).getLocation();
			newLoc.add(0, 2, 0);
			e.teleport(newLoc);
		}
		
		e.remove();
		EntityManager.SpawnZombie(e.getLocation());
		SpawnBonusZombies(e);
	}

	public void SpawnBonusZombies(Entity e) {
		Random r = new Random();
		if (e.getLocation().getY() > 66 && r.nextBoolean())
		{
			double bonusSpawn = r.nextDouble() * 100;
			if (bonusSpawn > 99.5)
				for (int i = 0; i < 19; i++)
				{
					EntityManager.SpawnZombie(e.getLocation());
				}
			else if (bonusSpawn > 90)
				for (int i = 0; i < 14; i++)
				{
					EntityManager.SpawnZombie(e.getLocation());
				}
			else if (bonusSpawn > 75)
				for (int i = 0; i < 9; i++)
				{
					EntityManager.SpawnZombie(e.getLocation());
				}
			else if (bonusSpawn > 50)
				for (int i = 0; i < 3; i++)
				{
					EntityManager.SpawnZombie(e.getLocation());
				}
			else if (bonusSpawn > 25)
				for (int i = 0; i < 1; i++)
				{
					EntityManager.SpawnZombie(e.getLocation());
				}
		}
	}

	@Override
	public void run(EntityExplodeEvent event) {
		Entity e = event.getEntity();//
		if (!e.getWorld().equals(example6.getWorldManager().getZombieWorld()))
			return;
		
		MobMovement.handleNoise(e, 100);
	}
	
	private void handleZombieDeath(Player player)
	{

		PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
		cfg.setZombiesKills(cfg.getZombiesKills() + 1);
	}
	
	private void handlePlayerDeath(Player player)
	{
		PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
		
		long lifetime = cfg.getZombiesLifeTime() + 
				(Calendar.getInstance().getTimeInMillis()
						- cfg.getZombiesLastEnter());
		
		cfg.setZombiesLifeTime(
				lifetime
						);
		
		cfg.setZombified(null);
		
		//do the work
		int kills = cfg.getZombiesKills();
		
		double seconds = (lifetime / 1000L);
		double minutes = seconds / 60D;
		
		double money = (1 + kills) * (Math.log(minutes+1D)/Math.log(1.1));
		
		SendSurvivalTimeMessage(player, seconds);
		
		SendKillsMessage(player, kills);
		
		SendEarningsMessage(player, money);
		
		Economy economy = example6.getPlug().getEconomy();
		economy.depositPlayer(player.getName(), money);

		cfg.setZombiesKills(0);
		cfg.setZombiesLifeTime(0L);
	}
	
	public static String longTimeString(double seconds)
	{
		String value = "";
		
		if (seconds >= 86400)
		{
			int days = 0;
			while (seconds >= 86400)
			{
				seconds -= 86400;
				days++;
			}
			if (value.length() > 0)
				value += ", " + days + " days";
			else
				value = days + " days";
		}
		
		if (seconds >= 3600)
		{
			int hours = 0;
			while (seconds >= 3600)
			{
				seconds -= 3600;
				hours++;
			}
			if (value.length() > 0)
				value += ", " + hours + " hours";
			else
				value = hours + " hours";
		}
		
		if (seconds >= 60)
		{
			int minutes = 0;
			while (seconds >= 60)
			{
				seconds -= 60;
				minutes++;
			}
			if (value.length() > 0)
				value += ", " + minutes + " minutes";
			else
				value = minutes + " minutes";
		}
		
		if (value.length() > 0)
			value += " and " + seconds + " seconds";
		else
			value = ((int)seconds) + " seconds";
		
		return value;
	}

	public static void SendSurvivalTimeMessage(Player player, double seconds) {
		player.sendMessage(
						ChatColor.RED + "You have survived " + 
						ChatColor.YELLOW + longTimeString(seconds) +  
						ChatColor.RED + "."
						);
	}

	public static void SendKillsMessage(Player player, int kills) {
		player.sendMessage(
				ChatColor.RED + "You have killed " + 
				ChatColor.YELLOW + kills +  
				ChatColor.RED + " zombies."
				);
	}

	public static void SendEarningsMessage(Player player, double money) {
		player.sendMessage(
				ChatColor.RED + "You have earned " + 
				ChatColor.YELLOW + (new DecimalFormat("#.##")).format(money) +  
				ChatColor.RED + " Server Points."
				);
	}
	
	private void handlePlayerEnter(Player player)
	{
		PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
		cfg.setZombiesLastEnter(
				Calendar.getInstance().getTimeInMillis()
				);
				
		if (cfg.getZombified() > 0L)
			ZombiesCheckupTask.ApplyZombification(player);
	}
	
	private void handlePlayerExit(Player player)
	{
		player.removePotionEffect(PotionEffectType.BLINDNESS);
		player.removePotionEffect(PotionEffectType.CONFUSION);
		player.removePotionEffect(PotionEffectType.SLOW);
		player.removePotionEffect(PotionEffectType.HUNGER);
		
		PlayerConfig cfg = example6.getConfigManager().getPlayerConfig(player);
		cfg.setZombiesLifeTime(
				cfg.getZombiesLifeTime() + 
				(Calendar.getInstance().getTimeInMillis()
						- cfg.getZombiesLastEnter())
						);
	}

	private void handleInfection(Player e) {
		if (new Random().nextDouble() * 100 <= 6.25)
		{
			e.sendMessage(ChatColor.RED + "You have been scratched!");
			example6.getConfigManager().getPlayerConfig(e).setZombified(Calendar.getInstance().getTimeInMillis());
		}
	}

}
