package de.dafuqs.spectrum.items.food;

import de.dafuqs.spectrum.items.trinkets.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

public class StarCandyItem extends Item {
	
	public StarCandyItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack itemStack = super.finishUsing(stack, world, user);
		if (!world.isClient) {
			WhispyCircletItem.removeSingleStatusEffect(user, StatusEffectCategory.HARMFUL);
		}
		return itemStack;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("item.spectrum.star_candy.tooltip").formatted(Formatting.GRAY));
	}
	
}
