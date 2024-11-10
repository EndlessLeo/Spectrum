package de.dafuqs.spectrum.blocks.idols;

import net.minecraft.block.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class StatusEffectIdolBlock extends IdolBlock {
	
	protected final StatusEffect statusEffect;
	protected final int amplifier;
	protected final int duration;
	
	public StatusEffectIdolBlock(Settings settings, ParticleEffect particleEffect, StatusEffect statusEffect, int amplifier, int duration) {
		super(settings, particleEffect);
		this.statusEffect = statusEffect;
		this.amplifier = amplifier;
		this.duration = duration;
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("block.spectrum.potion_effect_idol.tooltip", this.statusEffect.getName()));
	}
	
	@Override
	public boolean trigger(ServerWorld world, BlockPos blockPos, BlockState state, @Nullable Entity entity, Direction side) {
		if (entity instanceof LivingEntity livingEntity) {
			livingEntity.addStatusEffect(new StatusEffectInstance(statusEffect, duration, amplifier, true, true));
			
			// if entity is burning: put out fire
			if (statusEffect == StatusEffects.FIRE_RESISTANCE && livingEntity.isOnFire()) {
				livingEntity.setFireTicks(0);
			}
			
			return true;
		}
		return false;
	}
	
}
