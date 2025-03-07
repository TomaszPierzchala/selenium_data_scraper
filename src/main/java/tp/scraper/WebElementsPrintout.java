package tp.scraper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebElementsPrintout {
    default void printoutInfoEntries(List<WebElement> readInfoEntries) {
		final String PRINT_FORMAT = "%-75.75s | %-25s%n";

		for (WebElement entry : readInfoEntries) {
			String href = entry.getAttribute("href");
			String title = entry.findElement(By.tagName("span")).getText();
			System.out.printf(PRINT_FORMAT, title, href);
		}

	}
}