package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.MineBlock;

public class MineDestroyEvent extends AbstractMineEvent implements MineEvent.Destroy {

	public MineDestroyEvent(Player destroyer, MineBlock mine) {
		super(destroyer, mine);
	}

	@Override
	public Player getDestroyer() {
		return this.player;
	}

}
