package de.dafuqs.spectrum.items.trinkets;

import net.minecraft.client.item.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class PuffCircletItem extends AzureDikeTrinketItem {
	
	public static final float PROJECTILE_DEFLECTION_COST = 4;
	public static final float FALL_DAMAGE_NEGATING_COST = 2;

	public PuffCircletItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("item.spectrum.puff_circlet.tooltip"));
		tooltip.add(Text.translatable("item.spectrum.puff_circlet.tooltip2"));
	}

	@Override
	public int maxAzureDike(ItemStack stack) {
		return 4;
	}
	
}
