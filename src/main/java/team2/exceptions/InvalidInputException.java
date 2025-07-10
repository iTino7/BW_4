package team2.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Valore inserito non valido.");
    }
}
