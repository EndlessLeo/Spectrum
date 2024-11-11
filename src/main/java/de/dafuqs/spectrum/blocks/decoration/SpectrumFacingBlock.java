package de.dafuqs.spectrum.blocks.decoration;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.state.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class SpectrumFacingBlock extends FacingBlock {

	public static final MapCodec<SpectrumFacingBlock> CODEC = createCodec(SpectrumFacingBlock::new);

	public SpectrumFacingBlock(Settings settings) {
		super(settings);
		this.setDefaultState((this.stateManager.getDefaultState()).with(FacingBlock.FACING, Direction.UP));
	}

	@Override
	public MapCodec<? extends SpectrumFacingBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getSide();
		BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
		return blockState.isOf(this) && blockState.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
	}
	
	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
}
