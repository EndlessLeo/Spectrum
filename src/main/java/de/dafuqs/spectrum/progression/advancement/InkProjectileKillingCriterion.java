package de.dafuqs.spectrum.progression.advancement;

import com.google.common.collect.*;
import com.google.gson.*;
import de.dafuqs.spectrum.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.entity.*;
import net.minecraft.loot.context.*;
import net.minecraft.predicate.NumberRange.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.server.network.*;
import net.minecraft.util.*;

import java.util.*;

public class InkProjectileKillingCriterion extends AbstractCriterion<InkProjectileKillingCriterion.Conditions> {

	public static final Identifier ID = SpectrumCommon.locate("ink_projectile_killing");

	public InkProjectileKillingCriterion() {
	}

	@Override
	public Identifier getId() {
		return ID;
	}

	@Override
	public InkProjectileKillingCriterion.Conditions conditionsFromJson(JsonObject jsonObject, LootContextPredicate extended, AdvancementEntityPredicateDeserializer advancementEntityPredicateDeserializer) {
		LootContextPredicate[] victims = EntityPredicate.contextPredicateArrayFromJson(jsonObject, "victims", advancementEntityPredicateDeserializer);
		IntRange intRange = IntRange.fromJson(jsonObject.get("unique_entity_types"));
		return new InkProjectileKillingCriterion.Conditions(extended, victims, intRange);
	}

	public void trigger(ServerPlayerEntity player, Collection<Entity> piercingKilledEntities) {
		List<LootContext> list = Lists.newArrayList();
		Set<EntityType<?>> set = Sets.newHashSet();

		for (Entity entity : piercingKilledEntities) {
			set.add(entity.getType());
			list.add(EntityPredicate.createAdvancementEntityLootContext(player, entity));
		}

		this.trigger(player, (conditions) -> conditions.matches(list, set.size()));
	}

	public static class Conditions extends AbstractCriterionConditions {
		private final LootContextPredicate[] victims;
		private final IntRange uniqueEntityTypes;

		public Conditions(LootContextPredicate player, LootContextPredicate[] victims, IntRange uniqueEntityTypes) {
			super(InkProjectileKillingCriterion.ID, player);
			this.victims = victims;
			this.uniqueEntityTypes = uniqueEntityTypes;
		}

		public static InkProjectileKillingCriterion.Conditions create(EntityPredicate.Builder... victimPredicates) {

			LootContextPredicate[] extendeds = new LootContextPredicate[victimPredicates.length];
			for (int i = 0; i < victimPredicates.length; i++) {
				var predicate = EntityPredicate.asLootContextPredicate(victimPredicates[i].build());
				extendeds[i] = predicate;
			}

			return new InkProjectileKillingCriterion.Conditions(LootContextPredicate.EMPTY, extendeds, IntRange.ANY);
		}

		public static InkProjectileKillingCriterion.Conditions create(IntRange uniqueEntityTypes) {
			LootContextPredicate[] extendeds = new LootContextPredicate[0];
			return new InkProjectileKillingCriterion.Conditions(LootContextPredicate.EMPTY, extendeds, uniqueEntityTypes);
		}

		public boolean matches(Collection<LootContext> victimContexts, int uniqueEntityTypeCount) {
			if (this.victims.length > 0) {
				List<LootContext> list = Lists.newArrayList(victimContexts);

				for (LootContextPredicate extended : this.victims) {
					boolean bl = false;

					Iterator<LootContext> iterator = list.iterator();
					while (iterator.hasNext()) {
						LootContext lootContext = iterator.next();
						if (extended.test(lootContext)) {
							iterator.remove();
							bl = true;
							break;
						}
					}

					if (!bl) {
						return false;
					}
				}
			}

			return this.uniqueEntityTypes.test(uniqueEntityTypeCount);
		}

		@Override
		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.add("victims", LootContextPredicate.toPredicatesJsonArray(this.victims, predicateSerializer));
			jsonObject.add("unique_entity_types", this.uniqueEntityTypes.toJson());
			return jsonObject;
		}
	}
}
