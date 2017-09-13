package base.exceptions;

public class IntervalOutOfBoundsException extends Exception {

    public IntervalOutOfBoundsException() {

    }

    public IntervalOutOfBoundsException(String message){
        super(message);
    }
}