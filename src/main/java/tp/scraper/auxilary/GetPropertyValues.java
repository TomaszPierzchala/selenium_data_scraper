package tp.scraper.auxilary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {

	public Properties getPropValues(String propFileName) throws IOException {

		Properties prop = null;

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			prop = new Properties();

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		return prop;
	}
}
