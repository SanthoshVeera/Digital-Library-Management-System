package GKG.Digital.Library.Management.System.controller;

import GKG.Digital.Library.Management.System.dto.CreateStudentRequest;
import GKG.Digital.Library.Management.System.entities.Student;
import GKG.Digital.Library.Management.System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("{id}")
    Student findById(@PathVariable Long id) {
        return studentService.findByStudentId(id);
    }

    @PostMapping("/add")
    Student addNewStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.addNewStudent(createStudentRequest);
    }
}
