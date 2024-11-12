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

public class LineTeleportingIdolBlock extends IdolBlock {
	
	protected final int range;
	
	public LineTeleportingIdolBlock(Settings settings, ParticleEffect particleEffect, int range) {
		super(settings, particleEffect);
		this.range = range;
	}

	@Override
	public MapCodec<? extends LineTeleportingIdolBlock> getCodec() {
		//TODO: Make the codec
		return null;
	}
	
	public static Direction getLookDirection(@NotNull Entity entity, boolean mirrorVertical, boolean mirrorHorizontal) {
		double pitch = entity.getPitch();
		if (pitch < -60) {
			return mirrorVertical ? Direction.UP : Direction.DOWN;
		} else if (pitch > 60) {
			return mirrorVertical ? Direction.DOWN : Direction.UP;
		} else {
			return mirrorHorizontal ? entity.getMovementDirection().getOpposite() : entity.getMovementDirection();
		}
	}
	
	public static Optional<BlockPos> searchForBlock(World world, BlockPos pos, BlockState searchedState, Direction direction, int range) {
		BlockPos.Mutable mutable = pos.mutableCopy();
		for (int i = 1; i < range; i++) {
			BlockPos currPos = mutable.offset(direction, i);
			if (world.getBlockState(currPos) == searchedState) {
				return Optional.of(currPos);
			}
		}
		return Optional.empty();
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("block.spectrum.line_teleporting_idol.tooltip", range));
		tooltip.add(Text.translatable("block.spectrum.line_teleporting_idol.tooltip2", range));
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!world.isClient && !hasCooldown(state)) {
			if (trigger((ServerWorld) world, pos, state, entity, getLookDirection(entity, true, false).getOpposite())) { // we want the movement direction here, instead of only "top"
				playTriggerParticles((ServerWorld) world, pos);
				playTriggerSound(world, pos);
				triggerCooldown(world, pos);
			}
		}
	}
	
	@Override
	public boolean trigger(ServerWorld world, BlockPos blockPos, BlockState state, @Nullable Entity entity, Direction side) {
		if (entity != null) {
			Optional<BlockPos> foundBlockPos = searchForBlock(world, blockPos, state, side.getOpposite(), this.range);
			if (foundBlockPos.isPresent()) {
				BlockPos targetPos = foundBlockPos.get();
				triggerCooldown(world, targetPos);
				RandomTeleportingIdolBlock.teleportTo(world, entity, targetPos);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getCooldownTicks() {
		return 10;
	}
	
}
