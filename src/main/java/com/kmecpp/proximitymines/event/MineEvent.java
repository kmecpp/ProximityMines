package com.kmecpp.proximitymines.event;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

import com.kmecpp.proximitymines.mine.MineBlock;

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
