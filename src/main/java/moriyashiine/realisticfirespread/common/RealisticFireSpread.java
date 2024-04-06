/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.common;

import eu.midnightdust.lib.config.MidnightConfig;
import moriyashiine.realisticfirespread.common.event.FireSpreadEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.Identifier;

public class RealisticFireSpread implements ModInitializer {
	public static final String MOD_ID = "realisticfirespread";

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, ModConfig.class);
		ServerTickEvents.END_WORLD_TICK.register(new FireSpreadEvent());
	}

	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}
