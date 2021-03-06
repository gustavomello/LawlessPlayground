package org.example6.example6.EventHandlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.example6.example6.EventHandlers.HandlerTypes.PlayerChatHandler;

public class ZombieChatHandler implements PlayerChatHandler {

	@Override
	public void run(AsyncPlayerChatEvent event) {
		if (event.getPlayer().hasPermission("example6.allchat"))
			return;
		
		Player player = event.getPlayer();
		
		List<Player> recipients = new ArrayList<Player>();
		List<Player> context = new ArrayList<Player>();
		if (player.getWorld().getName().equalsIgnoreCase("zombies"))
		{
			context = getNearbyPlayers(player, 150);
			if (context.size() == 0)
			{
				player.sendMessage(ChatColor.RED + "Nobody is around to hear your voice.");
			}
		}
		else
		{
			for(Player p : event.getPlayer().getServer().getOnlinePlayers())
			{
				if (!p.getWorld().getName().equalsIgnoreCase("zombies"))
					context.add(p);
			}
		}
		
		//add allchat members
		List<Player> allchat = this.getAllchatRecipients(event.getPlayer().getServer());

		recipients.add(player);
		recipients.addAll(allchat);
		recipients.addAll(context);
				
		event.getRecipients().clear();
		event.getRecipients().addAll(recipients);
	}
	
	public List<Player> getAllchatRecipients(Server server)
	{
		List<Player> players = new ArrayList<Player>();
		for (Player player : server.getOnlinePlayers())
		{
			if (player.hasPermission("example6.allchat"))
				players.add(player);
		}
		return players;
	}
	
	public List<Player> getNearbyPlayers(Player player, double maxLength)
	{
		List<Player> players = player.getWorld().getPlayers();
		List<Player> nearbyPlayers = new ArrayList<Player>();
		for (Player p : players)
		{
			if (p.getLocation().distance(player.getLocation()) < maxLength)
				nearbyPlayers.add(p);
		}
		
		return nearbyPlayers;
	}
}
