package com.kmecpp.proximitymines;

import com.kmecpp.proximitymines.command.ProximityMinesCommand;
import com.kmecpp.spongecore.Initializer;
import com.kmecpp.spongecore.SpongeCore;

public class Initialization implements Initializer {

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		//Commands
		SpongeCore.registerCommand(new ProximityMinesCommand());

		//Listeners
		SpongeCore.registerListener(new EventListener());
	}

	@Override
	public void postInit() {

	}

}