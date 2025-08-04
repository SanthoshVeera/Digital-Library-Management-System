package GKG.Digital.Library.Management.System.dto;

import GKG.Digital.Library.Management.System.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    private String name;

    private Genre genre;

    private String authorName;

    String authorEmail;

    String authorCountry;
}
