package com.kmecpp.proximitymines.mine.mines;

import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.MineBlock;
import com.kmecpp.proximitymines.mine.MineExplosion;

public class Tier4Mine implements MineExplosion {

	@Override
	public void onExplode(Player target, MineBlock mine) {
		target.playSound(SoundTypes.ENTITY_GENERIC_EXPLODE, target.getLocation().getPosition(), 3D, 2D);
		ExplosionUtil.blast(target.getLocation(), 20);
	}

}
