package org.example;

import DAO.BookDAOImple;
import DAO.StudentDAOImple;
import DAO.TransactionDAOImple;
import Entities.Book;
import Entities.Student;
import Entities.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App
{
    Scanner sc = new Scanner(System.in);
    private final StudentDAOImple studentDAO;
    private final TransactionDAOImple transactionDAO;
    private final BookDAOImple bookDAO;

    public App(StudentDAOImple studentDAO, TransactionDAOImple transactionDAO, BookDAOImple bookDAO){
        this.studentDAO = studentDAO;
        this.transactionDAO = transactionDAO;
        this.bookDAO = bookDAO;
    }
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        App app =  context.getBean("app", App.class);
        app.run();
    }
    public void run(){
        int choice = 1;
        while(choice >= 1 && choice <= 2){
            System.out.println("---------------------------------------------------------------");
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as Student");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            System.out.println("---------------------------------------------------------------");
            switch(choice){
                case 1: adminMethods();
                break;
                case 2: studentMethods();
                break;
                default: break;
            }
        }
    }
    public void adminMethods(){
        int choice = 1;
        while(choice >= 1 && choice <= 5){
            System.out.println("1. Add New Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Get All Books");
            System.out.println("4. Get All Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Show All Transactions");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            switch(choice){
                case 1 -> {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Enter Book ID");
                    int bookID = sc.nextInt();
                    System.out.println("Enter Book Title");
                    String bookTitle = sc.next();
                    System.out.println("Enter Book Author");
                    String bookAuthor = sc.next();
                    int bookQuantity = 3;
                    Book book = new Book(bookID, bookTitle, bookAuthor, bookQuantity,null);
                    System.out.println();
                    bookDAO.addBook(book);
                    System.out.println();
                    System.out.println("---------------------------------------------------------------");
                }
                case 2 -> {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Enter Book ID");
                    int bookID = sc.nextInt();
                    System.out.println();
                    bookDAO.deleteBook(bookID);
                    System.out.println();
                    System.out.println("---------------------------------------------------------------");
                }
                case 3 -> {
                    System.out.println("---------------------------------------------------------------");
                    List<Book> books = bookDAO.getAllBooks();
                    books.forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------");
                }
                case 4 -> {
                    System.out.println("---------------------------------------------------------------");
                    List<Student> students = studentDAO.getAllStudents();
                    students.forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------");
                }
                case 5 -> {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Enter Student ID");
                    int studentID = sc.nextInt();
                    System.out.println("Enter Student Name");
                    String studentName = sc.next();
                    Student student = new Student(studentID,studentName);
                    studentDAO.deleteStudent(student);
                    System.out.println("---------------------------------------------------------------");
                }
                case 6 -> {
                    System.out.println("---------------------------------------------------------------");
                    List<Transaction> transactions = transactionDAO.showAllTransactions();
                    transactions.forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------");
                }
            }
        }
    }
    public void studentMethods(){
        int choice = 1;
        while(choice >= 1 && choice <= 3){
            System.out.println("1. Show All Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("0. Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            switch(choice){
                case 1 -> {
                    System.out.println("---------------------------------------------------------------");
                    bookDAO.getAllBooks().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------");
                }
                case 2 -> {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Enter Your ID :");
                    int id = sc.nextInt();
                    System.out.println("Enter your name");
                    String name = sc.next();
                    System.out.println("Enter your City");
                    String city = sc.next();
                    System.out.println("Enter Book Id Which you want to issue :");
                    int bookID = sc.nextInt();
                    Student student = new Student(id,name,city);
                    studentDAO.insertStudent(student);
                    Transaction transaction = new Transaction(student.getId(),student.getName(),bookID,new Date(),null);
                    transactionDAO.issueBook(transaction);
                    System.out.println("---------------------------------------------------------------");
                }
                case 3 -> {
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Enter Your ID :");
                    int studentId = sc.nextInt();
                    System.out.println("Enter your Book ID Which you want to return:");
                    int bookID = sc.nextInt();
                    System.out.println("Enter Your Transaction Id :");
                    int transactionID = sc.nextInt();
                    Transaction transaction = new Transaction(transactionID,studentId,bookID);
                    transactionDAO.returnBook(transaction);
                    System.out.println("---------------------------------------------------------------");
                }
            }
        }
    }
}
