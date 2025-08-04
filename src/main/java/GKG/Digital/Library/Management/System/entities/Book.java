package GKG.Digital.Library.Management.System.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
