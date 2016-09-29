package com.kmecpp.proximitymines;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.type.Include;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;
import com.kmecpp.jlib.utils.StringUtil;
import com.kmecpp.proximitymines.event.events.MineCreateEvent;
import com.kmecpp.proximitymines.event.events.MineDestroyEvent;
import com.kmecpp.proximitymines.event.events.MineExplodeEvent;
import com.kmecpp.proximitymines.mine.AbstractMine;
import com.kmecpp.proximitymines.mine.MineBlock;
import com.kmecpp.spongecore.event.SpongeListener;

public class EventListener implements SpongeListener {

	@Listener
	@Include({ ChangeBlockEvent.Place.class, ChangeBlockEvent.Break.class })
	public void onBlockChange(ChangeBlockEvent e, @First Player player) {
		e.getTransactions().stream().forEach((transaction) -> {
			BlockSnapshot targetBlock = (e instanceof ChangeBlockEvent.Place) ? transaction.getFinal() : transaction.getOriginal();
			boolean head = targetBlock.getState().getType() == BlockTypes.TNT;
			Location<World> location = head
					? targetBlock.getLocation().get()
					: targetBlock.getLocation().get().add(0, 1, 0); //Location of mine (TNT)

			if (!head && location.getBlockType() != BlockTypes.TNT) { //If head, block is destroyed on break
				return; //No mine present
			}

			Optional<? extends AbstractMine> optionalMine = targetBlock.getState().getType() == BlockTypes.TNT
					? MineRegistry.getMine(targetBlock.getLocation().get().add(0, -1, 0).getBlockType())
					: MineRegistry.getMine(targetBlock.getLocation().get().getBlockType());

			//			Optional<MineType> mineType = targetBlock.getState().getType() == BlockTypes.TNT
			//					? MineType.fromBlock(targetBlock.getLocation().get().add(0, -1, 0).getBlockType())
			//					: MineType.fromBlock(targetBlock);

			//Mine exists
			optionalMine.ifPresent((mine) -> {
				MineBlock mineBlock = new MineBlock(mine.getBlockType(), location);
				String article = StringUtil.article(mineBlock.getType().getName());

				//Place
				if (e instanceof ChangeBlockEvent.Place) {
					if (!ProximityMines.postEvent(new MineCreateEvent(player, mineBlock))) {
						player.sendMessage(Text.of(
								TextColors.GREEN, "You have planted " + article + " ",
								TextColors.AQUA, mine.getName(),
								TextColors.GREEN, " proximity mine!"));
					} else {
						e.setCancelled(true);
					}
				}

				//Break
				else if (e instanceof ChangeBlockEvent.Break) {
					if (!ProximityMines.postEvent(new MineDestroyEvent(player, mineBlock))) {
						player.sendMessage(Text.of(
								TextColors.GREEN, "You have diffused " + article + " ",
								TextColors.AQUA, mine.getName(),
								TextColors.GREEN, " proximity mine!"));
					} else {
						e.setCancelled(true);
					}
				}
			});

		});
	}

	@Listener
	public void onPlayerInteract(InteractBlockEvent.Primary e, @First Player player) {
		ThreadLocalRandom RAND = ThreadLocalRandom.current();
		World world = player.getWorld();
		Entity arrow = world.createEntity(EntityTypes.TIPPED_ARROW, player.getLocation().getPosition().add(1, 0, 0));
		arrow.setVelocity(new Vector3d(
				(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat(),
				RAND.nextFloat(),
				(RAND.nextBoolean() ? 1 : -1) * RAND.nextFloat()));
		Vector3d vec = arrow.getVelocity().normalize();
		arrow.setRotation(new Vector3d(Math.asin(-vec.getY()), Math.atan2(vec.getX(), vec.getZ()), 0D));
		world.spawnEntity(arrow, ProximityMines.getPlugin().asCause());

		//		System.out.println("Shrapnel!");
		//		ExplosionUtil.shrapnel(new Location<World>(player.getWorld(), e.getTargetBlock().getPosition()),
		//				10, 10, ProximityMines.getPlugin().asCause(NamedCause.of("player", player)));
	}

	//	@Listener
	//	public void onPlayerInteract(InteractBlockEvent.Primary e, @First Player player) {
	//		e.getInteractionPoint().ifPresent((location) -> {
	//			player.getWorld().setBlockType(location.getFloorX(), location.getFloorY(), location.getFloorZ(), BlockTypes.GOLD_BLOCK, ProximityMines.getPlugin().asCause());
	//		});
	//	}

	@Listener
	public void onPlayerMove(MoveEntityEvent e) {
		if (e.getTargetEntity() instanceof Player) {
			Player player = (Player) e.getTargetEntity();
			if (!e.getFromTransform().getLocation().getBlockPosition().equals(e.getToTransform().getLocation().getBlockPosition())) {
				scanLocation(player, e.getToTransform().getLocation(), 3);
			}
		}
	}

	private static long scanLocation(Player player, Location<World> location, int size) {
		//		ArrayList<Mine> list = new ArrayList<Mine>();
		//		for (int i = 0; i < 1000; i++) {
		//			list.add(new Mine(MineType.TIER_FOUR, location));
		//		}
		//		long s = System.nanoTime();
		//		for (Mine mine : list) {
		//			if (mine.distanceTo(player.getLocation().getPosition()) < size) {
		//			}
		//		}
		//		SpongeCore.log("Time: " + ((System.nanoTime() - s) / 1000) + "�s");

		long start = System.nanoTime();
		for (int x = -size; x <= size; x++) {
			for (int z = Math.abs(x) - size; z <= -(Math.abs(x) - size); z++) { //0, -1, -2, -3, -2, -1, 0 || Math.abs(x) - size
				for (int y = -1; y >= (Math.abs(x) + Math.abs(z)) - size; y--) {
					Location<World> loc = location.add(x, y, z);

					MineBlock.fromLocation(loc).ifPresent((mineBlock) -> {
						if (!ProximityMines.postEvent(new MineExplodeEvent(player, mineBlock))) {
							mineBlock.explode(player);
						}
					});
					//					SpongeCore.log(loc.getBlockPosition().toString());
				}
			}
		}
		ProximityMines.log("Time: " + ((System.nanoTime() - start) / 1000) + "�s");
		return (System.nanoTime() - start) / 1000;
	}

}
