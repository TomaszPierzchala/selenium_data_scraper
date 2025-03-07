package tp.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import tp.scraper.auxilary.GetPropertyValues;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ScraperSelenium {
    static final String INITIAL_PROPERTIES_FILE = "initial.properties";

	static final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
	private static final String SCRAPE_WEB_PAGE_KEY = "scrape.web.page";
	private static final String XPATH_KEY = "scrape.xpath";

    static final long WAIT_BEFORE_CHECK_READY_STATE_IN_SECONDS = 1;
	static final long WAIT_TIME_OUT_IN_SECONDS = 30;

    protected WebDriver driver = null;

    protected Properties initProps;

    private void readProperties() {
		GetPropertyValues getProps = new GetPropertyValues();
		try {
			initProps = getProps.getPropValues(INITIAL_PROPERTIES_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    private void setupChromDriver() {
		System.setProperty(WEB_DRIVER_KEY, initProps.getProperty(WEB_DRIVER_KEY));
		WebDriverManager.chromedriver().setup();
		// silent mode - no browser window
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless=new");
		//
		driver = new ChromeDriver(options);
	}

	protected ScraperSelenium() {
		readProperties();
		setupChromDriver();
	}
	
    protected void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver _driver) {
				return ((JavascriptExecutor) _driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(WAIT_BEFORE_CHECK_READY_STATE_IN_SECONDS * 1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_OUT_IN_SECONDS));
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

    protected void loadPage(){
		driver.get(initProps.getProperty(SCRAPE_WEB_PAGE_KEY));
	}

	protected List<WebElement> readInfoEntries() {
		String xpath = initProps.getProperty(XPATH_KEY);
		List<WebElement> links = driver.findElements(By.xpath(xpath));

		return links;
	}
}