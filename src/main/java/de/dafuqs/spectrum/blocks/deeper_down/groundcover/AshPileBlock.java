package de.dafuqs.spectrum.blocks.deeper_down.groundcover;

import com.mojang.serialization.MapCodec;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public class AshPileBlock extends SnowBlock {

	public static final MapCodec<AshPileBlock> CODEC = createCodec(AshPileBlock::new);

	public AshPileBlock(Settings settings) {
		super(settings);
	}

//	@Override
//	public MapCodec<? extends AshPileBlock> getCodec() {
//		//TODO: Make the codec
//		return CODEC;
//	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.down());
		if (blockState.isOf(SpectrumBlocks.ASH))
			return true;
		return super.canPlaceAt(state, world, pos);
	}
	
	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
	}
}
