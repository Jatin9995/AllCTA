package screenshot;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScreenShot {
	

	public static String takeScreenshot(WebDriver driver, String testname) throws IOException {
		
		// Wait for page load using JavaScript
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
        
		TakesScreenshot src=((TakesScreenshot)driver);
		File srcshot = src.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "/Reports/Screenshot/" + testname + ".png";
		File destFile=new File(destinationPath);
		destFile.getParentFile().mkdirs();
		FileUtils.copyFile(srcshot, destFile);
		return destinationPath;

	}

}
