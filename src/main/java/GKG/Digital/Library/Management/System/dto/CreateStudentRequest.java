package GKG.Digital.Library.Management.System.dto;

import GKG.Digital.Library.Management.System.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {


    @NotBlank
    private String name;

    @UniqueElements
    private Integer rollNumber;

    @UniqueElements
    private String email;

    private Department department;
}
