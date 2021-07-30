package moriyashiine.realisticfirespread.common.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import moriyashiine.realisticfirespread.api.component.FireFromSunComponent;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class RSFComponents implements EntityComponentInitializer {
	public static final ComponentKey<FireFromSunComponent> FIRE_FROM_SUN_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("realisticfirespread", "fire_from_sun"), FireFromSunComponent.class);
	
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(Entity.class, FIRE_FROM_SUN_COMPONENT).impl(FireFromSunComponent.class).respawnStrategy(RespawnCopyStrategy.LOSSLESS_ONLY).end(FireFromSunComponent::new);
	}
}
