package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.Mine;

public class MineCreateEvent extends AbstractMineEvent implements MineEvent.Create {

	public MineCreateEvent(Player creator, Mine mine) {
		super(mine);
		this.player = creator;
	}

	@Override
	public Player getCreator() {
		return this.player;
	}

}