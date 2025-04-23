import config.DBConfig;
import student.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCQueryExample {

    public Student createStudent(Student student){
        String query = "INSERT INTO students(first_name, second_name, id_number, email_address) VALUES(?,?,?,?)";
        try(DBConfig dbConfig = new DBConfig(); PreparedStatement ps = dbConfig.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getIdNumber());
            ps.setString(4, student.getEmailAddress());

            if(ps.executeUpdate() > 0 ){
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        student.setStudentId(rs.getLong(1));
                        return student;
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Student> getAllStudents(){
        String query = "SELECT * FROM students";
        try(DBConfig config = new DBConfig();
            Connection con = config.getCon();
            PreparedStatement ps = con.prepareStatement(query)
        ){
            try(ResultSet rs = ps.executeQuery()){
                List<Student> studentList = new ArrayList<>();
                while (rs.next()){
                    long studentId = rs.getLong("student_id");

                    Student student = Student.builder()
                            .studentId(studentId)
                            .firstName(rs.getString("first_name"))
                            .surname(rs.getString("second_name"))
                            .emailAddress(rs.getString("email_address"))
                            .idNumber(rs.getString("id_number"))
                            .build();

                    studentList.add(student);
                }
                return studentList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDBCQueryExample example = new JDBCQueryExample();

        System.out.println(
                example.createStudent(
                        Student.builder()
                            .firstName(scanner.nextLine())
                            .surname(scanner.nextLine())
                            .emailAddress(scanner.nextLine())
                            .idNumber(scanner.nextLine())
                            .build())
        );

        List<Student> allStudents = example.getAllStudents();

        allStudents.forEach(System.out::println);
    }
}
