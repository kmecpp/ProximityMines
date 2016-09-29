package com.kmecpp.proximitymines;

import com.kmecpp.spongecore.config.ConfigKey;
import com.kmecpp.spongecore.config.SpongeConfig;

@SpongeConfig(header = "ProximityMines configuration file")
public class Config {

	public static final ConfigKey MINE_RANGE = new ConfigKey("mine-range", "4", "Detection radius for proximity mines");

}
