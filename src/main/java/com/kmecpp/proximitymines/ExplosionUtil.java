package com.kmecpp.proximitymines;

import java.util.concurrent.ThreadLocalRandom;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.explosion.Explosion;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public abstract class ExplosionUtil {

	private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

	public static void playSound(Location<World> location) {
		location.getExtent().playSound(SoundTypes.ENTITY_GENERIC_EXPLODE, location.getPosition(),
				2D, //Volume
				1D); //Pitch
	}

	public static void tnt(Location<World> location, float radius, Cause cause) {
		playSound(location);
		location.getExtent().triggerExplosion(Explosion.builder()
				.location(location)
				.shouldDamageEntities(true)
				.shouldPlaySmoke(true)
				.shouldBreakBlocks(true)
				.radius(radius)
				.build(), cause);
	}

	public static void shrapnel(Location<World> location, int radius, int count, Cause cause) {
		World world = location.getExtent();
		for (int i = 0; i < count; i++) {
			Entity arrow = world.createEntity(EntityTypes.TIPPED_ARROW, location.getPosition());
			arrow.setRotation(new Vector3d(90, 0, 0));
			arrow.setVelocity(new Vector3d(
					(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat(),
					RAND.nextFloat(),
					(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat()));
			//					RAND.nextInt(-3, 3),
			//					RAND.nextInt(1, 3),
			//					RAND.nextInt(-3, 3)));
			world.spawnEntity(arrow, cause);
		}
	}

	public static void kinetic(Location<World> location, int radius, boolean exothermic, Cause cause) {
		//		World world = location.getExtent();
	}

	public static void poison(Location<World> location, int radius, int severeity) {

	}

	public static void blast(Location<World> location, int radius, Cause cause) {
		Player target = cause.first(Player.class).get();
		target.playSound(SoundTypes.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, target.getLocation().getPosition(), 3D, 2D);
		ellipse(location, radius, radius / 2, radius, true, cause);
	}

	public static void sphere(Location<World> location, int radius, Cause cause) {
		ellipse(location, radius, radius, radius, false, cause);
	}

	public static void ellipse(Location<World> location, int radiusX, int radiusY, int radiusZ, boolean jagged, Cause cause) {
		final Vector3i center = location.getBlockPosition();
		for (int x = -radiusX; x <= radiusX; x++) {
			for (int z = -radiusZ; z <= radiusZ; z++) {
				for (int y = radiusY; y >= -radiusY; y--) { //Iterate downwards
					Vector3i point = location.getBlockPosition().add(x, y, z);
					if (Math.abs(center.getX() - point.getX()) < radiusX
							&& Math.abs(center.getY() - point.getY()) < radiusY
							&& Math.abs(center.getZ() - point.getZ()) < radiusZ) {
						location.setBlockType(BlockTypes.AIR, cause);
						if (jagged && RAND.nextFloat() < .60) {
							location.add(0, -1, 0).setBlockType(BlockTypes.AIR, cause);
						}
					}
				}
			}
		}
	}

	//	private static void ellipse(Location<World> location, int radius, int height, boolean jagged, Cause cause) {
	//		for (int x = -radius; x <= radius; x++) {
	//			for (int z = -radius; z <= radius; z++) {
	//				for (int y = height; y >= -height; y--) { //Iterate downwards
	//					Vector3i blockPos = location.getBlockPosition().add(x, y, z);
	//					float distance = blockPos.distance(location.getBlockPosition());
	//
	//					if (distance < radius) {
	//						//Remove center
	//						if (distance < radius - 3) {
	//							location.getExtent().setBlockType(blockPos, BlockTypes.AIR, ProximityMines.getPlugin().asCause());
	//						}
	//
	//						//Jagged edges
	//						else if (jagged && RAND.nextFloat() > .5 && location.add(0, 1, 0).getBlockType() == BlockTypes.AIR) {
	//							location.getExtent().setBlockType(blockPos, BlockTypes.AIR, ProximityMines.getPlugin().asCause());
	//						}
	//					}
	//				}
	//			}
	//		}
	//	}

}
