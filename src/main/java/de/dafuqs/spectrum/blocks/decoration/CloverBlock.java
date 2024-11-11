package de.dafuqs.spectrum.blocks.decoration;

import com.mojang.serialization.MapCodec;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class CloverBlock extends PlantBlock implements Fertilizable {

	public static final MapCodec<CloverBlock> CODEC = createCodec(CloverBlock::new);

	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);

	public CloverBlock(Settings settings) {
		super(settings);
	}

	@Override
	public MapCodec<? extends CloverBlock> getCodec() {
		return CODEC;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		world.getRegistryManager()
				.get(RegistryKeys.CONFIGURED_FEATURE)
				.get(SpectrumConfiguredFeatures.CLOVER_PATCH)
				.generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
	}

}
