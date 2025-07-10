package team2.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String T, long id) {
        super(T + " con id " + id + " non trovato.");
    }
}
