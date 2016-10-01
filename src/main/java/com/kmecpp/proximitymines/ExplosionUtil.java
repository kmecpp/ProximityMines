package com.kmecpp.proximitymines;

import java.util.concurrent.ThreadLocalRandom;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
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
			arrow.offer(Keys.IS_AFLAME, true);
			arrow.setRotation(new Vector3d(0, 90, 0));
			arrow.setVelocity(new Vector3d(
					(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat(),
					RAND.nextFloat(),
					(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat()).mul(1.5));
			world.spawnEntity(arrow, cause);
		}

		//		float amountToRotate = 360 / radius;
		//		for (int r = 0; r < radius; r++) {
		//			float yaw = 0.0F + amountToRotate * r;
		//			for (int i = 0; i < radius; i++) {
		//				Entity arrow = world.createEntity(EntityTypes.TIPPED_ARROW, location.getPosition());
		//				arrow.offer(Keys.IS_AFLAME, true);
		//
		//				float pitch = 0.0F + amountToRotate * i;
		//
		//				arrow.setLocationAndRotation(location.add(new Vector3d(
		//						-(Math.cos(yaw / 180.0F * (float) Math.PI) * 0.16F),
		//						-0.10000000149011612D,
		//						-(Math.sin(yaw / 180.0F * (float) Math.PI) * 0.16F))),
		//						new Vector3d(yaw, pitch, 0D));
		//				arrow.setVelocity(new Vector3d(
		//						(-Math.sin(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI)),
		//						(Math.cos(yaw / 180.0F * (float) Math.PI) * Math.cos(pitch / 180.0F * (float) Math.PI)),
		//						(-Math.sin(pitch / 180.0F * (float) Math.PI))));
		//				arrow.setVelocity(new Vector3d(
		//						arrow.getVelocity().getX() * RAND.nextFloat(),
		//						arrow.getVelocity().getY() * RAND.nextFloat(),
		//						arrow.getVelocity().getZ() * RAND.nextFloat()));
		//				world.spawnEntity(arrow, cause);
		//			}
		//		}

	}

	public static void kinetic(Location<World> location, int radius, boolean exothermic, Cause cause) {
		//		World world = location.getExtent();
	}

	public static void poison(Location<World> location, int radius, int severeity) {

	}

	public static void blast(Location<World> location, int radius, Cause cause) {
		playSound(location);
		sphere(location, radius, true, cause);
		//		ellipse(location, radius, (int) (radius / 1.2), true, cause);
	}

	public static void sphere(Location<World> location, int radius, boolean jagged, Cause cause) {
		for (int x = -radius; x <= radius; x++) {
			for (int z = -radius; z <= radius; z++) {
				for (int y = -radius; y <= radius; y++) {
					Location<World> point = location.add(x, y, z);
					if (point.getPosition().distance(location.getPosition()) <= radius) {
						point.setBlockType(BlockTypes.AIR, cause);
						if (jagged && RAND.nextFloat() < 0.50) {
							point.add(0, Math.signum(y), 0).setBlockType(BlockTypes.AIR, cause);
						}
					}
				}
			}
		}
		//		ellipse(location, radius, radius, jagged, cause);
	}

	public static void ellipse(Location<World> location, int radius, int height, boolean jagged, Cause cause) {
		ellipse(location, radius, height, radius, jagged, cause);
	}

	public static void ellipse(Location<World> location, int radiusX, int radiusY, int radiusZ, boolean jagged, Cause cause) {
		for (int x = -radiusX; x <= radiusX; x++) {
			for (int z = -radiusZ; z <= radiusZ; z++) {
				for (int y = -radiusY; y <= radiusY; y++) {
					Location<World> point = location.add(x, y, z);
					if (point.getBlockPosition()
							.sub(location.getBlockPosition())
							.div(new Vector3i(radiusX, radiusY, radiusZ))
							.lengthSquared() <= 1) {
						point.setBlockType(BlockTypes.AIR, cause);
						//						if (jagged && RAND.nextFloat() < 0.40) {
						//							point.add(0, Math.signum(y), 0).setBlockType(BlockTypes.AIR, cause);
						//						}
					}
				}
			}
		}

		//			 position.subtract(center).divide(radius).lengthSq() <= 1;
		//			for (int x = -radiusX; x <= radiusX; x++) {
		//				for (int z = -radiusZ; z <= radiusZ; z++) {
		//					for (int y = radiusY; y >= -radiusY; y--) { //Iterate downwards
		//						Location<World> point = location.add(x, y, z);
		//						if (Math.abs(center.getX() - point.getX()) < radiusX
		//								&& Math.abs(center.getY() - point.getY()) < radiusY
		//								&& Math.abs(center.getZ() - point.getZ()) < radiusZ) {
		//							point.setBlockType(BlockTypes.AIR, cause);
		//							if (jagged && RAND.nextFloat() < .60) {
		//								point.add(0, -1, 0).setBlockType(BlockTypes.AIR, cause);
		//							}
		//						}
		//					}
		//				}
		//			}
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
