package team2.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Formato inserito non valido.");
    }
}
