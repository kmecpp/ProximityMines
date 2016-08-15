package com.kmecpp.proximitymines.command;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import com.kmecpp.proximitymines.ProximityMines;
import com.kmecpp.spongecore.command.Result;
import com.kmecpp.spongecore.command.SpongeCommand;

public class ProximityMinesCommand extends SpongeCommand {

	public ProximityMinesCommand() {
		super("proximitymines", "pmines", "pm");
		setDescription(Text.of("Proximity mines base command"));
		registerArg("info", infoCommand());
		registerArg("reload", reloadCommand());
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) {
		return Result.CHILD_LIST;
	}

	public CommandSpec infoCommand() {
		return CommandSpec.builder()
				.description(Text.of("Displays information about the plugin"))
				.executor((src, args) -> {
					src.sendMessage(Text.builder()
							.append(Text.of(TextColors.GREEN, ProximityMines.NAME + " Information"), Text.NEW_LINE)
							.append(Text.of(TextColors.YELLOW, TextStyles.BOLD, TextStyles.STRIKETHROUGH, "------------------------------"), Text.NEW_LINE)
							.append(Text.of(TextColors.AQUA, "Author:  ", TextColors.GREEN, ProximityMines.AUTHOR), Text.NEW_LINE)
							.append(Text.of(TextColors.AQUA, "Version: ", TextColors.GREEN, ProximityMines.VERSION), Text.NEW_LINE)
							.append(Text.of(TextColors.AQUA, "GitHub:  ", TextColors.GREEN, ProximityMines.GITHUB))
							.build());
					return Result.SUCCESS;
				})
				.build();
	}

	public CommandSpec reloadCommand() {
		return CommandSpec.builder()
				.description(Text.of("Reloads the plugin configuration"))
				.executor((src, args) -> {
					src.sendMessage(Text.of(TextColors.GREEN, "Configuration file reloaded!"));
					return Result.SUCCESS;
				})
				.build();
	}

}