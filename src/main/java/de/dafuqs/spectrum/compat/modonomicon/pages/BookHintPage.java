package de.dafuqs.spectrum.compat.modonomicon.pages;

import com.google.gson.*;
import com.klikli_dev.modonomicon.book.*;
import com.klikli_dev.modonomicon.book.conditions.*;
import com.klikli_dev.modonomicon.book.page.*;
import com.klikli_dev.modonomicon.util.*;
import com.mojang.serialization.JsonOps;
import de.dafuqs.spectrum.compat.modonomicon.*;
import net.minecraft.network.*;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.*;

import java.util.*;

public class BookHintPage extends BookTextPage {

    private final Identifier completionAdvancement;
    private final Ingredient cost;

    public BookHintPage(BookTextHolder title, BookTextHolder text, boolean useMarkdownInTitle, boolean showTitleSeparator, String anchor, BookCondition condition, Identifier completionAdvancement,Ingredient cost) {
        super(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition);
        this.completionAdvancement = completionAdvancement;
        this.cost = cost;
    }

    public static BookHintPage fromJson(Identifier entryId, JsonObject json, RegistryWrapper.WrapperLookup provider) {
        var title = BookGsonHelper.getAsBookTextHolder(json, "title", BookTextHolder.EMPTY, provider);
        var useMarkdownInTitle = JsonHelper.getBoolean(json, "use_markdown_title", false);
        var showTitleSeparator = JsonHelper.getBoolean(json, "show_title_separator", true);
        var text = BookGsonHelper.getAsBookTextHolder(json, "text", BookTextHolder.EMPTY, provider);
        var anchor = JsonHelper.getString(json, "anchor", "");
        var condition = json.has("condition")
                ? BookCondition.fromJson(entryId, json.getAsJsonObject("condition"), provider)
                : new BookNoneCondition();
        var completionAdvancement = Identifier.tryParse(JsonHelper.getString(json, "completion_advancement"));
        var cost = Ingredient.EMPTY;
        if (json.has("cost")) {
            var ingredient = JsonHelper.getObject(json, "cost");
            var count = JsonHelper.getInt(ingredient, "count", 1);
            cost = Ingredient.ALLOW_EMPTY_CODEC.parse(provider.getOps(JsonOps.INSTANCE), ingredient).result().orElse(cost);
            Arrays.stream(cost.getMatchingStacks()).forEach(itemStack -> itemStack.setCount(count));
        }
        return new BookHintPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, completionAdvancement, cost);
    }

    public static BookHintPage fromNetwork(RegistryByteBuf buffer) {
        var title = BookTextHolder.fromNetwork(buffer);
        var useMarkdownInTitle = buffer.readBoolean();
        var showTitleSeparator = buffer.readBoolean();
        var text = BookTextHolder.fromNetwork(buffer);
        var anchor = buffer.readString();
        var condition = BookCondition.fromNetwork(buffer);
        var completionAdvancement = buffer.readIdentifier();
        var cost = Ingredient.PACKET_CODEC.decode(buffer);
        return new BookHintPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, completionAdvancement, cost);
    }

    public Identifier getCompletionAdvancement() {
        return completionAdvancement;
    }

    public Ingredient getCost() {
        return cost;
    }

    @Override
    public Identifier getType() {
        return ModonomiconCompat.HINT_PAGE;
    }

    @Override
    public void toNetwork(RegistryByteBuf buffer) {
        super.toNetwork(buffer);
        buffer.writeIdentifier(completionAdvancement);
        Ingredient.PACKET_CODEC.encode(buffer, cost);
    }

}
