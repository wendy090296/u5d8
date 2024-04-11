package wendydeluca.U5D8.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;


public record BlogPostDTO(@NotEmpty(message ="Category field is mandatory.")
                          @Size(min = 5, max = 20, message = "Category field length must be between 5 and 20 characters. ")
                          String category,
                          @NotEmpty(message ="Title field is mandatory." )
                          @NotBlank(message = "No spaces are allowed in title.")
                          @Size(min = 10, max = 30, message = "The title must be between 10 and 30 characters.")
                          String title,
                          @URL(message = "Invalid URL, try again.")
                          @NotEmpty(message = "The URL is mandatory.")
                          String coverUrl,
                          @NotEmpty(message = "The content is mandatory.")
                          @Size(min = 20, max = 50, message = "The content must be between 20 and 50 words.")
                          String content,
                          @NotNull(message = "Reading time field must be declared.")
                          int timeOfReading,
                          @NotNull(message = "Author Id must be declared.")
                          long authorId) {


}
