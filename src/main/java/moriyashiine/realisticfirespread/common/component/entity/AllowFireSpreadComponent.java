/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.common.component.entity;

import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class AllowFireSpreadComponent implements ServerTickingComponent {
	private final Entity obj;
	private boolean allowFireSpread = false;

	public AllowFireSpreadComponent(Entity obj) {
		this.obj = obj;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		allowFireSpread = tag.getBoolean("AllowFireSpread");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putBoolean("AllowFireSpread", allowFireSpread);
	}

	@Override
	public void serverTick() {
		if (allowFireSpread && obj.getFireTicks() <= 0) {
			allowFireSpread = false;
		}
	}

	public boolean allowFireSpread() {
		return allowFireSpread;
	}

	public void setAllowFireSpread(boolean allowFireSpread) {
		this.allowFireSpread = allowFireSpread;
	}
}
