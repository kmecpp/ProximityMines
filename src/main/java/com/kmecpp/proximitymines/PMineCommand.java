package com.kmecpp.proximitymines;

import com.kmecpp.osmium.api.command.Chat;
import com.kmecpp.osmium.api.command.Command;

public class PMineCommand extends Command {

	public PMineCommand() {
		super("pmine", "proximitymines", "pmines");

		add("info").setExecutor(e -> {
			e.sendTitle("Proximity Mines Plugin Information");
			e.sendMessage(Chat.GREEN + "Authors: ");
			e.sendMessage(Chat.GREEN + "Version: ");
			e.sendMessage(Chat.GREEN + "Website: ");
		});
	}

}
