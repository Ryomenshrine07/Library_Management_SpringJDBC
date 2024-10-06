package Entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;
    private Integer studentId;
    public Book() {
        super();
    }
    public Book(int id, String title, String author, int quantity, Integer studentId) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.studentId = studentId;
    }
    public Book(String title, String author, int quantity) {
        super();
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", studentId=" + studentId +
                '}';
    }
}
