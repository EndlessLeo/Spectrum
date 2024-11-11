package de.dafuqs.spectrum.blocks.decoration;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class AlternatePlayerOnlyGlassBlock extends TransparentBlock {

	private final Block alternateBlock;
	
	// used for tinted glass to make light not shine through
	private final boolean tinted;
	
	public AlternatePlayerOnlyGlassBlock(AbstractBlock.Settings settings, Block block, boolean tinted) {
		super(settings);
		this.alternateBlock = block;
		this.tinted = tinted;
	}

	@Override
	public MapCodec<? extends AlternatePlayerOnlyGlassBlock> getCodec() {
		//TODO: Make the codec
		return null;
	}
	
	@Override
	public boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}
	
	@Override
	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (context instanceof EntityShapeContext entityShapeContext) {
			Entity entity = entityShapeContext.getEntity();
			if (entity instanceof PlayerEntity) {
				return VoxelShapes.empty();
			}
		}
		return state.getOutlineShape(world, pos);
	}
	
	@Override
	public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
		return !tinted;
	}
	
	@Override
	public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
		if (tinted) {
			return world.getMaxLightLevel();
		} else {
			return super.getOpacity(state, world, pos);
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
		if (stateFrom.isOf(this) || stateFrom.getBlock() == alternateBlock) {
			return true;
		}
		
		return super.isSideInvisible(state, stateFrom, direction);
	}
	
}
