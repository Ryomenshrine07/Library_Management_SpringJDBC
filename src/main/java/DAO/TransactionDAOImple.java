package DAO;

import Entities.Transaction;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class TransactionDAOImple implements TransactionDAO {
    private final JdbcTemplate jdbcTemplate;

    public TransactionDAOImple(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void issueBook(Transaction transaction) {
        String checkQuantity = "SELECT COUNT(quantity) FROM Books WHERE id = ?";
            Integer count = jdbcTemplate.queryForObject(checkQuantity, Integer.class, transaction.getBookId());
            if (count != null && count > 0) {
                String insertTransaction = "INSERT INTO transactions (studentId,studentName,bookId,issueDate,returnDate) VALUES (?,?,?,?,?)";
                int result = jdbcTemplate.update(insertTransaction,transaction.getStudentId(),transaction.getStudentName(),transaction.getBookId(),transaction.getIssueDate(),null);
                String updateQuantity = "UPDATE Books SET quantity = quantity - 1 WHERE id = ?";
                jdbcTemplate.update(updateQuantity, transaction.getBookId());
                System.out.println("Rows Affected: " + result);
            }
            else if(count == null) {
                System.out.println("There is no book with id => " + transaction.getBookId());
            }
            else{
                System.out.println("This book is out of stock................");
            }
    }

    @Override
    public void returnBook(Transaction transaction) {
        String checkTransaction = "SELECT COUNT(id) FROM transactions WHERE studentId = ? AND bookId = ? AND returnDate IS NULL";
        Integer activeTransactionCount = jdbcTemplate.queryForObject(checkTransaction, Integer.class, transaction.getStudentId(), transaction.getBookId());
        if(activeTransactionCount == 0){
            System.out.println("You have not issued book with id => " + transaction.getBookId());
        }
        else{
            String updateQuantity = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";
            jdbcTemplate.update(updateQuantity, transaction.getBookId());
            String updateTransaction = "UPDATE transactions SET returnDate = ? WHERE id = ?";
            int result = jdbcTemplate.update(updateTransaction, new java.sql.Date(System.currentTimeMillis()), transaction.getId());
            System.out.println("Book returned successfully. Rows affected: " + result);
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        String query = "SELECT * FROM transactions WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id},(rs, rowNum) ->{
           Transaction transaction = new Transaction();
           transaction.setStudentId(rs.getInt("studentId"));
           transaction.setStudentName(rs.getString("studentName"));
           transaction.setBookId(rs.getInt("bookId"));
           transaction.setIssueDate(rs.getDate("issueDate"));
           transaction.setReturnDate(rs.getDate("returnDate"));
           return transaction;
        });
    }

    @Override
    public List<Transaction> showAllTransactions() {
        String query = "SELECT * FROM transactions";
        return jdbcTemplate.query(query,(rs, rowNum) -> {
           Transaction transaction = new Transaction();
           transaction.setStudentId(rs.getInt("studentId"));
           transaction.setStudentName(rs.getString("studentName"));
           transaction.setBookId(rs.getInt("bookId"));
           transaction.setIssueDate(rs.getDate("issueDate"));
           transaction.setReturnDate(rs.getDate("returnDate"));
           return transaction;
        });
    }
}
