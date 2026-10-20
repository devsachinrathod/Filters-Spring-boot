package in.strikes.filterDemo.service;

import in.strikes.filterDemo.DTO.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(Student student){
        System.out.println( "student created successfully");
        System.out.println("This is the student details " + student.getName() + student.getEmail());
    }
}
