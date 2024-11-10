package de.dafuqs.spectrum.items.trinkets;

import de.dafuqs.spectrum.*;
import dev.emi.trinkets.api.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;

import java.util.*;

public class CottonCloudBootsItem extends SpectrumTrinketItem {
	
	public CottonCloudBootsItem(Settings settings) {
		super(settings, SpectrumCommon.locate("unlocks/trinkets/cotton_cloud_boots"));
	}
	
	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		super.tick(stack, slot, entity);
		World world = entity.getWorld();
		if (entity.isSprinting() && !entity.isOnGround() && !entity.isSneaking()) {
			Vec3d velocity = entity.getVelocity();
			if (velocity.y < 0) {
				entity.setVelocity(entity.getVelocity().multiply(1, 0.1, 1));
				if (world.isClient) {
					Random random = world.random;
					world.addParticle(ParticleTypes.CLOUD, entity.getX(), entity.getY(), entity.getZ(),
							0.125 - random.nextFloat() * 0.25, 0.04 - random.nextFloat() * 0.08, 0.125 - random.nextFloat() * 0.25);
				}
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("item.spectrum.cotton_cloud_boots.tooltip").formatted(Formatting.GRAY));
		tooltip.add(Text.translatable("item.spectrum.cotton_cloud_boots.tooltip2").formatted(Formatting.GRAY));
	}
	
}
