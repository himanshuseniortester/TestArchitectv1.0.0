package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; // log4J
import org.apache.logging.log4j.Logger; //log4j

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity","Reggresion","Master","DataDriven"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		
		//Loading Properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
				p = new Properties();
				p.load(file);

		logger = LogManager.getLogger(this.getClass());
		
		//For remote execution in Selenium Grid
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
		    DesiredCapabilities capabilities = new DesiredCapabilities();

		    // os
		    if (os.equalsIgnoreCase("windows")) {
		        capabilities.setPlatform(Platform.WIN11);
		    } else if (os.equalsIgnoreCase("mac")) {
		        capabilities.setPlatform(Platform.MAC);
		    } else if (os.equalsIgnoreCase("linux")) {
		    	capabilities.setPlatform(Platform.LINUX);
		    }
		    
		    else {
		        System.out.println("No matching os");
		        return;
		    }

		    // browser
		    switch (br.toLowerCase()) {
		        case "chrome":
		            capabilities.setBrowserName("chrome");
		            break;
		        case "edge":
		            capabilities.setBrowserName("MicrosoftEdge");
		            break;
		        default:
		            System.out.println("No matching browser");
		            return; 
		
		    }
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		default : System.out.println("..Invalid Browser Name ...."); return;
		}
		
		}
		
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL"));   // reading URL from Properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

	}
	
	@AfterClass(groups={"Sanity","Reggresion","Master","DataDriven"})
	public void tearDown() {
		driver.quit();

	}

	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomeAlphaNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	}

	public void scrollDownPage() {
		// Create JavascriptExecutor instance
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down by half the viewport height
		js.executeScript("window.scrollBy(0, window.innerHeight / 2);");

		// Optional: Wait to observe the scrolling effect (for demo purposes)
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}
}
