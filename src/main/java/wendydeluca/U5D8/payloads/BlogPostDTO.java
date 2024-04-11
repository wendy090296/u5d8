package wendydeluca.U5D8.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record BlogPostDTO(@NotEmpty(message ="Category field is mandatory.")
                          @Size(min = 5, max = 20, message = "Category field length must be between 5 and 20 characters. ")
                          String category,
                          @NotEmpty(message ="Title field is mandatory." )
                          @NotBlank()
                          String title,
                          String coverUrl,
                          String content,
                          int timeOfReading,
                          long authorId) {


}
