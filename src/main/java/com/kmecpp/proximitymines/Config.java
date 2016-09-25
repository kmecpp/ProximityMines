package com.kmecpp.proximitymines;

import com.kmecpp.spongecore.config.Configurable;
import com.kmecpp.spongecore.config.SpongeConfig;

@SpongeConfig(header = "ProximityMines configuration file")
public enum Config {

	@Configurable(key = "mine-range", def = "4",
			comment = "Detection radius for proximity mines")
	MINE_RANGE;

	//	@Override
	//	public String getHeader() {
	//		return "Configuration file for " + ProximityMines.NAME + ". Author: " + ProximityMines.AUTHOR;
	//	}
	//
	//	@Override
	//	public void populate(IConfigSpec config) {
	//		config.setDefault("mines", "target-owner").setComment("Should players trigger their own proximity mines").setValue(false);
	//
	//		root.getNode("mines", "target-owner").setComment("Should players trigger their own proximity mines").setValue(false);
	//	}

}
