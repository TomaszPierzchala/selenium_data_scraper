package tp.scraper;

import java.util.List;
import org.openqa.selenium.WebElement;

public final class Scraper extends ScraperSelenium implements WebElementsPrintout {
	
	public static void main(String[] args) {
		Scraper scraper = null;
		try {
			scraper = new Scraper();

			scraper.loadPage();

			scraper.waitForPageLoaded();

			List<WebElement> webElements = scraper.readInfoEntries();

			scraper.printoutInfoEntries(webElements);

		} catch (IllegalStateException exp) {
			if (exp.getMessage().contains("The driver executable does not exist:")) {
				throw new ChromDriverSetupException(exp);
			} else {
				throw exp;
			}

		} finally {
			if (scraper != null && scraper.driver != null) {
				scraper.driver.quit();
			}
		}
	}

}
