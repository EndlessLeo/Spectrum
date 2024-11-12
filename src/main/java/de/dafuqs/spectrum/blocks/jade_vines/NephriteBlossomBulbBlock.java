package de.dafuqs.spectrum.blocks.jade_vines;

import com.mojang.serialization.MapCodec;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public class NephriteBlossomBulbBlock extends PlantBlock implements Fertilizable {

	public static final MapCodec<NephriteBlossomBulbBlock> CODEC = createCodec(NephriteBlossomBulbBlock::new);

	public NephriteBlossomBulbBlock(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState());
	}

	@Override
	public MapCodec<? extends NephriteBlossomBulbBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}
	
	@Override
	public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
		return SpectrumBlocks.NEPHRITE_BLOSSOM_BULB.asItem().getDefaultStack();
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (random.nextFloat() < 0.025) {
			grow(world, random, pos, state);
		}
	}
	
	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
		return true;
	}

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.075;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).get(SpectrumConfiguredFeatures.NEPHRITE_BLOSSOM_BULB).generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}
