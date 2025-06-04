package practise.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import practise.Framework.Login;
import practise.Framework.OrderHistory;

public class BaseTest {

	public WebDriver driver;
	public Login loginDetails;

	public WebDriver driverInvoke() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/practise/resources/GlobalData.properties");

		prop.load(fis);

		String browserType = System.getProperty("driver") != null ? System.getProperty("driver")
				: prop.getProperty("driver");

		// String browserType = prop.getProperty("driver");
		if (browserType.contains("Chrome")) 
		{
			ChromeOptions co = new ChromeOptions();
			if (browserType.contains("headless")) 
			{
				co.addArguments("headless");
			}
			driver = new ChromeDriver(co);
			
			driver.manage().window().setSize(new Dimension(1440,900)); //for headless-->enable full screen
		} 
		else if (browserType.contains("Safari")) 
		{
			driver = new SafariDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	@BeforeMethod(groups = { "MakeOrder" })
	public Login launchApp() throws IOException {
		driver = driverInvoke();
		loginDetails = new Login(driver);
		loginDetails.client_url();
		return loginDetails;
	}

	@AfterMethod(groups = { "MakeOrder" })
	public void closeSession() {

		driver.quit();
	}

	// JSON

	public List<HashMap<String, String>> readJsonFile(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// need to get external dependency Jackson databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

}
