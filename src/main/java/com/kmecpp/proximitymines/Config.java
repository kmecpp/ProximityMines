package com.kmecpp.proximitymines;

import com.kmecpp.spongecore.ConfigurationSpec;

public class Config extends ConfigurationSpec {

	@Override
	public String getHeader() {
		return "Configuration file for " + ProximityMines.NAME + ". Author: " + ProximityMines.AUTHOR;
	}

	@Override
	public void populate(IConfigSpec config) {
		//		config.setDefault("mines", "target-owner").setComment("Should players trigger their own proximity mines").setValue(false);

		//		root.getNode("mines", "target-owner").setComment("Should players trigger their own proximity mines").setValue(false);
	}

}
