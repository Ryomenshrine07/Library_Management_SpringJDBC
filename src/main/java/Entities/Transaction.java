package Entities;

import java.util.Date;

public class Transaction {
    private int id;
    private int studentId;
    private String studentName;
    private int bookId;
    private Date issueDate;
    private Date returnDate;

    public Transaction() {
        super();
    }

    public Transaction(int studentId, String studentName, int bookId, Date issueDate, Date returnDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    public Transaction(int id,int studentId,int bookId){
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", bookId=" + bookId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
