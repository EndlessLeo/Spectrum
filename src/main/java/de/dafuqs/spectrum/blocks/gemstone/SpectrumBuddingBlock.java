package de.dafuqs.spectrum.blocks.gemstone;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;

public class SpectrumBuddingBlock extends SpectrumGemstoneBlock {

	private static final Direction[] DIRECTIONS = Direction.values();
	private final Block smallBlock;
	private final Block mediumBlock;
	private final Block largeBlock;
	private final Block clusterBlock;
	
	public SpectrumBuddingBlock(Settings settings, Block smallBlock, Block mediumBlock, Block largeBlock, Block clusterBlock, SoundEvent hitSoundEvent, SoundEvent chimeSoundEvent) {
		super(settings, hitSoundEvent, chimeSoundEvent);
		
		this.smallBlock = smallBlock;
		this.mediumBlock = mediumBlock;
		this.largeBlock = largeBlock;
		this.clusterBlock = clusterBlock;
	}

	@Override
	public MapCodec<? extends SpectrumBuddingBlock> getCodec() {
		//TODO: Make the codec
		return null;
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (random.nextInt(5) == 0) {
			Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			Block block = null;
			if (BuddingAmethystBlock.canGrowIn(blockState)) {
				block = smallBlock;
			} else if (blockState.isOf(smallBlock) && blockState.get(AmethystClusterBlock.FACING) == direction) {
				block = mediumBlock;
			} else if (blockState.isOf(mediumBlock) && blockState.get(AmethystClusterBlock.FACING) == direction) {
				block = largeBlock;
			} else if (blockState.isOf(largeBlock) && blockState.get(AmethystClusterBlock.FACING) == direction) {
				block = clusterBlock;
			}
			
			if (block != null) {
				world.setBlockState(blockPos, block.getDefaultState().with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER));
			}
		}
	}
	
}
