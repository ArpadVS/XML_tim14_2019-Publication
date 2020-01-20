package publications.exceptions;

public class MarshallingFailedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarshallingFailedException(String message) {
        super(message);
    }
}
