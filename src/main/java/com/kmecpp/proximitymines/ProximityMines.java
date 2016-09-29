package com.kmecpp.proximitymines;

import org.spongepowered.api.plugin.Plugin;

import com.kmecpp.proximitymines.command.ProximityMinesCommand;
import com.kmecpp.proximitymines.mine.mines.ContagiousMine;
import com.kmecpp.proximitymines.mine.mines.EndothermicMine;
import com.kmecpp.proximitymines.mine.mines.ExothermicMine;
import com.kmecpp.proximitymines.mine.mines.ShrapnelMine;
import com.kmecpp.proximitymines.mine.mines.SonicMine;
import com.kmecpp.proximitymines.mine.mines.Tier1Mine;
import com.kmecpp.proximitymines.mine.mines.Tier2Mine;
import com.kmecpp.proximitymines.mine.mines.Tier3Mine;
import com.kmecpp.proximitymines.mine.mines.Tier4Mine;
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
		setConfig(Config.class);
	}

	/*
	 * <<<----- TODO ----->>>
	 * 
	 * - Active mine list instead of scanning
	 * 
	 */

	@Override
	protected void preInit() {
		new Tier1Mine().register();
		new Tier2Mine().register();
		new Tier3Mine().register();
		new Tier4Mine().register();
		new SonicMine().register();
		new ShrapnelMine().register();
		new ContagiousMine().register();
		new ExothermicMine().register();
		new EndothermicMine().register();
	}

	@Override
	public void init() {
		//Commands
		ProximityMines.registerCommand(new ProximityMinesCommand());

		//Listeners
		ProximityMines.registerListener(new EventListener());
	}

}
