package de.dafuqs.spectrum.progression.advancement;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.items.trinkets.*;
import dev.emi.trinkets.api.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.*;
import net.minecraft.predicate.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;

import java.util.*;

public class TrinketChangeCriterion extends AbstractCriterion<TrinketChangeCriterion.Conditions> {

	public static final Identifier ID = SpectrumCommon.locate("trinket_change");

	public void trigger(ServerPlayerEntity player) {
		this.trigger(player, (conditions) -> {
			Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(player);
			if (trinketComponent.isPresent()) {
				List<ItemStack> equippedStacks = new ArrayList<>();
				int spectrumStacks = 0;
				for (Pair<SlotReference, ItemStack> t : trinketComponent.get().getAllEquipped()) {
					equippedStacks.add(t.getRight());
					if (t.getRight().getItem() instanceof SpectrumTrinketItem) {
						spectrumStacks++;
					}
				}
				return conditions.matches(equippedStacks, equippedStacks.size(), spectrumStacks);
			}
			return false;
		});
	}

	@Override
	public Codec<Conditions> getConditionsCodec() {
		return Conditions.CODEC;
	}

	public record Conditions(
		Optional<LootContextPredicate> player,
		Optional<List<ItemPredicate>> itemPredicates,
		Optional<NumberRange.IntRange> totalCountRange,
		Optional<NumberRange.IntRange> spectrumCountRange
	) implements AbstractCriterion.Conditions {

		public static final Codec<TrinketChangeCriterion.Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			LootContextPredicate.CODEC.optionalFieldOf("player").forGetter(TrinketChangeCriterion.Conditions::player),
			ItemPredicate.CODEC.listOf().optionalFieldOf("items").forGetter(TrinketChangeCriterion.Conditions::itemPredicates),
			NumberRange.IntRange.CODEC.optionalFieldOf("total_count").forGetter(TrinketChangeCriterion.Conditions::totalCountRange),
			NumberRange.IntRange.CODEC.optionalFieldOf("spectrum_count").forGetter(TrinketChangeCriterion.Conditions::spectrumCountRange)
		).apply(instance, TrinketChangeCriterion.Conditions::new));

		public boolean matches(List<ItemStack> trinketStacks, int totalCount, int spectrumCount) {
			if (this.totalCountRange.isPresent() && this.totalCountRange.get().test(totalCount)
				&& this.spectrumCountRange.isPresent() && this.spectrumCountRange.get().test(spectrumCount)) {
				int i = this.itemPredicates.orElse(List.of()).size();
				if (i == 0) {
					return true;
				} else {
					List<ItemPredicate> requiredTrinkets = new ObjectArrayList<>(this.itemPredicates.get());
					for (ItemStack trinketStack : trinketStacks) {
						if (requiredTrinkets.isEmpty()) {
							return true;
						}
						if (!trinketStack.isEmpty()) {
							requiredTrinkets.removeIf((item) -> item.test(trinketStack));
						}
					}

					return requiredTrinkets.isEmpty();
				}
			}
			return false;
		}
	}

}
