/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.realisticfirespread.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import moriyashiine.realisticfirespread.common.RealisticFireSpread;
import moriyashiine.realisticfirespread.common.component.entity.FireFromSunComponent;
import net.minecraft.entity.Entity;

public class ModEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<FireFromSunComponent> FIRE_FROM_SUN = ComponentRegistry.getOrCreate(RealisticFireSpread.id("fire_from_sun"), FireFromSunComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(Entity.class, FIRE_FROM_SUN, FireFromSunComponent::new);
	}
}
