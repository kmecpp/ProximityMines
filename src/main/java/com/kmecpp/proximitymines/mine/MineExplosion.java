package com.kmecpp.proximitymines.mine;

import org.spongepowered.api.entity.living.player.Player;

public interface MineExplosion {

	public void onExplode(Player target, MineBlock mine);

}
