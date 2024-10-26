/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.realisticfirespread.common.component.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class AllowFireSpreadComponent implements ServerTickingComponent {
	private final Entity obj;
	private boolean allowFireSpread = false;

	public AllowFireSpreadComponent(Entity obj) {
		this.obj = obj;
	}

	@Override
	public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
		allowFireSpread = tag.getBoolean("AllowFireSpread");
	}

	@Override
	public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
		tag.putBoolean("AllowFireSpread", allowFireSpread);
	}

	@Override
	public void serverTick() {
		if (allowFireSpread && obj.getFireTicks() <= 0) {
			allowFireSpread = false;
		}
	}

	public boolean allowsFireSpread() {
		return allowFireSpread;
	}

	public void setAllowFireSpread(boolean allowFireSpread) {
		this.allowFireSpread = allowFireSpread;
	}
}
