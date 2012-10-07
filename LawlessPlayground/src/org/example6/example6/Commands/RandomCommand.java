package org.example6.example6.Commands;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.PlayerCommand;

public class RandomCommand extends PlayerCommand {

	public RandomCommand(example6 plugin) {
		super("random", "example6.random", plugin);
	}
	
	public int generatePosNegRandomInt(Random generator, int size)
	{
		int randompos = generator.nextInt(size);
		return (generator.nextBoolean()) ? randompos * -1 : randompos;
	}

	@Override
	public Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {
		World w = player.getWorld();
		
		int borderWidth = 4500;
		if (args.length > 0)
		{
			borderWidth = Integer.parseInt(args[0]);
		}
		
		Random generator = new Random();
		Location location = null;
		while (location == null)
		{
			int randomposX = generatePosNegRandomInt(generator, borderWidth);
			int randomposZ = generatePosNegRandomInt(generator, borderWidth);
			
			Location targetLocation = w.getHighestBlockAt(randomposX, randomposZ).getLocation();
			if (!(targetLocation.getBlock().isLiquid()
					|| targetLocation.getBlock().getRelative(BlockFace.UP).isLiquid()))
				location = targetLocation;
		}
		player.teleport(location.add(0, 2, 0));
		return true;
	}
}
