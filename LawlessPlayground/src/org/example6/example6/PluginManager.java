package org.example6.example6;

import net.milkbowl.vault.economy.Economy;
import com.onarandombox.MultiverseCore.MultiverseCore;

public class PluginManager {
	
	private Economy economy;
	
	private MultiverseCore mvcore;
	
	private example6 ex;

	public PluginManager(example6 plugin) {
		this.ex = plugin;
	}

	public Economy getEconomy() {
		return this.economy;
	}

	public example6 getExample6() {
		return this.ex;
	}

	public void setEconomy(Economy economy) {
		this.economy = economy;
	}

	public MultiverseCore getMvcore() {
		return mvcore;
	}

	public void setMvcore(MultiverseCore mvcore) {
		this.mvcore = mvcore;
	}

}
