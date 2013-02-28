package org.example6.example6.Commands;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.PlayerCommand;
import org.example6.example6.Utils.EntityManager;

public class CrystalCommand extends PlayerCommand {

	public CrystalCommand() {
		super("crystal", "example6.crystal");
	}

	@Override
	public Boolean RunAsPlayer(Player player, String command, String alias,
			String[] args) {

		Block targetBlock = player.getTargetBlock(null, 50);
		if (args.length > 0)
		{
			switch (args[0])
			{
			case "reload":
				
				//delete all in world
				example6.getConfigManager().getSpellConfig().reloadFromConfig();
				break;
			case "create":
				if (args.length > 1)
				example6.getConfigManager().getSpellConfig().addCrystal(args[1], targetBlock);
				break;
			case "remove":
				if (args.length > 1)
				example6.getConfigManager().getSpellConfig().removeCrystal(args[1]);
				break;
			}
		}
		else
		{
			EntityManager.SpawnEnderCrystal(targetBlock);
		}
		
		return true;
	}
}
