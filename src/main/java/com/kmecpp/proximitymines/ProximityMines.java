package com.kmecpp.proximitymines;

import org.spongepowered.api.plugin.Plugin;

import com.kmecpp.proximitymines.command.ProximityMinesCommand;
import com.kmecpp.spongecore.SpongePlugin;

@Plugin(id = ProximityMines.ID,
		name = ProximityMines.NAME,
		url = ProximityMines.GITHUB,
		version = ProximityMines.VERSION,
		authors = { ProximityMines.AUTHOR })
public class ProximityMines extends SpongePlugin {

	public static final String ID = "proximitymines";
	public static final String NAME = "ProximityMines";
	public static final String GITHUB = "https://github.com/kmecpp/ProximityMines";
	public static final String VERSION = "1.0";
	public static final String AUTHOR = "kmecpp";

	public ProximityMines() {
		setConfigurationSpec(new Config());
	}

	/*
	 * <<<----- TODO ----->>>
	 * 
	 * - Active mine list instead of scanning
	 * 
	 */

	@Override
	public void init() {
		//Commands
		ProximityMines.registerCommand(new ProximityMinesCommand());

		//Listeners
		ProximityMines.registerListener(new EventListener());
	}

}
