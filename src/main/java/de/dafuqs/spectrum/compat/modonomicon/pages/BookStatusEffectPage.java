package de.dafuqs.spectrum.compat.modonomicon.pages;

import com.google.gson.JsonObject;
import com.klikli_dev.modonomicon.book.BookTextHolder;
import com.klikli_dev.modonomicon.book.conditions.BookCondition;
import com.klikli_dev.modonomicon.book.conditions.BookNoneCondition;
import com.klikli_dev.modonomicon.book.page.BookTextPage;
import com.klikli_dev.modonomicon.client.gui.book.markdown.BookTextRenderer;
import com.klikli_dev.modonomicon.util.BookGsonHelper;
import de.dafuqs.spectrum.compat.modonomicon.ModonomiconCompat;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class BookStatusEffectPage extends BookTextPage {

    private final Identifier statusEffectId;

    public BookStatusEffectPage(BookTextHolder title, BookTextHolder text, boolean useMarkdownInTitle, boolean showTitleSeparator, String anchor, BookCondition condition, Identifier statusEffectId) {
        super(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition);
        this.statusEffectId = statusEffectId;
    }

    public static BookStatusEffectPage fromJson(Identifier entryId, JsonObject json, RegistryWrapper.WrapperLookup provider) {
        var title = BookGsonHelper.getAsBookTextHolder(json, "title", BookTextHolder.EMPTY, provider);
        var useMarkdownInTitle = JsonHelper.getBoolean(json, "use_markdown_title", false);
        var showTitleSeparator = JsonHelper.getBoolean(json, "show_title_separator", true);
        var text = BookGsonHelper.getAsBookTextHolder(json, "text", BookTextHolder.EMPTY, provider);
        var anchor = JsonHelper.getString(json, "anchor", "");
        var condition = json.has("condition")
                ? BookCondition.fromJson(entryId, json.getAsJsonObject("condition"), provider)
                : new BookNoneCondition();
        var statusEffectId = json.has("status_effect_id") ? Identifier.tryParse(JsonHelper.getString(json, "status_effect_id")) : null;
        return new BookStatusEffectPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, statusEffectId);
    }

    public static BookStatusEffectPage fromNetwork(RegistryByteBuf buffer) {
        var title = BookTextHolder.fromNetwork(buffer);
        var useMarkdownInTitle = buffer.readBoolean();
        var showTitleSeparator = buffer.readBoolean();
        var text = BookTextHolder.fromNetwork(buffer);
        var anchor = buffer.readString();
        var condition = BookCondition.fromNetwork(buffer);
        var statusEffectId = buffer.readIdentifier();
        return new BookStatusEffectPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, statusEffectId);
    }

    @Override
    public Identifier getType() {
        return ModonomiconCompat.STATUS_EFFECT_PAGE;
    }

    public Identifier getStatusEffectId() {
        return this.statusEffectId;
    }

    @Override
    public void prerenderMarkdown(BookTextRenderer textRenderer) {
        if (title.isEmpty()) {
            title = new BookTextHolder(statusEffectId.toTranslationKey("effect"));
        }

        super.prerenderMarkdown(textRenderer);
    }

    @Override
    public void toNetwork(RegistryByteBuf buffer) {
        super.toNetwork(buffer);
        buffer.writeIdentifier(this.statusEffectId);
    }

}
