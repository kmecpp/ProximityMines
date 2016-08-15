package com.kmecpp.proximitymines.mine;

import java.util.Optional;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public class Mine {

	private MineType type;
	private Location<World> location;

	public Mine(MineType type, Location<World> location) {
		this.type = type;
		this.location = location;
	}

	public MineType getType() {
		return type;
	}

	public Location<World> getLocation() {
		return location;
	}

	public Vector3i getPosition() {
		return location.getBlockPosition();
	}

	public double distanceTo(Vector3d vector) {
		return location.getPosition().distance(vector);
	}

	public void explode(Player target) {
		location.removeBlock();
		getType().getExplosion().onExplode(target, this);
	}

	/**
	 * Creates a mine instance from the physical mine at the specified location.
	 * If not mine exists at that location the Optional will be empty. The
	 * location MUST BE the location of the actual mine (TNT Block) of the
	 * multiblock structure or it will not be detected.
	 * 
	 * @param location
	 *            the location from which to retrieve the mine instance
	 * @return a mine instance representing the one at the given location,
	 *         assuming it exists
	 */
	public static Optional<Mine> fromLocation(Location<World> location) {
		if (location.getBlockType() == BlockTypes.TNT) {
			Optional<MineType> mineType = MineType.fromBlock(location.add(0, -1, 0).getBlockType());
			if (mineType.isPresent()) {
				return Optional.of(new Mine(mineType.get(), location));
			}
		}
		return Optional.empty();
	}

}