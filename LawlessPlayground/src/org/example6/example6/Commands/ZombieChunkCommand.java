package org.example6.example6.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Config.ChunkConfig;
import org.example6.example6.Models.ChunkGroup;
import org.example6.example6.Tasks.ChunkRegenTask;

public class ZombieChunkCommand extends example6Command {

	public ZombieChunkCommand() {
		super("chunk", "example6.chunk");
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		if (sender instanceof Player)
		{
			Player player = (Player)sender;
			if (args.length==0)
			{
				SendUsage(sender);
			}
			else
			{
				ChunkConfig cfg = example6.getConfigManager().getChunkConfig();
				if (args[0].equalsIgnoreCase("list"))
				{
					int page = 1;
					if (args.length == 2)
						page = Integer.parseInt(args[1]);
					ListChunkGroups(player, page);
				}
				else if (args[0].equalsIgnoreCase("info"))
				{
					ChunkGroup group = null;
					if (args.length == 2)
						group = example6.getConfigManager().getChunkConfig().getChunkGroupByName(args[1]);
					else
						group = cfg.getChunkGroupByChunk
							(player.getLocation().getChunk());
					SendChunkGroupInfo(player, group);
				}
				else if (args[0].equalsIgnoreCase("regen"))
				{
					RegenChunkGroup(player);
				}
				else if (args[0].equalsIgnoreCase("delete"))
				{
					if (args.length < 2)
						sender.sendMessage(ChatColor.RED + "Usage: /c delete [chunkgroup]");
					DeleteChunkGroup(player, args[1]);
				}
				else if (args[0].equalsIgnoreCase("put"))
				{
					if (args.length < 2)
						sender.sendMessage(ChatColor.RED + "Usage: /c put [chunkgroup]");
					SetChunkGroup(player, args[1]);
				}
				else if (args[0].equalsIgnoreCase("free"))
				{
					FreeChunk(player);
				}
				else if (args[0].equalsIgnoreCase("title"))
				{
					String name = "";
					for (int i = 1; i < args.length; i++)
					{
						if (name.length() > 0) name += " ";
						name += args[i];
					}
					SetChunkGroupTitle(player, name);
				}
			}
		}
		return true;
	}

	private void FreeChunk(Player player) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		ChunkGroup group = cfg.getChunkGroupByChunk(
					player.getLocation().getChunk()
				);
		if (group == null)
		{
			player.sendMessage(ChatColor.RED 
					+ "There is no ChunkGroup with a Chunk at your location.");
		}
		else
		{
			group.removeChunk(player.getLocation().getChunk());
			player.sendMessage(ChatColor.YELLOW 
					+ "Chunk successfully removed from '" + group.getName() + "'");
			if (group.getChunks(player.getWorld()).size()==0)
			{
				cfg.deleteChunkGroup(group.getName());
				player.sendMessage(ChatColor.RED 
					+ "ChunkGroup had 0 chunks left, so it was destroyed!");
			}
			
		}
	}

	private void ListChunkGroups(Player player, int page) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		page = page - 1;
		List<ChunkGroup> groups = cfg.getChunkGroups();
		int pages = (int) Math.ceil((groups.size()) / 10);
		if (page < 0) page = 0;
		if (page > pages) page = pages;
		player.sendMessage(ChatColor.YELLOW + "All ChunkGroups (Page "+(page+1)+" of "+(pages+1)+")");
		for (int i = 1; i <= 10; i++)
		{
			int current = i + (page*10);
			if (current > groups.size()) break;
			ChunkGroup g = groups.get(current - 1);
			String message = "  - " + g.getName();
			if (g.hasTitle()) 
				message = ChatColor.YELLOW + message + " (" + g.getTitle() + ")";
			else
				message = ChatColor.RED + message;
			player.sendMessage(message);
		}
	}

	private void DeleteChunkGroup(Player sender, String string) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		cfg.deleteChunkGroup(string);
		cfg.saveChunkGroups();
		sender.sendMessage(ChatColor.YELLOW + "ChunkGroup deleted.");
	}

	private void RegenChunkGroup(Player player) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		Chunk myChunk = player.getLocation().getChunk();
		ChunkGroup current = cfg.getChunkGroupByChunk(myChunk);
		if (current==null)
		{
			player.sendMessage(ChatColor.RED + "This chunk isn't in a group yet.");
			return;
		}
		player.sendMessage(ChatColor.YELLOW + "Regenerating ChunkGroup...");
		ChunkRegenTask task = new ChunkRegenTask();
		for (Chunk chunk : current.getChunks(example6.getWorldManager().getZombieWorld()))
		{
			Chunk source = example6.getWorldManager().getCityDev().getChunkAt(
					chunk.getX(), 
					chunk.getZ()
					);
			task.makeChunkRegen(source, chunk);
		}
		
		example6.getTaskManager().RunTaskTimer(task, 1L, 1L);
		current.setChangeCount(0);
		cfg.saveChunkGroups();
	}

	private void SetChunkGroupTitle(Player player, String string) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		Chunk chunk = player.getLocation().getChunk();
		ChunkGroup current = cfg.getChunkGroupByChunk(chunk);
		if (current == null)
		{
			player.sendMessage(ChatColor.RED + "This chunk isn't in a group yet.");
			return;
		}
		current.setTitle(string);
		cfg.saveChunkGroups();
		player.sendMessage(ChatColor.YELLOW + "Group '" + current.getName() + "' title set to '"
				+ current.getTitle() + "'");
	}

	public void SetChunkGroup(Player player, String name) {
		ChunkConfig cfg = example6.getConfigManager().getChunkConfig();;
		Chunk chunk = player.getLocation().getChunk();
		ChunkGroup current = cfg.getChunkGroupByChunk(chunk);
		
		//if current chunk is in a group, remove it
		if (current != null) current.removeChunk(chunk);
		
		ChunkGroup desired =
				cfg.getChunkGroupByName(name);
		
		if (desired != null)
			desired.addChunk(chunk);
		else
			desired = cfg.createNamedChunkGroup(name, chunk);
		
		cfg.saveChunkGroups();

		player.sendMessage(ChatColor.YELLOW + "Put chunk " + chunk.getX() + "," + chunk.getZ() 
				+ " in " + desired.getTitle());
	}

	public void SendChunkGroupInfo(Player player, ChunkGroup group) {
		if (group != null)
		{
			player.sendMessage(ChatColor.YELLOW + "ChunkGroup (" + group.getName() + ")");
			player.sendMessage(ChatColor.YELLOW + "  Title: " + group.getTitle() + "");
			player.sendMessage(ChatColor.YELLOW + "  Chunks:");
			List<Chunk> chunks = group.getChunks(player.getWorld());
			for (int i = 0; i < chunks.size(); i++)
			{
				String message = "    ";
				message += chunks.get(i).getX() + ";" + chunks.get(i).getZ();
				if (chunks.get(i).equals(group.getBase(player.getWorld())))
				{
					message += " (Base)";
				}
				player.sendMessage(ChatColor.YELLOW + message);
			}
		}
		else
			player.sendMessage(ChatColor.RED + "Couldn't find the desired ChunkGroup...");
	}

	public void SendUsage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Usage:");
		sender.sendMessage(ChatColor.RED + "  /chunk add [desired_group]");
		sender.sendMessage(ChatColor.RED + "  /chunk remove");
		sender.sendMessage(ChatColor.RED + "  /chunk clear [desired_group]");
		sender.sendMessage(ChatColor.RED + "  /chunk title [desired_group]");
		sender.sendMessage(ChatColor.RED + "  /chunk regen");
	}

}
