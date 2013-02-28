package org.example6.example6.Commands;

import java.util.Calendar;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.PlayerCommand;
import org.example6.example6.Config.PlayerConfig;

public class RandomCommand extends PlayerCommand {

	public RandomCommand() {
		super("random", "example6.random");
	}
	
	public static int generatePosNegRandomInt(Random generator, int size)
	{
		int randompos = generator.nextInt(size);
		return (generator.nextBoolean()) ? randompos * -1 : randompos;
	}

	@Override
	public Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {
		if (!player.getWorld().getName().equalsIgnoreCase("world"))
		{
			player.sendMessage(ChatColor.RED
					+ "You can't do that in this world."
					);
			return true;
		}
		
		PlayerConfig pc = example6.getConfigManager().getPlayerConfig(player);
		
		if (pc.getLastRandom() + 3600000 > Calendar.getInstance().getTimeInMillis()
				&& !player.hasPermission("example6.bypass"))
		{
			int minutes = (int) (((pc.getLastRandom() + 3600000) - Calendar.getInstance().getTimeInMillis()) / 1000);
			player.sendMessage(ChatColor.RED
					+ "You must wait " + minutes + " seconds before doing that again."
					);
		}
		else
		{
			int borderWidth = 2000;
			if (args.length > 0 && player.hasPermission("example6.random.args"))
			{
				borderWidth = Integer.parseInt(args[0]);
			}
			
			player.teleport(GetRandomLocation(player, borderWidth));
			pc.setLastRandom(Calendar.getInstance().getTimeInMillis());
		}
		
		return true;
	}
	
	public static Location GetRandomLocation(Player player, int radius)
	{
		Random generator = new Random();
		Location location = null;
		World w = player.getWorld();
		while (location == null)
		{
			int randomposX = generatePosNegRandomInt(generator, radius);
			int randomposZ = generatePosNegRandomInt(generator, radius);
			
			Location targetLocation = w.getHighestBlockAt(randomposX, randomposZ).getLocation();
			if (!(targetLocation.getBlock().isLiquid()
					|| targetLocation.getBlock().getRelative(BlockFace.UP).isLiquid()))
				location = targetLocation;
		}
		location.add(0, 2, 0);
		
		return location;
	}
}
