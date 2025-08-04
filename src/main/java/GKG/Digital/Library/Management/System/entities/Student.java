package GKG.Digital.Library.Management.System.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private Integer rollNumber;

    @Column(unique = true)
    private String email;

    private Department department;

}
