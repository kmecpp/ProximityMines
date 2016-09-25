package com.kmecpp.proximitymines.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.impl.AbstractEvent;

import com.kmecpp.proximitymines.mine.MineBlock;

public abstract class AbstractMineEvent extends AbstractEvent implements MineEvent {

	protected final MineBlock mine;
	protected final Player player;

	private boolean cancelled;

	public AbstractMineEvent(Player player, MineBlock mine) {
		this.mine = mine;
		this.player = player;
	}

	@Override
	public MineBlock getMine() {
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
