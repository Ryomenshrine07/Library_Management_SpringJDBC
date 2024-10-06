package DAO;

import Entities.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class StudentDAOImple implements StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    public StudentDAOImple(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertStudent(Student student) {
        String insertQuery = "insert into students(id,name,city) values(?,?,?)";
        int result = jdbcTemplate.update(insertQuery,student.getId(),student.getName(), student.getCity());
        System.out.println("Rows affected: " + result);
    }

    public void updateStudent(Student student) {
        String updateQuery = "update students set name=?,city=? where id=?";
        int result = jdbcTemplate.update(updateQuery, student.getName(), student.getCity(), student.getId());
        System.out.println("Rows affected: " + result);
    }

    public void deleteStudent(Student student) {
        String checkQuery = "SELECT COUNT(bookId) FROM transactions WHERE studentId=?";
        Integer count = jdbcTemplate.queryForObject(checkQuery,Integer.class,student.getId());
        if(count != null && count == 0){
            String Query = "DELETE FROM students WHERE id = ?";
            int result = jdbcTemplate.update(Query, student.getId());
            System.out.println("Rows affected: " + result);
        }
        else{
            System.out.println(student.getName()+" have not returned all of your Books....");
            System.out.println("So he can't be removed");
        }
    }

    public Student getStudentById(int id) {
        String query = "select * from students where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCity(rs.getString("city"));
            return student;
        });
    }
    public List<Student> getAllStudents() {
        String query = "select * from students";
         return jdbcTemplate.query(query, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCity(rs.getString("city"));
            return student;
        });
    }
}
