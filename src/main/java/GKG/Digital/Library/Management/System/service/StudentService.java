package GKG.Digital.Library.Management.System.service;

import GKG.Digital.Library.Management.System.dto.CreateStudentRequest;
import GKG.Digital.Library.Management.System.entities.Student;
import GKG.Digital.Library.Management.System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public Student findByStudentId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public Student addNewStudent(CreateStudentRequest createStudentRequest) {
        Student student = Student.builder()
                                        .name(createStudentRequest.getName())
                                        .email(createStudentRequest.getEmail())
                                        .department(createStudentRequest.getDepartment())
                                        .rollNumber(createStudentRequest.getRollNumber())
                                        .build();

        return studentRepository.save(student);
    }
}
