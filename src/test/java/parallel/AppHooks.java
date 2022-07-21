package parallel;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.factory.DriverFactoryParallel;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AppHooks {

	protected WebDriver driver;
	private ConfigReader configReader;
	private Properties prop;
	private DriverFactory driverFactory;
	private DriverFactoryParallel driverFactoryParallel;

	// Create object of SimpleDateFormat class and decide the format
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	// get current date time with Date()
	Date date = new Date();
	// Now format the date
	String date1 = dateFormat.format(date);
	String date2 = date1.replace("/", "_");
	String date3 = date2.replace(":", "_");
	String date4 = date3.replace(" ", "_");
	String path;
	

	@Before(order = 1)
	public void initPropertyFile() throws IOException {
		configReader = new ConfigReader();
		prop = configReader.initProperties();
	}

	@Before(order = 2)
	public void launchBrowser() {
//		driverFactory = new DriverFactory();
		driverFactoryParallel = new DriverFactoryParallel();
		String browser = prop.getProperty("browser");
//		driverFactory.initBrowser(browser);
//		driver = DriverFactory.getDriver();
		driver = driverFactoryParallel.init_driver(browser);
		
	}


	@Before(order = 3)
	public void createOutputFolder(Scenario sc) throws IOException {
		path = "C:\\Shameer\\Selenium\\CucumberTestNG\\target\\Screenshots\\" + sc.getName().replace(" ", "_") + "_"+ date4 ;
		File file = new File(path );
		file.mkdir();

	}

	@AfterStep
	public void takeScreenshot() throws IOException {

		// Take a ScreenShot
		byte[] scrBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		// convert the Bytes to File type
		File file = OutputType.FILE.convertFromPngBytes(scrBytes);
		// store the converted file as Image
		FileUtils.copyFile(file, new File(path+"\\"+"screencapture_"+ date4 + System.currentTimeMillis() +".png"), true);
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
}
