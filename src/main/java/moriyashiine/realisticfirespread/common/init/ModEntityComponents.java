/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.realisticfirespread.common.init;

import moriyashiine.realisticfirespread.common.RealisticFireSpread;
import moriyashiine.realisticfirespread.common.component.entity.AllowFireSpreadComponent;
import net.minecraft.entity.Entity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class ModEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<AllowFireSpreadComponent> ALLOW_FIRE_SPREAD = ComponentRegistry.getOrCreate(RealisticFireSpread.id("allow_fire_spread"), AllowFireSpreadComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(Entity.class, ALLOW_FIRE_SPREAD, AllowFireSpreadComponent::new);
	}
}
