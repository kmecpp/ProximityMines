package com.kmecpp.proximitymines.event.events;

import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.event.AbstractMineEvent;
import com.kmecpp.proximitymines.event.MineEvent;
import com.kmecpp.proximitymines.mine.MineBlock;

public class MineExplodeEvent extends AbstractMineEvent implements MineEvent.Explode {

	public MineExplodeEvent(Player target, MineBlock mine) {
		super(target, mine);
	}

	@Override
	public Player getTarget() {
		return this.player;
	}

}
