package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.MineBlock;

public class MineCreateEvent extends AbstractMineEvent implements MineEvent.Create {

	public MineCreateEvent(Player creator, MineBlock mine) {
		super(creator, mine);
	}

	@Override
	public Player getCreator() {
		return this.player;
	}

}
