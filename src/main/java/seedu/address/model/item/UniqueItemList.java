package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * An item is considered unique by comparing using {@code Item#isSameItem(Item)}. As such, adding and updating of
 * items uses Item#Item(Item) for equality so as to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueItemList. However, the removal of a item uses Item#equals(Object) so
 * as to ensure that the item with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Item#isSameItem(Item)
 */
public class UniqueItemList implements Iterable<Item> {

    private final ObservableList<Item> internalList = FXCollections.observableArrayList();
    private final ObservableList<Item> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     * @see Item#isSameItem(Item)
     */
    public boolean contains(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(x -> toCheck.isSameItem(x) && x.getCount() > 0);
    }
    /**
     * Returns true if the list contains an equivalent item as the given argument.
     * @see Item#isSameItem(Item)
     */
    public boolean containsID(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(x -> toCheck.isSameId(x));
    }
    /**
     * Returns true if the list contains an equivalent name as the given argument.
     * @see Item#isSameItem(Item)
     */
    public boolean containsName(Item toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(x -> toCheck.isSameName(x));
    }

    /**
     * Returns true if item list is empty.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns true if the list contains an item that matches the given {@code ItemDescriptor}
     */
    public List<Item> get(ItemDescriptor descriptor) {
        requireNonNull(descriptor);
        return internalList.stream()
                .filter(descriptor::isMatch)
                .collect(Collectors.toList());
    }

    /**
     * Returns an optional of the item in the list with the same identity fields.
     * If item does not exist, return an empty optional.
     * @see Item#isSameItem(Item)
     */
    public Optional<Item> getItem(Item item) {
        requireNonNull(item);
        return internalList.stream().filter(item::equals).findFirst();
    }

    /**
     * Adds an item to the list.
     * The item must not already exist in the list.
     */
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);

        getItem(toAdd);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the list.
     */
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSameItem(editedItem) && contains(editedItem)) {
            throw new DuplicateItemException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the specified count of the equivalent item from the list.
     * The item must exist in the list.
     */
    public void remove(Item toRemove) {
        requireNonNull(toRemove);

        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }
        internalList.setAll(items);
    }

    /**
     * Sorts the item list using the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    public void sortItems(Comparator<Item> comparator) {
        requireNonNull(comparator);
        internalList.sort(comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Item> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Item> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueItemList // instanceof handles nulls
                        && internalList.equals(((UniqueItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code items} contains only unique items.
     */
    private boolean itemsAreUnique(List<Item> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSameItem(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
