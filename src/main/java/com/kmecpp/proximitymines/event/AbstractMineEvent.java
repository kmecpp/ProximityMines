package com.kmecpp.proximitymines.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;

import com.kmecpp.proximitymines.mine.Mine;

public abstract class AbstractMineEvent implements MineEvent {

	private Mine mine;
	private boolean cancelled;

	protected Player player;

	public AbstractMineEvent(Mine mine) {
		this.mine = mine;
	}

	@Override
	public Mine getMine() {
		return mine;
	}

	@Override
	public Cause getCause() {
		return Cause.of(NamedCause.source(player));
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
