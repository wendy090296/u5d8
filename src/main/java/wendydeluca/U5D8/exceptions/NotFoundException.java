package wendydeluca.U5D8.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("The item with id" + id + " "+ "has not been found!");
    }
}
