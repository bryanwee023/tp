package seedu.address.testutil;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.TransactionList;
import seedu.address.model.order.TransactionRecord;

public class TypicalTransactions {

    public static final String TRANSACTION_ID_BAGEL = "bagelid";
    public static final String TRANSACTION_ID_DONUT = "coffeeid";
    public static final Instant TRANSACTION_TIME_BAGEL = Instant.parse("2021-10-28T15:31:50.225994Z");
    public static final Instant TRANSACTION_TIME_DONUT = Instant.parse("2021-11-28T15:34:50.225994Z");

    /**
     * Returns an {@code transaction}.
     */
    public static TransactionList getTypicalTransaction() {
        ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();
        transactions.add(new TransactionRecord(List.of(TypicalItems.BAGEL),
                TypicalTransactions.TRANSACTION_ID_BAGEL, TypicalTransactions.TRANSACTION_TIME_BAGEL));
        transactions.add(new TransactionRecord(List.of(TypicalItems.DONUT),
                TypicalTransactions.TRANSACTION_ID_DONUT, TypicalTransactions.TRANSACTION_TIME_DONUT));
        return new TransactionList(transactions);
    }

}
