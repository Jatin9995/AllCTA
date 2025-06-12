package baseclass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Random.RandomDetails;
import io.appium.java_client.functions.ExpectedCondition;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ApplyNowPOM;
import pages.ApplyNow_HeroCart;
import pages.BCA_Cloud_Security_POM;
import pages.BcaDownloadBrochure;
import pages.BlueRFIPOM;
import pages.DownloadBrochure;
import pages.OpenFormMBA;
import pages.ScholarshipPOM;
import pages.enquireNowPOM;
import report.Extent;
import screenshot.ScreenShot;


public class base {
	
	protected	WebDriver driver;
	protected	ExtentReports reports;
	protected ExtentTest test;
	protected Extent headerExtent;
	protected RandomDetails random;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected SoftAssert softAssert;
	protected enquireNowPOM enquirNow;
	protected ApplyNowPOM applyNow;
	protected ApplyNow_HeroCart ApplyNowHeroCart;
	protected BlueRFIPOM blueRFI;
	protected OpenFormMBA openformmba;
	protected DownloadBrochure dbrochure;
	protected BCA_Cloud_Security_POM bcaspecialization;
	protected BcaDownloadBrochure bcaBrochure;
	protected ScholarshipPOM scholarship;
	
	
	@BeforeMethod
	public void LaunchBrowser() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // ✅ new headless mode (preferred)
		options.addArguments("--disable-notifications");
		options.addArguments("--window-size=1920,1080"); // ✅ sets correct resolution
		options.addArguments("--disable-gpu"); // ✅ necessary on some systems
		driver = new ChromeDriver(options);
		driver.navigate().to("https://amityonline.com/");
		Thread.sleep(2000);
		softAssert=new SoftAssert();
		js=((JavascriptExecutor)driver);
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		random=new RandomDetails(driver);
		enquirNow=new enquireNowPOM(driver);
		applyNow=new ApplyNowPOM(driver);
		blueRFI=new BlueRFIPOM(driver);
		ApplyNowHeroCart=new ApplyNow_HeroCart(driver);
		openformmba=new OpenFormMBA(driver);
		dbrochure=new DownloadBrochure(driver);
		bcaspecialization=new BCA_Cloud_Security_POM(driver);
		bcaBrochure=new BcaDownloadBrochure(driver);
		scholarship=new ScholarshipPOM(driver);
		
		
		
	


}
	
	@BeforeTest
	public void setupReport() {
		cleanScreenshotFolders();
		headerExtent=new Extent();
		reports = headerExtent.generateReport();

	}
	
	@AfterTest
	public void flushReport() {
		reports.flush();
	}
	
	public void cleanScreenshotFolders() {
		String screenshotDir = System.getProperty("user.dir") + "/Reports/Screenshot";
		deleteFilesInFolder(screenshotDir);
	}
	
	public void deleteFilesInFolder(String folderpath) {
		File folder=new File(folderpath);
		if (folder.exists() && folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				if (file.isDirectory()) {
					deleteFilesInFolder(file.getAbsolutePath()); // recursive delete


				}else {
					boolean deleted = file.delete();
					if (!deleted) {
						System.out.println("Failed to delete: " + file.getAbsolutePath());
					}
				}
			}
		}
	
	
	}
	
	@AfterMethod
	public void CloseBrowser(ITestResult result) throws IOException {
		 String testName = result.getName();
		    String screenshotPath = ScreenShot.takeScreenshot(driver, testName);

		    switch (result.getStatus()) {
		        case ITestResult.FAILURE:
		            test.addScreenCaptureFromPath(screenshotPath);
		            test.log(Status.FAIL, "❌ Test failed: " + result.getThrowable());
		            break;

		        case ITestResult.SUCCESS:
		            test.addScreenCaptureFromPath(screenshotPath);
		            test.log(Status.PASS, "✅ Test passed");
		            break;

		        case ITestResult.SKIP:
		            test.addScreenCaptureFromPath(screenshotPath);
		            test.log(Status.SKIP, "⚠️ Test skipped");
		            break;
		    }

		}
		

	
	@AfterSuite
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}
	
	public WebElement waitForElementWithTiming(Function<WebDriver, WebElement> condition, String elementName) {
	    WebElement element = null;
	    long startTime = System.currentTimeMillis(); // Start time in milliseconds
	    try {
	        element = new WebDriverWait(driver, Duration.ofSeconds(30)).until(condition); // Try to locate the element
	    } catch (Exception e) {
	        long endTime = System.currentTimeMillis(); // End time in milliseconds
	        long durationInSeconds = (endTime - startTime) / 1000; // Convert to seconds
	        test.log(Status.FAIL, "❌ Failed to load element '" + elementName + "' in " + durationInSeconds + " sec: " + e.getMessage());
	        softAssert.fail("Element '" + elementName + "' failed to load: " + e.getMessage());
	    }
	    return element;
	}

	 // ✅ Add this once here
    protected String formatSeconds(long nanos) {
        return String.format("%.2f", (double) nanos / 1_000_000_000) + " seconds";
    }
	
}
