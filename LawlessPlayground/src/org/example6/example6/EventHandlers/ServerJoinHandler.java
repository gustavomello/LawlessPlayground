package org.example6.example6.EventHandlers;

import java.util.Calendar;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.example6.example6.example6;
import org.example6.example6.Commands.RandomCommand;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerJoinHandler;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerLoginHandler;
import org.example6.example6.Models.MysteryBox;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ServerJoinHandler implements PlayerJoinHandler, PlayerLoginHandler {
	
	private enum WorldDestination
	{
		Zombies,
		Survival,
		Creative
	}
	
	private HashMap<Player,WorldDestination> destination = new HashMap<Player,WorldDestination>();

	@Override
	public void run(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (!player.hasPlayedBefore())
		{
			SetInitialLocation(event, player);
			InitializeNewPlayer(event, player);
		}
		else
		{
			PlayerConfig pc = example6.getConfigManager().getPlayerConfig(player);
			if (PlayerReturnedNewDay(pc))
			{
				RewardPlayerForReturn(player, pc);
			}
		}
		
	}

	public void RewardPlayerForReturn(Player player, PlayerConfig pc) {
		int newLoginCount = pc.getLoginCount() + 1;
		pc.setLastLogin(Calendar.getInstance().getTimeInMillis());
		pc.setLoginCount(newLoginCount);
		
		player.sendMessage(ChatColor.YELLOW + "Welcome back.");
		if (newLoginCount == 2)
		{
		}
		else if (newLoginCount % 3 == 2)
		{
			//will work for 2nd login and every 3rd one afterward
			//MysteryBox box = new MysteryBox("100");
			//box.giveToPlayer(player);
		}
	}

	public boolean PlayerReturnedNewDay(PlayerConfig pc) {
		return Math.floor(Calendar.getInstance().getTimeInMillis() / 86400000) 
				> Math.floor(pc.getLastLogin() / 86400000);
	}

	public void InitializeNewPlayer(PlayerJoinEvent event, Player player) {
		//give starter kit
		
		MysteryBox box = new MysteryBox("Starter");
		box.giveToPlayer(player);
		
		//set join date
		example6.getConfigManager().getPlayerConfig(player).setJoinDate(Calendar.getInstance().getTimeInMillis());

		//add to creativeaccess
		PermissionUser user = PermissionsEx.getUser(player);
		if (!user.isRanked("creative"))
			user.addGroup("CreativeAccess");
	}

	public void SetInitialLocation(PlayerJoinEvent event, Player player) {
		Location newloc = null;
		if (this.destination.get(player).equals(WorldDestination.Zombies))
		{
			newloc = example6.getPlug().getMvcore()
				.getMVWorldManager()
				.getMVWorld(example6.getWorldManager().getZombieWorld())
				.getSpawnLocation();
		} 
		else if (this.destination.get(player).equals(WorldDestination.Creative))
		{
			newloc = example6.getPlug().getMvcore()
				.getMVWorldManager()
				.getMVWorld(example6.getWorldManager().getCreativeWorld())
				.getSpawnLocation();
		}
		else
		{
			newloc = RandomCommand.GetRandomLocation(player, 100);
		}
		
		player.teleport(newloc);
		this.destination.remove(player);
		
		
	}

	@Override
	public void run(PlayerLoginEvent event) {
		System.out.println("Login event!");
		if (event.getHostname().startsWith("zombie"))
		{
			this.destination.put(event.getPlayer(), WorldDestination.Zombies);
			System.out.println("sending to zomb");
			return;
		}

		if (event.getHostname().startsWith("creative"))
		{
			this.destination.put(event.getPlayer(), WorldDestination.Creative);
			return;
		}
		
		this.destination.put(event.getPlayer(), WorldDestination.Survival);
		
	}

}
