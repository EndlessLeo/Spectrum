package de.dafuqs.spectrum.items.food;

import de.dafuqs.spectrum.items.trinkets.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

public class EnchantedStarCandyItem extends Item {
	
	public EnchantedStarCandyItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack itemStack = super.finishUsing(stack, world, user);
		
		user.heal(user.getMaxHealth());
		if (!world.isClient) {
			WhispyCircletItem.removeNegativeStatusEffects(user);
		}
		if (user instanceof PlayerEntity player) {
			player.getHungerManager().add(1000, 1.0F);
		}
		return itemStack;
	}
	
	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.translatable("item.spectrum.enchanted_star_candy.tooltip").formatted(Formatting.GRAY));
		tooltip.add(Text.translatable("item.spectrum.enchanted_star_candy.tooltip2").formatted(Formatting.GRAY));
	}
	
	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}
	
}
