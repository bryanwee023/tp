package seedu.address.model.display;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.TransactionRecord;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of an {@code transaction}.
 */
public class TransactionCard extends UiPart<Region> {

    private static final String FXML = "TransactionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TransactionRecord transaction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label index;
    @FXML
    private Label timestamp;
    @FXML
    private Label id;
    @FXML
    private Label totalPrice;
    @FXML
    private Label totalItems;

    /**
     * Creates a {@code TransactionCard} with the given {@code TransactionRecord} and index to display.
     */
    public TransactionCard(TransactionRecord transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;
        index.setText(displayedIndex + ". ");
        timestamp.setText(transaction.getTimeString());
        id.setText(transaction.getId());

        Double sp = transaction.getOrderItems().stream()
                .map(item -> item.getCount() * item.getSalesPrice()).reduce((a, b) -> a + b).get();
        totalPrice.setText(String.format("Total price: $%.2f", sp));
        totalItems.setText(String.format("Total items: %d", transaction.getOrderItems().size()));
    }
    
}
