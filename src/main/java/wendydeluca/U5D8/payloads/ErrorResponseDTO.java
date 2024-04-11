package wendydeluca.U5D8.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message,LocalDateTime timestamp) {

}
