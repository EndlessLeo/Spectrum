package de.dafuqs.spectrum.items;

import com.klikli_dev.modonomicon.api.multiblock.*;
import de.dafuqs.spectrum.api.item.*;
import de.dafuqs.spectrum.helpers.*;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.client.item.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class StructurePlacerItem extends Item implements CreativeOnlyItem {
	
	protected final Identifier multiBlockIdentifier;
	
	public StructurePlacerItem(Settings settings, Identifier multiBlockIdentifier) {
		super(settings);
		this.multiBlockIdentifier = multiBlockIdentifier;
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getPlayer() != null && context.getPlayer().isCreative()) {
			Multiblock multiblock = SpectrumMultiblocks.get(multiBlockIdentifier);
			if (multiblock != null) {
				BlockRotation blockRotation = Support.rotationFromDirection(context.getHorizontalPlayerFacing());
				multiblock.place(context.getWorld(), context.getBlockPos().up(), blockRotation);
				return ActionResult.CONSUME;
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		CreativeOnlyItem.appendTooltip(tooltip);
	}
	
}
