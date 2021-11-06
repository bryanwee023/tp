package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.javafx.collections.ImmutableObservableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.display.Displayable;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tests that all ui components are wired together correctly.
 */
@ExtendWith(ApplicationExtension.class)
public class MainWindowTest {

    MainWindow mainWindow;
    LogicStubWithUiMethods dummyLogic = new LogicStubWithUiMethods();

    @Start
    private void start(Stage stage) {
        mainWindow = new MainWindow(stage, dummyLogic);
        stage.setScene(mainWindow.getRoot().getScene());
        stage.show();
    }

    /**
     * Tests that there are no fatal errors in FXML components.
     * If a fatal error exists, test will fail (e.g. syntax error)
     */
    @Test
    public void mainWindowStart_isShowing(FxRobot robot) {
        robot.interact(() -> mainWindow.fillInnerParts()); // Start up other ui components

        assertTrue(mainWindow.getPrimaryStage().isShowing());
    }

    /**
     * Only supports methods that initialising of MainWindow needs.
     * Other methods (e.g. those mutating inventory) should not be called.
     */
    private class LogicStubWithUiMethods implements Logic {

        private GuiSettings guiSettings = new GuiSettings();

        @Override
        public CommandResult execute(String commandText) throws CommandException, ParseException {
            return new CommandResult(commandText);
        }

        @Override
        public ReadOnlyInventory getInventory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Displayable> getFilteredDisplayList() {
            return new ImmutableObservableList<>();
        }

        @Override
        public Path getInventoryFilePath() {
            return Paths.get("/dummy/path");
        }

        @Override
        public GuiSettings getGuiSettings() {
            return guiSettings;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            this.guiSettings = guiSettings;
        }
    }
}
