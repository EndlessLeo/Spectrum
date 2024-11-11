package de.dafuqs.spectrum.events;

import de.dafuqs.spectrum.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.*;
import net.minecraft.world.event.*;

import java.util.*;

public class SpectrumGameEvents {
	
	public static RegistryEntry<GameEvent> ENTITY_SPAWNED;
	public static RegistryEntry<GameEvent> BLOCK_CHANGED;

	public static RegistryEntry<GameEvent> HUMMINGSTONE_HUMMING;
	public static RegistryEntry<GameEvent> HUMMINGSTONE_HYMN;

	public static final HashMap<DyeColor, List<RedstoneTransferGameEvent>> WIRELESS_REDSTONE_SIGNALS = new HashMap<>(); // a list of 16 * 16 events, meaning redstone strength 0-15 with each dye color

	public static void register() {
		ENTITY_SPAWNED = register("entity_spawned");
		BLOCK_CHANGED = register("block_changed");

		HUMMINGSTONE_HUMMING = register("hummingstone_humming");
		HUMMINGSTONE_HYMN = register("hummingstone_hymn");

		for (DyeColor dyeColor : DyeColor.values()) {
			List<RedstoneTransferGameEvent> list = new ArrayList<>();
			for (int power = 0; power < 16; power++) {
				list.add(Registry.register(Registries.GAME_EVENT, SpectrumCommon.locate("wireless_redstone_signal_" + dyeColor.name().toLowerCase(Locale.ROOT) + "_" + power), new RedstoneTransferGameEvent("wireless_redstone_signal_" + dyeColor.name().toLowerCase(Locale.ROOT) + "_" + power, 16, dyeColor, power)));
			}
			WIRELESS_REDSTONE_SIGNALS.put(dyeColor, list);
		}
	}
	
	private static RegistryEntry<GameEvent> register(String id) {
		return register(id, 16);
	}
	
	private static RegistryEntry<GameEvent> register(String id, int range) {
		return Registry.registerReference(Registries.GAME_EVENT, SpectrumCommon.locate(id), new GameEvent(range));
	}
	
}