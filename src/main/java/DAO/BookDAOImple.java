package DAO;

import Entities.Book;
import Entities.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

public class BookDAOImple implements BookDAO {
    private JdbcTemplate jdbcTemplate;
    public BookDAOImple(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        String addQuery = "INSERT INTO Books(id,title,author,quantity) VALUES(?,?,?,?)";
        int result = jdbcTemplate.update(addQuery,book.getId(),book.getTitle(),book.getAuthor(),book.getQuantity());
        System.out.println("Row affected: " + result);
    }

    @Override
    public void deleteBook(int id) {
        String checkQuery = "SELECT COUNT(bookId) From transactions WHERE bookId = ? AND returnDate IS NULL";
        Integer count = jdbcTemplate.queryForObject(checkQuery,Integer.class,id);
        if(count == 0){
            String deleteQuery = "DELETE FROM Books WHERE id=?";
            int result = jdbcTemplate.update(deleteQuery,id);
            System.out.println("Row affected: " + result);
        }
        else{
            System.out.println("Can't Delete This Book, It is issued By These Students :");
            String query = "SELECT studentName FROM transactions WHERE bookId = ?";
            List<String> students = jdbcTemplate.query(query,new Object[]{id},(rs, rowNum)->{
                return rs.getString("studentName");
            });
            students.forEach(System.out::println);
        }
    }

    @Override
    public Book getBookById(int id) {
        String selectQuery = "SELECT * FROM Books WHERE id=?";
        return jdbcTemplate.queryForObject(selectQuery,new Object[]{id},(rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setQuantity(rs.getInt("quantity"));
            return book;
        });
    }

    @Override
    public List<Book> getAllBooks() {
        String query = "SELECT * FROM Books";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setQuantity(rs.getInt("quantity"));
            return book;
        });
    }
}
