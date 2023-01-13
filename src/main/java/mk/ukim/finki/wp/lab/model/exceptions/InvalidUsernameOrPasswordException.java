package mk.ukim.finki.wp.lab.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password exception");
    }
}
