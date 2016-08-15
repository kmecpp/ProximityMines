package com.kmecpp.proximitymines.explosion;

import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.living.player.Player;

import com.kmecpp.proximitymines.ExplosionUtil;
import com.kmecpp.proximitymines.mine.Mine;
import com.kmecpp.proximitymines.mine.MineExplosion;

public class Tier4Explosion implements MineExplosion {

	@Override
	public void onExplode(Player target, Mine mine) {
		target.playSound(SoundTypes.EXPLODE, target.getLocation().getPosition(), 3D, 2D);
		ExplosionUtil.blast(target.getLocation(), 20);
	}

}