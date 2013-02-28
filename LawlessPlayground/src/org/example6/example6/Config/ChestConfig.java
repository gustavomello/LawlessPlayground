package org.example6.example6.Config;

import java.io.File;

public class ChestConfig extends Config {

	public ChestConfig(File dataFolder) {
		super("chests/chunks.yml", dataFolder);
		this.loadChests();
	}

	private void loadChests() {
		// TODO Auto-generated method stub
		
	}

}
