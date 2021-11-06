package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalItems.getTypicalItems;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.model.display.DisplayList;
import seedu.address.model.display.Displayable;

import java.util.List;

@ExtendWith(ApplicationExtension.class)
class DisplayListPanelIntegrationTest {

    private DisplayListPanel displayListPanel;
    private DisplayList displayList;
    private ObservableList<? extends Displayable> listToDisplay;

    @Start
    private void start(Stage stage) {
        listToDisplay = FXCollections.observableList(getTypicalItems());
        displayList = new DisplayList(listToDisplay);
        displayListPanel = new DisplayListPanel(displayList.getFilteredDisplayList());
        stage.setScene(new Scene(new StackPane(displayListPanel.getRoot()), 500, 500));
        stage.show();
    }

    /**
     * Asserts that the display list view has all and only the items the given list.
     * @param robot Should be the same robot as the one injected by the test runner.
     * @param list The query list to compare display list view with.
     */
    private void assertDisplay(FxRobot robot, List<? extends Displayable> list) {
        ListView<Displayable> listView = robot.lookup("#displayListView").query();

        assertEquals(listView.getItems(), list);
    }

    /**
     * Tests if DisplayListPanel is displaying the list given in its constructor.
     */
    @Test
    void constructor_displayingList(FxRobot robot) {
        assertDisplay(robot, getTypicalItems());
    }

    /**
     * Tests if changes to the given list is propagated to DisplayListPanel
     */
    @Test
    void editObservableList_displayListPanelChanges(FxRobot robot) {
        robot.interact(() -> listToDisplay.remove(0));
        assertDisplay(robot, listToDisplay);
    }

    /**
     * Tests if filtering the given list is propagated to DisplayListPanel
     */
    @Test
    void filterObservableList_displayListPanelChanges(FxRobot robot) {
        robot.interact(() -> displayList.setPredicate(x -> x.hashCode() % 2 == 0));
        assertDisplay(robot, displayList.getFilteredDisplayList());
    }

    /**
     * Tests if changing the list (from DisplayList) to display is propagated to DisplayListPanel
     */
    @Test
    void displayListSetItems_displayListPanelChanges(FxRobot robot) {
        ObservableList<Displayable> newListToDisplay = FXCollections.observableList(List.of(getTypicalTransaction()));

        robot.interact(() -> displayList.setItems(newListToDisplay));
        assertDisplay(robot, newListToDisplay);
    }
}