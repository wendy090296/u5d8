package wendydeluca.U5D8.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record AuthorDTO(@NotEmpty(message = "The name is mandatory!")
                        @Size(min = 4, max = 15, message = "Name length must be between 4 and 15 characters.")
                        String name,
                        @NotEmpty(message = "Surname is a  mandatory field.")
                        @Size(min = 4, max = 15, message = "Surname length must be between 4 and 15 characters.")
                        String surname,
                        @Email(message = "Invalid email, try again!")
                        @NotEmpty(message = "Email is mandatory.")
                        @NotBlank(message = "No spaces allowed in the email field.")
                        String email,
                        @NotNull(message = "Date of Birth is mandatory.")
                        LocalDate dateOfBirth) {

}

