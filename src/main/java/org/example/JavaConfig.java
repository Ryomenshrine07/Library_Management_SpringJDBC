package org.example;

import DAO.BookDAOImple;
import DAO.TransactionDAOImple;
import Entities.Student;
import DAO.StudentDAOImple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.example")
public class JavaConfig {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springJDBC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234@zoro");
        return dataSource;
    }
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
    @Bean("student")
    Student getStudent() {
        return new Student();
    }
    @Bean("studentDao")
    public StudentDAOImple getStudentDAO() {
        return new StudentDAOImple(getJdbcTemplate());
    }
    @Bean("bookDao")
    public BookDAOImple getBookDAO() {
        return new BookDAOImple(getJdbcTemplate());
    }
    @Bean("transactionDao")
    public TransactionDAOImple getTransactionDAO() {
        return new TransactionDAOImple(getJdbcTemplate());
    }
    @Bean("app")
    public App getApp() {
        return new App(getStudentDAO(),getTransactionDAO(),getBookDAO());
    }
}
