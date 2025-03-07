# selenium_data_scraper
Simple demo, shows how to use Selenium for Web data scraping

This Selenium uses real locally installed Chrome web browser.
A needed web browser driver is automatically installed by Maven `webdrivermanager`.

Path to a driver executable, as well as a page to scrape and a scrape XPath are in a `src/main/resources/initial.properties` file.  
```README.md
...
scrape.web.page=https://www.washingtonpost.com

scrape.xpath=(//a[@href and span and contains(@href, 'https://www.washingtonpost.com')])[position() >= 1 and position() <= 10]

```