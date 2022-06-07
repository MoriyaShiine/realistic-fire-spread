/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.realisticfirespread.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import moriyashiine.realisticfirespread.common.RealisticFireSpread;
import moriyashiine.realisticfirespread.common.component.entity.FireFromSunComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ModEntityComponents implements EntityComponentInitializer {
	public static final ComponentKey<FireFromSunComponent> FIRE_FROM_SUN = ComponentRegistry.getOrCreate(new Identifier(RealisticFireSpread.MOD_ID, "fire_from_sun"), FireFromSunComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(Entity.class, FIRE_FROM_SUN, FireFromSunComponent::new);
	}
}
