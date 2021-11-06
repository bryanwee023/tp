package seedu.address.model.display;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of an {@code Item}.
 */
public class ItemCard extends UiPart<Region> {

    private static final String FXML = "ItemListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private Label count;
    @FXML
    private Label costPrice;
    @FXML
    private Label salesPrice;

    /**
     * Creates a {@code ItemCard} with the given {@code Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super(FXML);
        this.item = item;
        index.setText(displayedIndex + ". ");
        name.setText(item.getName().fullName);
        id.setText(String.format("#%06d", item.getId()));
        count.setText(String.format("Quantity: %d", item.getCount()));
        costPrice.setText(String.format("Cost Price: $ %.2f", item.getCostPrice()));
        salesPrice.setText(String.format("Sales Price: $ %.2f", item.getSalesPrice()));
        item.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}
