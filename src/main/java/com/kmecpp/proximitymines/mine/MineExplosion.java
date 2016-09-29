package com.kmecpp.proximitymines.mine;

import java.util.concurrent.ThreadLocalRandom;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;

public interface MineExplosion {

	public static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

	public void onExplode(Player target, MineBlock mine, Cause cause);

}
