package wendydeluca.U5D8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
public class AuthorPayload {
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private String avatar;
}
