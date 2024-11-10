package de.dafuqs.spectrum.items;

import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.tick.*;

import java.util.*;

public class DecayPlacerItem extends AliasedBlockItem {
	
	protected final List<Text> tooltips;
	
	public DecayPlacerItem(Block block, Settings settings, List<Text> tooltips) {
		super(block, settings);
		this.tooltips = tooltips;
	}
	
	@Override
    public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		ActionResult actionResult = super.useOnBlock(context);
		if (actionResult.isAccepted()) {
			ItemPlacementContext itemPlacementContext = this.getPlacementContext(new ItemPlacementContext(context));
			if (itemPlacementContext != null) {
				BlockPos blockPos = itemPlacementContext.getBlockPos();

				BlockState placedBlockState = context.getWorld().getBlockState(blockPos);
				if (placedBlockState.isIn(SpectrumBlockTags.DECAY)) {
					context.getWorld().scheduleBlockTick(blockPos, placedBlockState.getBlock(), 40 + world.random.nextInt(200), TickPriority.EXTREMELY_LOW);
				}
			}
		}
		if (!world.isClient && actionResult.isAccepted() && context.getPlayer() != null && !context.getPlayer().isCreative()) {
			context.getPlayer().getInventory().offerOrDrop(Items.GLASS_BOTTLE.getDefaultStack());
		}
		return actionResult;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.addAll(tooltips);
	}
	
}
