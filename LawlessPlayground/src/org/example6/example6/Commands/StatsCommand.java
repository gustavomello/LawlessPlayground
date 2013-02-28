package org.example6.example6.Commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Config.PlayerConfig;

public class StatsCommand extends example6Command {

	public StatsCommand() {
		super("stats", "example6.stats");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			PlayerConfig pc = example6.getConfigManager().getPlayerConfig(p);
			
			String dateString = "an unknown date.";
			
			if (pc.getJoinDate() > 0L)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy.");
				Calendar joinDate = Calendar.getInstance();
				joinDate.setTimeInMillis(pc.getJoinDate());
				dateString = sdf.format(joinDate.getTime());
			}
			
			sender.sendMessage(ChatColor.YELLOW + "You first joined on " + dateString);
			sender.sendMessage(ChatColor.YELLOW + "You have voted " + pc.getVotes() + " times.");
		}
		
		return true;
	}

}
