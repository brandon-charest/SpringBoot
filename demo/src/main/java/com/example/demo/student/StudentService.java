package com.example.demo.student;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student student) {
    Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

    if (studentByEmail.isPresent()) {
      throw new IllegalStateException("email already exists");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    if (!studentExists(studentId)) {
      throw new IllegalStateException("Student with id " + studentId + " does not exists.");
    }

    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    if (!studentExists(studentId)) {
      throw new IllegalStateException("Student with id " + studentId + " does not exists.");
    }

    Student student = studentRepository.getById(studentId);

    if (name != null
        && name.length() > 0
        && !Objects.equals(student.getName(), name)) {
      student.setName(name);
    }

    if (email != null
        && email.length() > 0
        && !Objects.equals(student.getEmail(), email)) {
      student.setEmail(email);
    }

    studentRepository.save(student);
  }
  private boolean studentExists(Long studentId) {
    return studentRepository.existsById(studentId);
  }

}
