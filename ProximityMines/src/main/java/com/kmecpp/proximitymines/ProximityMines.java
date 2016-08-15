package com.kmecpp.proximitymines;

import org.spongepowered.api.plugin.Plugin;

import com.kmecpp.spongecore.ConfigurationSpec;
import com.kmecpp.spongecore.Initializer;
import com.kmecpp.spongecore.SpongeCore;

@Plugin(id = ProximityMines.ID,
		name = ProximityMines.NAME,
		url = ProximityMines.GITHUB,
		version = ProximityMines.VERSION,
		authors = { ProximityMines.AUTHOR })
public class ProximityMines extends SpongeCore {

	public static final String ID = "proximitymines";
	public static final String NAME = "ProximityMines";
	public static final String GITHUB = "https://github.com/kmecpp/ProximityMines";
	public static final String VERSION = "1.0";
	public static final String AUTHOR = "kmecpp";

	/*
	 * <<<----- TODO ----->>>
	 * 
	 * - Active mine list instead of scanning
	 * 
	 */

	@Override
	public String getPluginName() {
		return NAME;
	}

	@Override
	public Initializer getInitializer() {
		return new Initialization();
	}

	@Override
	public ConfigurationSpec getConfigurationSpec() {
		return new Config();
	}

}