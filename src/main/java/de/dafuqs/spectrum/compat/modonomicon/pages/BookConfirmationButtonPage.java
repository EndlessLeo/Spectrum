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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class BookConfirmationButtonPage extends BookTextPage {

    private final Identifier checkedAdvancement;
    private BookTextHolder buttonText;
    private BookTextHolder confirmedButtonText;
    private final String confirmationString;

    public BookConfirmationButtonPage(BookTextHolder title, BookTextHolder text, boolean useMarkdownInTitle, boolean showTitleSeparator, String anchor, BookCondition condition, Identifier checkedAdvancement, BookTextHolder buttonText, BookTextHolder confirmedButtonText, String confirmationString) {
        super(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition);
        this.checkedAdvancement = checkedAdvancement;
        this.buttonText = buttonText;
        this.confirmedButtonText = confirmedButtonText;
        this.confirmationString = confirmationString;
    }

    public static BookConfirmationButtonPage fromJson(Identifier entryId, JsonObject json, RegistryWrapper.WrapperLookup provider) {
        var title = BookGsonHelper.getAsBookTextHolder(json, "title", BookTextHolder.EMPTY, provider);
        var useMarkdownInTitle = JsonHelper.getBoolean(json, "use_markdown_title", false);
        var showTitleSeparator = JsonHelper.getBoolean(json, "show_title_separator", true);
        var text = BookGsonHelper.getAsBookTextHolder(json, "text", BookTextHolder.EMPTY, provider);
        var anchor = JsonHelper.getString(json, "anchor", "");
        var condition = json.has("condition")
                ? BookCondition.fromJson(entryId, json.getAsJsonObject("condition"), provider)
                : new BookNoneCondition();
        var checkedAdvancement = Identifier.tryParse(JsonHelper.getString(json, "checked_advancement"));
        var buttonText = BookGsonHelper.getAsBookTextHolder(json, "button_text", BookTextHolder.EMPTY, provider);
        var confirmedButtonText = BookGsonHelper.getAsBookTextHolder(json, "button_text_confirmed", BookTextHolder.EMPTY, provider);
        var confirmationString = JsonHelper.getString(json, "confirmation", "");
        return new BookConfirmationButtonPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, checkedAdvancement, buttonText, confirmedButtonText, confirmationString);
    }

    public static BookConfirmationButtonPage fromNetwork(RegistryByteBuf buffer) {
        var title = BookTextHolder.fromNetwork(buffer);
        var useMarkdownInTitle = buffer.readBoolean();
        var showTitleSeparator = buffer.readBoolean();
        var text = BookTextHolder.fromNetwork(buffer);
        var anchor = buffer.readString();
        var condition = BookCondition.fromNetwork(buffer);
        var checkedAdvancement = buffer.readIdentifier();
        var buttonText = BookTextHolder.fromNetwork(buffer);
        var confirmedButtonText = BookTextHolder.fromNetwork(buffer);
        var confirmationString = buffer.readString();
        return new BookConfirmationButtonPage(title, text, useMarkdownInTitle, showTitleSeparator, anchor, condition, checkedAdvancement, buttonText, confirmedButtonText, confirmationString);
    }

    @Override
    public void prerenderMarkdown(BookTextRenderer textRenderer) {
        super.prerenderMarkdown(textRenderer);

        if (!buttonText.hasComponent()) {
            buttonText = new BookTextHolder(Text.translatable(buttonText.getKey()));
        }

        if (!confirmedButtonText.hasComponent()) {
            confirmedButtonText = new BookTextHolder(Text.translatable(confirmedButtonText.getKey()));
        }
    }

    public Identifier getCheckedAdvancement() {
        return checkedAdvancement;
    }

    public BookTextHolder getButtonText() {
        return buttonText;
    }

    public BookTextHolder getConfirmedButtonText() {
        return confirmedButtonText;
    }

    public String getConfirmationString() {
        return confirmationString;
    }

    @Override
    public Identifier getType() {
        return ModonomiconCompat.CONFIRMATION_BUTTON_PAGE;
    }

    @Override
    public void toNetwork(RegistryByteBuf buffer) {
        super.toNetwork(buffer);
        buffer.writeIdentifier(checkedAdvancement);
        buttonText.toNetwork(buffer);
        confirmedButtonText.toNetwork(buffer);
        buffer.writeString(confirmationString);
    }

}
