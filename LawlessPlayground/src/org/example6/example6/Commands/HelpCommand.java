package org.example6.example6.Commands;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.example6.example6.example6;
import org.example6.example6.Commands.CommandTypes.example6Command;
import org.example6.example6.Utils.MiscUtils;

import com.google.common.io.Files;


public class HelpCommand extends example6Command {
	static FileFilter filesOnly = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			return pathname.isFile();
		}
	};
	static FileFilter directoriesOnly = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			return pathname.isDirectory();
		}
	};
	public static File helpDir;

	public HelpCommand() {
		super("help", "example6.help");
		HelpCommand.helpDir = new File(example6.getConfigManager().getDataFolder(), "help");
	}
	/*
	public List<String> getSubTopics(File file)
	{
		List<String> returnVal = new ArrayList<String>();
		
		if (!file.isDirectory())
		{
			return returnVal;
		}
		
		returnVal.addAll(Arrays.asList(file.list()));
		
		return returnVal;
	}
	*/
	public File helpFile(File baseDir, String keyword)
	{
		Logger.getLogger("fdsa").info("Looking in " + baseDir.getAbsolutePath() + " for " + keyword);
		if (baseDir.getName().contains(keyword))
			return baseDir;
		
		for (File file : baseDir.listFiles(filesOnly))
		{
			if (file.getName().contains(keyword))
			{
				Logger.getLogger("fdsa").info(file.getName() + " contains " + keyword);
				return file;
			}
			else
			{

				Logger.getLogger("fdsa").info(file.getName() + " does not contain " + keyword);
			}
		}
		
		for (File file : baseDir.listFiles(directoriesOnly))
		{
			File subHelpFile = helpFile(file, keyword);
			if (subHelpFile != baseDir)
			{
				Logger.getLogger("fdsa").info(subHelpFile + " was not " + baseDir);
				return subHelpFile;
			}
			else
			{
				Logger.getLogger("fdsa").info(subHelpFile + " was " + baseDir);
			}
		}

		Logger.getLogger("fdsa").info("returning " + baseDir);
		return null;
	}

	@Override
	public Boolean Run(CommandSender sender, String command, String alias,
			String[] args) {
		File helpTarget;
		if (args.length == 0)
		{
			helpTarget = helpDir;
		}
		else
		{
			if (!StringUtils.isAlphanumeric(args[0]))
			{
				sender.sendMessage(ChatColor.YELLOW + "Sorry, could you type that again?");
				return true;
			}
			helpTarget = helpFile(helpDir, args[0]);
			if (helpTarget == null)
			{
				sender.sendMessage(ChatColor.YELLOW + "Sorry, I don't know anything about '" + args[0] + "'");
				return true;
			}
		}
		
		//if its a directory, its a topic, and needs to list subtopics
		if (helpTarget.isDirectory())
		{
			File defaultTarget = new File(helpTarget, "default.txt");
			if (!defaultTarget.exists())
				try {
					defaultTarget.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			displayHelpContents(sender, defaultTarget);
			displaySubTopics(sender, helpTarget);
		}
		else
		{
			displayHelpContents(sender, helpTarget);
		}
		return true;
	}
	
	public void displayHelpContents(CommandSender sender, File file)
	{
		try {
			List<String> lines = Files.readLines(file, Charset.defaultCharset());
			for (String line : lines)
			{
				sender.sendMessage(MiscUtils.colorize(StringEscapeUtils.unescapeJava(line)));
			}
			if (lines.size() == 0)
			{
				sender.sendMessage(ChatColor.YELLOW + "It loooks like this help file wasn't filled out yet!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displaySubTopics(CommandSender sender, File file)
	{
		sender.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.RED + "/help [" 
				+ ChatColor.YELLOW + "topic"
				+ ChatColor.RED + "]"
				+ChatColor.DARK_GREEN + " for more info.");
		sender.sendMessage(ChatColor.DARK_GREEN.toString()
				+ ChatColor.UNDERLINE + "'" + file.getName().replace(".txt",  "") + "' subtopics:");
		for (File f : file.listFiles())
		{
			if (!f.getName().contains("default.txt") && !f.getName().contains("~"))
				sender.sendMessage(ChatColor.GREEN + "    - " + ChatColor.YELLOW + f.getName().replace(".txt",  ""));
		}
	}
}
