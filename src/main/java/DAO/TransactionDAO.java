package DAO;

import Entities.Transaction;

import java.util.List;

public interface TransactionDAO {
    public void issueBook(Transaction transaction);
    public void returnBook(Transaction transaction);
    public Transaction getTransactionById(int id);
    public List<Transaction> showAllTransactions();
}
