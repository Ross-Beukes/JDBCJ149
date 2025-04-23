import student.dao.StudentRepoImpl;
import student.model.Student;
import student.service.StudentService;
import student.service.StudentServiceImpl;

import java.util.Scanner;

public class View {
    Scanner input = new Scanner(System.in);
    private StudentService studentService;

    public View(StudentService studentService) {
        this.studentService = studentService;
    }

    public void run(){
        while (true) {
            menu();
            System.out.println("Selection : ");
            String selection = input.nextLine();

            switch (selection){
                case "1" : {
                    createStudent();
                    break;
                }
                case "2" : {
                    return;
                }
                default:{}
            }

        }
    }

    public void createStudent(){
        Student student = new Student();

        System.out.println("First name : ");
        student.setFirstName(input.nextLine());
        System.out.println("Surname : ");
        student.setSurname(input.nextLine());
        System.out.println("Email Address : ");
        student.setEmailAddress(input.nextLine());
        System.out.println("ID Number : ");
        student.setIdNumber(input.nextLine());

        try {
            System.out.println("Student Successfully created : " + this.studentService.createStudent(student));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu(){
        System.out.println("""
                1. Create Student
                2. Exit
                """);
    }
    public static void main(String[] args) {
        new View(new StudentServiceImpl(new StudentRepoImpl())).run();
    }
}
