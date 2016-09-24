package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.Mine;

public class MineDestroyEvent extends AbstractMineEvent implements MineEvent.Destroy {

	public MineDestroyEvent(Player destroyer, Mine mine) {
		super(mine);
		this.player = destroyer;
	}

	@Override
	public Player getDestroyer() {
		return player;
	}

}