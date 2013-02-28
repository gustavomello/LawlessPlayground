package org.example6.example6.Config;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.example6.example6.example6;


public class PlayerConfig extends Config {
	public interface PendingChange
	{
		static final String CLEAR_EXPERIENCE = "clearexp";
		static final String CLEAR_WORN_ITEMS = "clearwornitems";
		static final String CLEAR_INVENTORY = "clearinventory";
	}
	interface PlayerConfigKeys
	{
		static final String KITS = "kits";
		
		static final String SPELLS = "spells";
		
		static final String CREATIVE_BAN_MESSAGE = "creative.ban.message";
		static final String CREATIVE_BAN_ADMIN = "creative.ban.admin";
		static final String CREATIVE_BAN_DATE = "creative.ban.date";

		
		static final String PENDING_CHANGES = "pendingchanges";

		static final String JOIN_DATE = "login.first";
		
		static final String LAST_LOGIN = "login.last";
		
		static final String LAST_RANDOM_TELEPORT = "lastrandomteleport";

		static final String LOGIN_DAY_COUNT = "login.daycount";
		
		static final String VOTES = "votes";

		static final String SURVIVAL_LOCATION = "location.survival";
		static final String SURVIVAL_LOCATION_WORLD = "location.survival.world";
		static final String SURVIVAL_LOCATION_X = "location.survival.x";
		static final String SURVIVAL_LOCATION_Y = "location.survival.y";
		static final String SURVIVAL_LOCATION_Z = "location.survival.z";
		static final String SURVIVAL_LOCATION_PITCH = "location.survival.pitch";
		static final String SURVIVAL_LOCATION_YAW = "location.survival.yaw";

		static final String ZOMBIES_LOCATION = "location.zombies";
		static final String ZOMBIES_LOCATION_X = "location.zombies.x";
		static final String ZOMBIES_LOCATION_Y = "location.zombies.y";
		static final String ZOMBIES_LOCATION_Z = "location.zombies.z";
		static final String ZOMBIES_LOCATION_PITCH = "location.zombies.pitch";
		static final String ZOMBIES_LOCATION_YAW = "location.zombies.yaw";
		
		static final String ZOMBIES_LIFETIME = "zombies.lifetime";
		static final String ZOMBIES_LAST_ENTER = "zombies.lastenter";
		static final String ZOMBIES_KILLS = "zombies.kills";
		static final String ZOMBIFIED = "zombies.zombified";

		static final String CREATIVE_LOCATION = "location.creative";
		static final String CREATIVE_LOCATION_X = "location.creative.x";
		static final String CREATIVE_LOCATION_Y = "location.creative.y";
		static final String CREATIVE_LOCATION_Z = "location.creative.z";
		static final String CREATIVE_LOCATION_PITCH = "location.creative.pitch";
		static final String CREATIVE_LOCATION_YAW = "location.creative.yaw";
	}

	//make this be player specific
	public PlayerConfig(String player, File dataFolder) {
		super("userdata/" + player.toLowerCase() + ".yml", dataFolder);
	}
	
	public int getVotes()
	{
		return (int) this.get(PlayerConfigKeys.VOTES, 0);
	}
	
	public void setVotes(int value)
	{
		this.set(PlayerConfigKeys.VOTES, value);
		this.save();
	}
	
	public Boolean hasSpell(String spell)
	{
		return getSpells().contains(spell);
	}
	
	public List<String> getSpells()
	{
		return this.getStringList(PlayerConfigKeys.SPELLS);
	}
	
	public void setSpells(List<String> newSpells)
	{
		this.set(PlayerConfigKeys.SPELLS, newSpells);
		this.save();
	}

	public void addSpell(String value) {
		if (hasSpell(value)) return;
		
		List<String> newSpells = getSpells();
		newSpells.add(value);
		setSpells(newSpells);
	}
	
	public void deleteSpell(String spell)
	{
		if (!hasSpell(spell)) return;
		
		List<String> values = getSpells();
		values.remove(spell);
		setSpells(values);
	}
	
	public List<String> getBoxes()
	{
		return this.getStringList(PlayerConfigKeys.KITS);
	}
	
	public void setBoxes(List<String> newKits)
	{
		this.set(PlayerConfigKeys.KITS, newKits);
		this.save();
	}

	public void addBox(String value) {
		List<String> newBoxes = getBoxes();
		newBoxes.add(value);
		setBoxes(newBoxes);
	}
	
	public String getFirstBox()
	{
		List<String> values = getBoxes();
		if (values.size() > 0)
		{
			String value = values.get(0);
			return value;
		}
		return null;
	}
	
	public void deleteBox(String box)
	{
		List<String> values = getBoxes();
		values.remove(box);
		setBoxes(values);
	}
	
	public void addPendingChange(String change)
	{
		List<String> pendingchanges = this.getStringList(PlayerConfigKeys.PENDING_CHANGES);
		pendingchanges.add(change);
		this.set(PlayerConfigKeys.PENDING_CHANGES, pendingchanges);
		this.save();
	}
	
	public void clearPendingChanges()
	{
		this.set(PlayerConfigKeys.PENDING_CHANGES, null);
		this.save();
	}
	
	public List<String> getPendingChanges()
	{
		return this.getStringList(PlayerConfigKeys.PENDING_CHANGES);
	}

	public Location getSurvivalLocation()
	{
		if (!this.has(PlayerConfigKeys.SURVIVAL_LOCATION))
			return null;
		
		String worldName = this.get(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, "world").toString();
		World world = null;
		if (worldName.equals("world"))
			world = example6.getWorldManager().getOverworld();
		else if (worldName.equals("world_nether"))
			world = example6.getWorldManager().getNether();
		else if (worldName.equals("world_the_end"))
			world = example6.getWorldManager().getEnd();
		double x = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.SURVIVAL_LOCATION_YAW, 0);
		
		Location location = new Location(world, x, y, z);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setSurvivalLocation(Location location)
	{
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_WORLD, location.getWorld().getName());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearSurvivalLocation()
	{
		this.set(PlayerConfigKeys.SURVIVAL_LOCATION, null);
		this.save();
	}

	public Location getZombiesLocation()
	{
		if (!this.has(PlayerConfigKeys.ZOMBIES_LOCATION))
			return null;
		
		double x = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.ZOMBIES_LOCATION_YAW, 0);
		
		Location location = new Location(
				example6.getWorldManager().getZombieWorld(), x, y, z
				);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setZombiesLocation(Location location)
	{
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearZombiesLocation()
	{
		this.set(PlayerConfigKeys.ZOMBIES_LOCATION, null);
		this.save();
	}

	public Location getCreativeLocation()
	{
		if (!this.has(PlayerConfigKeys.CREATIVE_LOCATION))
			return null;
		
		double x = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_X, 0);
		double y = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Y, 64);
		double z = this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_Z, 0);
		float pitch = (float) this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_PITCH, 0);
		float yaw = (float) this.getDouble(PlayerConfigKeys.CREATIVE_LOCATION_YAW, 0);
		
		Location location = new Location(
				example6.getWorldManager().getCreativeWorld(), x, y, z
				);
		location.setPitch(pitch);
		location.setYaw(yaw);
		
		return location;
	}
	
	public void setCreativeLocation(Location location)
	{
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_X, location.getX());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Y, location.getY());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_Z, location.getZ());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_PITCH, location.getPitch());
		this.set(PlayerConfigKeys.CREATIVE_LOCATION_YAW, location.getYaw());
		this.save();
	}
	
	public void clearCreativeLocation()
	{
		this.set(PlayerConfigKeys.CREATIVE_LOCATION, null);
		this.save();
	}
	
	public long getZombiesLifeTime() {
		return this.getLong(PlayerConfigKeys.ZOMBIES_LIFETIME, 0L);
	}
	
	public void setZombiesLifeTime(long value) {
		this.set(PlayerConfigKeys.ZOMBIES_LIFETIME, value);
		this.save();
	}
	
	public long getZombiesLastEnter() {
		return this.getLong(PlayerConfigKeys.ZOMBIES_LAST_ENTER, 0L);
	}
	
	public void setZombiesLastEnter(long value) {
		this.set(PlayerConfigKeys.ZOMBIES_LAST_ENTER, value);
		this.save();
	}
	
	public int getZombiesKills() {
		return (int) this.get(PlayerConfigKeys.ZOMBIES_KILLS, 0);
	}
	
	public void setZombiesKills(int value) {
		this.set(PlayerConfigKeys.ZOMBIES_KILLS, value);
		this.save();
	}
	
	public long getLastLogin() {
		return (long) this.get(PlayerConfigKeys.LAST_LOGIN, 0L);
	}
	
	public void setLastLogin(long value) {
		this.set(PlayerConfigKeys.LAST_LOGIN, value);
		this.save();
	}
	
	public int getLoginCount() {
		return (int) this.get(PlayerConfigKeys.LOGIN_DAY_COUNT, 0);
	}
	
	public void setLoginCount(int value) {
		this.set(PlayerConfigKeys.LOGIN_DAY_COUNT, value);
		this.save();
	}

	public void setJoinDate(long timeInMillis) {
		this.set(PlayerConfigKeys.JOIN_DATE, timeInMillis);
		this.save();
	}
	
	public long getJoinDate() {
		return (long) this.get(PlayerConfigKeys.JOIN_DATE, 0L);
	}

	public void setLastRandom(long timeInMillis) {
		this.set(PlayerConfigKeys.LAST_RANDOM_TELEPORT, timeInMillis);
		this.save();
	}
	
	public long getLastRandom() {
		return (long) this.get(PlayerConfigKeys.LAST_RANDOM_TELEPORT, 0L);
	}

	public void setCreativeBanMessage(String message, CommandSender sender) {
		this.set(PlayerConfigKeys.CREATIVE_BAN_MESSAGE, message);
		this.set(PlayerConfigKeys.CREATIVE_BAN_ADMIN, sender.getName());
		this.set(PlayerConfigKeys.CREATIVE_BAN_DATE, Calendar.getInstance().getTimeInMillis());
		this.save();
	}
	
	public String getCreativeBanMessage() {
		return (String) this.get(PlayerConfigKeys.CREATIVE_BAN_MESSAGE, null);
	}
	
	public String getCreativeBanAdmin() {
		return (String) this.get(PlayerConfigKeys.CREATIVE_BAN_ADMIN, null);
	}
	
	public long getCreativeBanDate() {
		return (long) this.getLong(PlayerConfigKeys.CREATIVE_BAN_DATE, 0L);
	}
	
	public long getZombified() {
		return (long) this.getLong(PlayerConfigKeys.ZOMBIFIED, 0L);
	}
	
	public void setZombified(Object ms) {
		this.set(PlayerConfigKeys.ZOMBIFIED, ms);
	}
}
