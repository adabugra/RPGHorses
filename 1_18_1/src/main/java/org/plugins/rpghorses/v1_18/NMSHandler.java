package org.plugins.rpghorses.v1_18;

import com.google.common.collect.Sets;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.plugins.rpghorses.NMS;

import java.lang.reflect.Field;

public class NMSHandler extends NMS {

	@Override
	public void removeBehaviour(LivingEntity entity) {
		EntityCreature creature = (EntityCreature) (((CraftEntity) entity).getHandle());
		try {
			Field selector;

			try {
				selector = PathfinderGoalSelector.class.getDeclaredField("d");
			} catch (NoSuchFieldException ignored) {
				selector = PathfinderGoalSelector.class.getDeclaredField("availableGoals");
			}

			selector.setAccessible(true);
			selector.set(creature.bR, Sets.newLinkedHashSet());
			selector.set(creature.bS, Sets.newLinkedHashSet());
		} catch (Exception | Error e) {
			logError(e);
		}

	}
}
