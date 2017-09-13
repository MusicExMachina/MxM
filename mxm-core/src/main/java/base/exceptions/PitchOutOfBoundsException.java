package base.exceptions;

public class PitchOutOfBoundsException extends Exception {

    public PitchOutOfBoundsException() {

    }

    public PitchOutOfBoundsException(String message){
        super(message);
    }
}