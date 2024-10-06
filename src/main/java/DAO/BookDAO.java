package DAO;

import Entities.Book;

import java.util.List;

public interface BookDAO {
    public void addBook(Book book);
//    public void updateBook(Book book);
    public void deleteBook(int id);
    public Book getBookById(int id);
    public List<Book> getAllBooks();
}
