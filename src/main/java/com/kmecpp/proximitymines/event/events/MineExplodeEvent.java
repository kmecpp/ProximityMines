package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.Mine;

public class MineExplodeEvent extends AbstractMineEvent implements MineEvent.Explode {

	public MineExplodeEvent(Player target, Mine mine) {
		super(mine);
		this.player = target;
	}

	@Override
	public Player getTarget() {
		return player;
	}

}