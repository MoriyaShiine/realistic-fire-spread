package moriyashiine.realisticfirespread.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "realisticfirespread")
public class RFSConfig implements ConfigData {
	public boolean shouldSunlitEntitiesSpreadFire = false;
}
