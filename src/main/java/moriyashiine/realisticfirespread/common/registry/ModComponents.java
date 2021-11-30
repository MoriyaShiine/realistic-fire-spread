package moriyashiine.realisticfirespread.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import moriyashiine.realisticfirespread.common.RealisticFireSpread;
import moriyashiine.realisticfirespread.common.component.entity.FireFromSunComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class ModComponents implements EntityComponentInitializer {
	public static final ComponentKey<FireFromSunComponent> FIRE_FROM_SUN_COMPONENT = ComponentRegistry.getOrCreate(new Identifier(RealisticFireSpread.MOD_ID, "fire_from_sun"), FireFromSunComponent.class);
	
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(Entity.class, FIRE_FROM_SUN_COMPONENT).respawnStrategy(RespawnCopyStrategy.LOSSLESS_ONLY).end(FireFromSunComponent::new);
	}
}
