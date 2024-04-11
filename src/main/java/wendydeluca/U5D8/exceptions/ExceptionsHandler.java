package wendydeluca.U5D8.exceptions;

import org.apache.coyote.BadRequestException;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wendydeluca.U5D8.payloads.ErrorResponseDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponseDTO handleBadRequest(BadRequestException ex){
        return new ErrorResponseDTO(ex.getMessage(), LocalDateTime.now());

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //401
    public ErrorResponseDTO handleNotFound(ChangeSetPersister.NotFoundException ex){
        return new ErrorResponseDTO(ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ErrorResponseDTO handleInternalError(Exception ex){
        ex.printStackTrace(); //stampiamo a schermo l'errore per andare a risolverlo!
        return new ErrorResponseDTO("Internal server error", LocalDateTime.now());
    }



}
