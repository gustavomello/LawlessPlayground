package org.example6.example6.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Config.PlayerConfig;
import org.example6.example6.Models.MysteryBox;

public class VoteCommand extends example6Command {

	public VoteCommand() {
		super("vote", "example6.vote");
	}
	
	private int addVote(String username) {
		
		
		//so we need to slowly migrate the vote count from votifier to lp
		//if LP doesn't have vote information, try to get it from old ways. in all
		//cases always save to LP
		
		if (example6.getConfigManager().hasPlayerConfig(username))
		{
			PlayerConfig cfg = example6.getConfigManager().getOfflinePlayerConfig(username);
			int newVotes = 0;
			
			if (cfg != null)
			{
				newVotes = cfg.getVotes() + 1;
				cfg.setVotes(newVotes);
			}
			
			return newVotes;
		}
		return -1;
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		
		String username = sender.getName();
		int newVotes = addVote(sender.getName());
		Player p = sender.getServer().getPlayer(username);
		
		if (p != null)
		{
			MysteryBox box = new MysteryBox(((Integer)newVotes).toString());
			box.giveToPlayer(p);
		}
		return true;
	}

}
