package tp.scraper;

public class ChromDriverSetupException extends RuntimeException {

	private static final String MESSAGE = "\nSetup properly the Chrom Driver. Check a PATH to the driver at a property \""
			+ Scraper.WEB_DRIVER_KEY + "\" in the file \"" + Scraper.INITIAL_PROPERTIES_FILE + "\".";

	public ChromDriverSetupException() {
	}

	public ChromDriverSetupException(Throwable cause) {
		super(cause.getMessage() + MESSAGE);
	}

}
