package student.service;

import student.dao.StudentRepo;
import student.model.Student;

import java.util.Optional;

public class StudentServiceImpl implements StudentService{
    private StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Student createStudent(Student student) throws Exception {
        if (student != null){
            String idNumber = student.getIdNumber();
            if (idNumber != null && idNumber.length() == 13) {
                Optional<Student> studentOptional = this.studentRepo.createStudent(student);
                if (studentOptional.isPresent()) {
                    return studentOptional.get();
                }
            }throw new IllegalArgumentException("Invalid ID Number");
        }
        throw new IllegalArgumentException("Invalid Student!");
    }

    @Override
    public Student findStudentById(Long id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Student updateStudent(Student student) throws Exception {
        return null;
    }

    @Override
    public Student deleteStudent(Student student) throws Exception {
        return null;
    }
}
