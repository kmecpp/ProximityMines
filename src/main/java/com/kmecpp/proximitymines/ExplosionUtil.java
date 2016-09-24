package com.kmecpp.proximitymines;

import java.util.Random;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.explosion.Explosion;

import com.flowpowered.math.vector.Vector3i;

public abstract class ExplosionUtil {

	private static final Random RANDOM = new Random();

	public static void tnt(Location<World> location, float radius) {
		location.getExtent().triggerExplosion(Explosion.builder()
				.location(location)
				.shouldDamageEntities(true)
				.shouldBreakBlocks(true)
				.radius(radius)
				.build());
	}

	public static void blast(Location<World> location, int radius) {
		ellipse(location, radius, (int) (radius / 1.5), true);
	}

	//	private static void sphere(Location<World> location, int radius) {
	//		ellipse(location, radius, radius);
	//	}

	private static void ellipse(Location<World> location, int radius, int height, boolean jagged) {
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = height; y >= -height; y--) { //Iterate downwards
					Vector3i blockPos = location.getBlockPosition().add(x, y, z);
					float distance = blockPos.distance(location.getBlockPosition());

					if (distance < radius) {
						//Remove center
						if (distance < radius - 3) {
							location.getExtent().setBlockType(blockPos, BlockTypes.AIR, ProximityMines.asCause());
						}

						//Jagged edges
						else if (jagged && RANDOM.nextFloat() > .5 && location.add(0, 1, 0).getBlockType() == BlockTypes.AIR) {
							location.getExtent().setBlockType(blockPos, BlockTypes.AIR, ProximityMines.asCause());
						}
					}
				}
			}
		}
	}

}
