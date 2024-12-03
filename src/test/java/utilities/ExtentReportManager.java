package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	// Method called at the start of the test context
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);*/
		
        // Timestamp to create unique report names
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Configuring the ExtentSparkReporter
        sparkReporter = new ExtentSparkReporter("./reports/" + repName); // Specify location of the report
        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the report
        sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK); // Set theme for the report

        // Initializing ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Adding system information to the report
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups 
         = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());}
        }

        public void onTestSuccess(ITestResult result) {
            test = extent.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups()); // to display groups in report
            test.log(Status.PASS, result.getMethod().getMethodName() 
         + " successfully executed");
        
    }

	/*// Method called when a test starts
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); // Create a new test in the report
	}*/



	public void onTestFailure(ITestResult result) {
	    test = extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());

	    test.log(Status.FAIL, result.getName()
	 + " got failed");
	    test.log(Status.INFO, result.getThrowable().getMessage());

	    try {
	        String imgPath = new BaseClass().captureScreen(result.getName());
	        test.addScreenCaptureFromPath(imgPath);

	    } catch (IOException e1) {
	        e1.printStackTrace();

	    }
	}

	// Method called when a test is skipped
	public void onTestSkipped(ITestResult result) {
	    test = extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP, result.getName()
	 + " got skipped");
	    test.log(Status.INFO, result.getThrowable().getMessage());

	}

	// Method called when the test context finishes
	public void onFinish(ITestContext testContext) {
	    extent.flush();

	    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	    File extentReport = new File(pathOfExtentReport);

	    try 
	 {
	        Desktop.getDesktop().browse(extentReport.toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	/*try {
	    URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

	    // Create the email message
	    ImageHtmlEmail email = new ImageHtmlEmail();

	    email.setDataSourceResolver(new DataSourceUrlResolver(url));
	    email.setHostName("smtp.googlemail.com");
	    email.setSmtpPort(465);
	    email.setAuthenticator(new DefaultAuthenticator("admin123@gmail.com", "password"));
	    email.setSSLOnConnect(true);
	    email.setFrom("admin123@gmail.com"); // Sender
	    email.setSubject("Test Results");
	    email.setMsg("Please find Attached Report....");
	    email.addTo("admin123@gmail.com"); // Receiver
	    email.attach(url, "extent report", "please check report...");
	    email.send(); // send the email
	} catch (Exception e) {
	    e.printStackTrace();
}
	}*/
}
}
