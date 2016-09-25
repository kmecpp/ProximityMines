package com.kmecpp.proximitymines.mine;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

public interface MineEvent extends Event, Cancellable {

	MineBlock getMine();

	public interface Create extends MineEvent {

		Player getCreator();

	}

	public interface Destroy extends MineEvent {

		Player getDestroyer();

	}

	public interface Explode extends MineEvent {

		Player getTarget();

	}

}