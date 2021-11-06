package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COSTPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALESPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;

public class AddCommandIntegrationTest {

    private Model model = new ModelManager();

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_newItem_addSuccessful() {

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR)
                .withCount(VALID_COUNT_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .build();

        Item validItem = new ItemBuilder(BAGEL)
                .withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_NEW, validItem);
        Model expectedModel = new ModelManager();
        expectedModel.addItem(validItem);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_newItemNoId_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_INCOMPLETE_INFO);
    }

    @Test
    public void execute_newItemNoCostPrice_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_INCOMPLETE_INFO);
    }

    @Test
    public void execute_newItemNoSalesPrice_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_INCOMPLETE_INFO);
    }


    @Test
    public void execute_newItemNoName_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(1)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_INCOMPLETE_INFO);
    }

    @Test
    public void execute_existingItemNameDescription_restockSuccessful() {
        model.addItem(BAGEL.updateCount(5));

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        Model expectedModel = new ModelManager();
        expectedModel.addItem(BAGEL.updateCount(10));

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existingItemIdDescription_restockSuccessful() {
        model.addItem(BAGEL.updateCount(5));

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        ModelManager expectedModel = new ModelManager();
        expectedModel.addItem(BAGEL.updateCount(10));

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleMatches_failure() throws Exception {
        model.addItem(BAGEL);
        model.addItem(DONUT);

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertCommandFailure(addCommand, model, AddCommand.MESSAGE_MULTIPLE_MATCHES);
    }
}
