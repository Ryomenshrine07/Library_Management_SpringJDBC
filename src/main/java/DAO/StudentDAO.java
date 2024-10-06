package DAO;

import Entities.Student;

import java.util.List;

interface StudentDAO {
    public void insertStudent(Student student);
    public void deleteStudent(Student student);
    public Student getStudentById(int id);
    public List<Student> getAllStudents();
}
