package de.dafuqs.spectrum.blocks.idols;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class FallDamageNegatingIdolBlock extends IdolBlock {
	
	public FallDamageNegatingIdolBlock(Settings settings, ParticleEffect particleEffect) {
		super(settings, particleEffect);
	}

	@Override
	public MapCodec<? extends FallDamageNegatingIdolBlock> getCodec() {
		//TODO: Make the codec
		return null;
	}
	
	@Override
	public boolean trigger(ServerWorld world, BlockPos blockPos, BlockState state, @Nullable Entity entity, Direction side) {
		if (entity != null && entity.getVelocity().getY() < -0.01) {
			entity.setVelocity(0, 0.5, 0); // makes it feel bouncy
			entity.velocityModified = true;
			entity.velocityDirty = true;
			entity.fallDistance = 0;
			return true;
		}
		return false;
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("block.spectrum.fall_damage_negating_idol.tooltip"));
		tooltip.add(Text.translatable("block.spectrum.fall_damage_negating_idol.tooltip2"));
	}
	
	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!hasCooldown(state) && fallDistance > 3F) {
			entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());
			if (!world.isClient) {
				playTriggerParticles((ServerWorld) world, pos);
				playTriggerSound(world, pos);
				triggerCooldown(world, pos);
			}
		}
	}
	
}
