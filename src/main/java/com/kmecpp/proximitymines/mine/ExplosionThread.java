package com.kmecpp.proximitymines.mine;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;

import com.kmecpp.proximitymines.ProximityMines;

public class ExplosionThread {

	private final ExplosionTickHandler tickHandler;
	private final int interval;
	private final int executions;

	private Task task;
	private int ticks = 0;

	private ExplosionThread(ExplosionTickHandler tickHandler, int interval, int executions) {
		this.tickHandler = tickHandler;
		this.interval = interval;
		this.executions = executions;
	}

	/**
	 * Starts a new {@link ExplosionThread} thread with the given parameters
	 * 
	 * @param tickHandler
	 *            the handler for each tick the thread is executed
	 * @param interval
	 *            the number of ticks that must pass before the handler is
	 *            executed again
	 * @param executions
	 *            the number of times the tick handler will be executed
	 */
	public static void start(ExplosionTickHandler tickHandler, int interval, int executions) {
		new ExplosionThread(tickHandler, interval, executions).start();
	}

	public void start() {
		task = Sponge.getScheduler()
				.createTaskBuilder()
				.async()
				.intervalTicks(1)
				.name("ProximityMines Explosion Thread")
				.execute(() -> {
					if (++ticks >= interval) {
						task.cancel();
					}

					Sponge.getScheduler()
							.createTaskBuilder()
							.execute(() -> tickHandler.tick(this))
							.submit(ProximityMines.getPlugin());
				})
				.submit(ProximityMines.getPlugin());
	}

	public int getTicks() {
		return ticks;
	}

	public int getInterval() {
		return interval;
	}

	public int getExecutions() {
		return executions;
	}

	public ExplosionTickHandler getTickHandler() {
		return tickHandler;
	}

	@FunctionalInterface
	public static interface ExplosionTickHandler {

		void tick(ExplosionThread thread);

	}

}
