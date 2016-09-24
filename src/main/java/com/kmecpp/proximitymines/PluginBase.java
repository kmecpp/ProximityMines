package com.kmecpp.proximitymines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigManager;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;

public abstract class PluginBase {

	//Effectively final variables
	private static PluginBase plugin;
	private static ConfigManager configManager;
	private static Logger logger;

	public PluginBase() {
		plugin = this;
	}

	public static PluginBase getPlugin() {
		return plugin;
	}

	public static Cause asCause() {
		return Cause.of(NamedCause.source(getPlugin()));
	}

	public static ConfigManager getConfigManager() {
		return configManager;
	}

	public void preInit() {
	}

	public void init() {
	}

	public void postInit() {
	}

	//INITIALIZATION
	public abstract String getPluginName();

	public void setConfigurationSpec() {
		//		this.configManager;
	}

	@Listener
	public void onGameConstruction(GameConstructionEvent e) {
		logger = LoggerFactory.getLogger(this.getClass().getName());
		//		configManager = new ConfigManager(Sponge.getGame().getConfigManager().getPluginConfig(this));
	}

	@Listener
	public void onGameInitialization(GamePreInitializationEvent e) {
		preInit();
	}

	@Listener
	public void onGameInitialization(GameInitializationEvent e) {
		init();
	}

	@Listener
	public void onGameInitialization(GamePostInitializationEvent e) {
		postInit();
		log("Initialization complete!");
	}

	//COMMANDS
	//	public static final void registerCommand(SpongeCommand command) {
	//		Sponge.getCommandManager().register(plugin, command.getSpec(), command.getAliases());
	//	}

	public static final void registerCommand(CommandSpec spec) {

	}

	//EVENTS
	public static final void registerListener(Object listener) {
		Sponge.getEventManager().registerListeners(plugin, listener);
	}

	public static final void unregisterListener(Object listener) {
		Sponge.getEventManager().unregisterListeners(listener);
	}

	public static final boolean postEvent(Event e) {
		return Sponge.getEventManager().post(e);
	}

	//LOGGING
	public static final Logger getLogger() {
		return logger;
	}

	public static final void log(String message) {
		logger.info(message);
	}

}
