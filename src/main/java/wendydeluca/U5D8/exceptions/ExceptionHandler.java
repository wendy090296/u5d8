package wendydeluca.U5D8.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorPayload handleBadRequest(BadRequestException ex){
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //401
    public ErrorPayload handleNotFound(NotFoundException ex){
        return new ErrorPayload(ex.getMessage(),LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public ErrorPayload handleInternalError(Exception ex){
        ex.printStackTrace(); //stampiamo a schermo l'errore per andare a risolverlo!
        return new ErrorPayload("Internal server error", LocalDateTime.now());
    }



}
