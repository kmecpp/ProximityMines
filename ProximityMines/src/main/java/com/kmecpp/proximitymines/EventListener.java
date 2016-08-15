package com.kmecpp.proximitymines;

import java.util.Optional;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.DisplaceEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.type.Include;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.kmecpp.jlib.StringUtil;
import com.kmecpp.proximitymines.event.events.MineCreateEvent;
import com.kmecpp.proximitymines.event.events.MineDestroyEvent;
import com.kmecpp.proximitymines.event.events.MineExplodeEvent;
import com.kmecpp.proximitymines.mine.Mine;
import com.kmecpp.proximitymines.mine.MineType;
import com.kmecpp.spongecore.SpongeCore;
import com.kmecpp.spongecore.event.SpongeListener;

public class EventListener extends SpongeListener {

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

			Optional<MineType> mineType = targetBlock.getState().getType() == BlockTypes.TNT
					? MineType.fromBlock(targetBlock.getLocation().get().add(0, -1, 0).getBlockType())
					: MineType.fromBlock(targetBlock);

			//Mine exists
			mineType.ifPresent((type) -> {

				Mine mine = new Mine(type, location);
				String article = StringUtil.vowel(mine.getType().getName()) ? "an" : "a";

				//Place
				if (e instanceof ChangeBlockEvent.Place) {
					if (!SpongeCore.postEvent(new MineCreateEvent(player, mine))) {
						player.sendMessage(Text.of(
								TextColors.GREEN, "You have planted " + article + " ",
								TextColors.AQUA, mine.getType().getName(),
								TextColors.GREEN, " proximity mine!"));
					} else {
						e.setCancelled(true);
					}
				}

				//Break
				else if (e instanceof ChangeBlockEvent.Break) {
					if (!SpongeCore.postEvent(new MineDestroyEvent(player, mine))) {
						player.sendMessage(Text.of(
								TextColors.GREEN, "You have diffused " + article + " ",
								TextColors.AQUA, mine.getType().getName(),
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
		e.getInteractionPoint().ifPresent((location) -> {
			player.getWorld().setBlockType(location.getFloorX(), location.getFloorY(), location.getFloorZ(), BlockTypes.GOLD_BLOCK);
		});
	}

	@Listener
	public void onPlayerMove(DisplaceEntityEvent.TargetPlayer e) {
		if (!e.getFromTransform().getLocation().getBlockPosition().equals(e.getToTransform().getLocation().getBlockPosition())) {
			scanLocation(e.getTargetEntity(), e.getToTransform().getLocation(), 3);
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

					Mine.fromLocation(loc).ifPresent((mine) -> {
						if (!SpongeCore.postEvent(new MineExplodeEvent(player, mine))) {
							mine.explode(player);
						}
					});
					//					SpongeCore.log(loc.getBlockPosition().toString());
				}
			}
		}
		SpongeCore.log("Time: " + ((System.nanoTime() - start) / 1000) + "�s");
		return (System.nanoTime() - start) / 1000;
	}

}