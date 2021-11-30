package moriyashiine.realisticfirespread.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = RealisticFireSpread.MOD_ID)
public class ModConfig implements ConfigData {
	public boolean shouldSunlitEntitiesSpreadFire = false;
}
