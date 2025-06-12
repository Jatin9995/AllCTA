package CTA.AmityOnline;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseclass.base;
import pages.ApplyNowPOM;
import pages.ApplyNow_HeroCart;
import pages.OpenFormMBA;
import pages.enquireNowPOM;

public class MainClass extends base{

	//--------------------------Enquire Now------------------------------------------------------------------	

	@Test(priority = 1)
	public void enquireNow() throws InterruptedException {
		test = reports.createTest("Enquire_Now_Indian_International (Header Section)");
		// ========== INDIAN FLOW ==========

		try {
			// Open Enquire Now form
			String originalWindow = driver.getWindowHandle();
			WebElement EnquireNowBTN = wait.until(ExpectedConditions.elementToBeClickable(
					enquirNow.enquireNowelementElement));
			EnquireNowBTN.click();
			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			enquirNow = new enquireNowPOM(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			// Select Indian flow
			wait.until(ExpectedConditions.elementToBeClickable(enquirNow.indianBTN)).click();
			// Generate test data
			String randomMobileNumber = "239" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			enquirNow.mobiElement.sendKeys(randomMobileNumber);
			enquirNow.nameElement.sendKeys(randomName);
			enquirNow.emailElement.sendKeys(randomEmail);
			enquirNow.submitElement.click();

			// Degree selection (optional step)
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(enquirNow.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(enquirNow.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				enquirNow.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			// OTP verification
			wait.until(ExpectedConditions.elementToBeClickable(enquirNow.OTPCell1)).sendKeys(random.GetOTP());
			enquirNow.OTPCell2.sendKeys(random.GetOTP());
			enquirNow.OTPCell3.sendKeys(random.GetOTP());
			enquirNow.OTPCell4.sendKeys(random.GetOTP());
			enquirNow.OTPCell5.sendKeys(random.GetOTP());
			enquirNow.OTPCell6.sendKeys(random.GetOTP());
			enquirNow.VerifyOTP.click();

			// Final verification
			wait.until(ExpectedConditions.elementToBeClickable(enquirNow.continueapplicationElement)).click();
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					enquirNow.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "Indian flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Enquire Now Indian Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Enquire Now Indian Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "Indian Flow Failed: " + e.getMessage());
			softAssert.fail("Indian Flow Failed - " + e.getMessage());
		} finally {

			// Return to main window
			driver.navigate().back();
			driver.navigate().back();
		}

		// ========== INTERNATIONAL FLOW ==========
		try {
			// Re-open Enquire Now form
			String originalWindow = driver.getWindowHandle();
			WebElement enquireNowBTN2 = wait.until(ExpectedConditions.elementToBeClickable(
					enquirNow.enquireNowelementElement));
			enquireNowBTN2.click();

			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			enquirNow = new enquireNowPOM(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Select International flow
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(enquirNow.internationalElement)).click();
			// Country code selection
			enquirNow.countrycodeElement.click();
			WebElement unitedElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='CustomDropdownGlobal_dropdownItem__Qu0Dw'][1]")));
			Actions codeActions=new Actions(driver);
			codeActions.moveToElement(unitedElement).click().perform();

			// Generate test data
			String randomMobileNumber = "23" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			enquirNow.nameElement.sendKeys(randomName);
			enquirNow.mobiElement.sendKeys(randomMobileNumber);
			enquirNow.emailElement.sendKeys(randomEmail);
			enquirNow.submitElement.click();

			// Degree selection (optional step)
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(enquirNow.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(enquirNow.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				enquirNow.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			// Final verification
			wait.until(ExpectedConditions.elementToBeClickable(enquirNow.continueapplicationElement)).click();
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					enquirNow.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "International flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Enquire Now International Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Enquire Now International Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "International Flow Failed: " + e.getMessage());
			softAssert.fail("International Flow Failed - " + e.getMessage());
		}finally {
			softAssert.assertAll();
		}


	}





	//-----------------Apply Now hero (Header)---------------------------------------------------------	

	@Test(priority = 2)
	public void ApplyNow_Header(){
		test = reports.createTest("Apply_Now_India_International (Header Section)");


		// ========== INDIAN FLOW ==========
		try {
			// Open Apply Now form from header
			String originalWindow = driver.getWindowHandle();
			WebElement ApplynowBTN = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(@class,'header_menuContainer__00BgW')]//span[contains(text(),'APPLY NOW')]")));
			ApplynowBTN.click();

			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			applyNow = new ApplyNowPOM(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Select Indian flow
			wait.until(ExpectedConditions.elementToBeClickable(applyNow.indianBTN)).click();

			// Generate and log test data
			String randomMobileNumber = "239" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			applyNow.mobiElement.sendKeys(randomMobileNumber);
			applyNow.nameElement.sendKeys(randomName);
			applyNow.emailElement.sendKeys(randomEmail);
			applyNow.submitElement.click();

			// Optional degree selection
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(applyNow.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(applyNow.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				applyNow.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			wait.until(ExpectedConditions.elementToBeClickable(applyNow.OTPCell1));
			// OTP verification
			applyNow.OTPCell1.sendKeys(random.GetOTP());
			applyNow.OTPCell2.sendKeys(random.GetOTP());
			applyNow.OTPCell3.sendKeys(random.GetOTP());
			applyNow.OTPCell4.sendKeys(random.GetOTP());
			applyNow.OTPCell5.sendKeys(random.GetOTP());
			applyNow.OTPCell6.sendKeys(random.GetOTP());
			applyNow.VerifyOTP.click();

			// Final verification
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					applyNow.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "Indian flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Apply Now Indian Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Apply Now Indian Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "Indian Flow Failed: " + e.getMessage());
			softAssert.fail("Indian Flow Failed - " + e.getMessage());
		} finally {
			// Return to main window
			driver.navigate().back();
		}

		// ========== INTERNATIONAL FLOW ==========
		try {
			// Re-open Apply Now form from header
			String originalWindow = driver.getWindowHandle();
			WebElement ApplynowBTN2 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(@class,'header_menuContainer__00BgW')]//span[contains(text(),'APPLY NOW')]")));
			ApplynowBTN2.click();

			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			applyNow = new ApplyNowPOM(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Select International flow
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(applyNow.internationalElement)).click();

			// Country code selection
			applyNow.countrycodeElement.click();
			WebElement unitedElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='CustomDropdownGlobal_dropdownItem__Qu0Dw'][1]")));
			new Actions(driver).moveToElement(unitedElement).click().perform();

			// Generate test data
			String randomMobileNumber = "23" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			applyNow.nameElement.sendKeys(randomName);
			applyNow.mobiElement.sendKeys(randomMobileNumber);
			applyNow.emailElement.sendKeys(randomEmail);
			applyNow.submitElement.click();

			// Optional degree selection
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(applyNow.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(applyNow.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				applyNow.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			// Final verification
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					applyNow.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "International flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Apply Now International Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Apply Now International Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "International Flow Failed: " + e.getMessage());
			softAssert.fail("International Flow Failed - " + e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}






	//------------------Apply Now HeroCart-----------------------------------------	

	@Test(priority = 3)
	public void ApplyNow_Herocart() {
		test = reports.createTest("ApplyNow (HeroCart Section_HomePage)");

		// ========== INDIAN FLOW ==========
		try {
			// Initialize and open Apply Now from Hero Cart
			String originalWindow = driver.getWindowHandle();
			ApplyNowHeroCart.closescholarshippopup();
			ApplyNowHeroCart.closePopupIfPresent();
			ApplyNowHeroCart.ApplyNowButton();

			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			ApplyNowHeroCart = new ApplyNow_HeroCart(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Select Indian flow
			wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.indianBTN)).click();

			// Generate and log test data
			String randomMobileNumber = "239" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			ApplyNowHeroCart.mobiElement.sendKeys(randomMobileNumber);
			ApplyNowHeroCart.nameElement.sendKeys(randomName);
			ApplyNowHeroCart.emailElement.sendKeys(randomEmail);
			ApplyNowHeroCart.submitElement.click();

			// Optional degree selection
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				ApplyNowHeroCart.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			// OTP verification
			wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.OTPCell1));
			ApplyNowHeroCart.OTPCell1.sendKeys(random.GetOTP());
			ApplyNowHeroCart.OTPCell2.sendKeys(random.GetOTP());
			ApplyNowHeroCart.OTPCell3.sendKeys(random.GetOTP());
			ApplyNowHeroCart.OTPCell4.sendKeys(random.GetOTP());
			ApplyNowHeroCart.OTPCell5.sendKeys(random.GetOTP());
			ApplyNowHeroCart.OTPCell6.sendKeys(random.GetOTP());
			ApplyNowHeroCart.VerifyOTP.click();

			// Final verification
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					ApplyNowHeroCart.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "Indian flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Apply Now-HeroCart Indian Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Apply Now-HeroCart Indian Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "Indian Flow Failed: " + e.getMessage());
			softAssert.fail("Indian Flow Failed - " + e.getMessage());
		} finally {
			// Return to main window
			driver.navigate().back();
		}

		// ========== INTERNATIONAL FLOW ==========
		try {
			// Re-open Apply Now from Hero Cart
			String originalWindow = driver.getWindowHandle();
			ApplyNowHeroCart.ApplyNowButton();

			// Switch to new window
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}

			// Initialize POM in new window
			ApplyNowHeroCart = new ApplyNow_HeroCart(driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// Select International flow
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.internationalElement)).click();

			// Country code selection
			ApplyNowHeroCart.countrycodeElement.click();
			WebElement unitedElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='CustomDropdownGlobal_dropdownItem__Qu0Dw'][1]")));
			Actions act=new Actions(driver);
			act.moveToElement(unitedElement).click().perform();


			// Generate test data
			String randomMobileNumber = "23" + random.getRandomMobileNumber();
			String randomName = "TestQA" + " " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + randomName);
			test.info("Random Email: " + randomEmail);
			test.info("Random Mobile Number: " + randomMobileNumber);

			// Fill form
			ApplyNowHeroCart.nameElement.sendKeys(randomName);
			ApplyNowHeroCart.mobiElement.sendKeys(randomMobileNumber);
			ApplyNowHeroCart.emailElement.sendKeys(randomEmail);
			ApplyNowHeroCart.submitElement.click();

			// Optional degree selection
			try {
				String handle = driver.getWindowHandle();
				driver.switchTo().window(handle);
				wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.degreeElement))
				.sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(ApplyNowHeroCart.prograElement))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");
				ApplyNowHeroCart.submit2elElement.click();
				test.info("Degree and Program selection completed successfully.");
			} catch (Exception e) {
				test.warning("Degree/Program selection step skipped: " + e.getMessage());
			}

			// Final verification
			boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
					ApplyNowHeroCart.startapplicationElement)).isEnabled();

			softAssert.assertTrue(isSuccess, "International flow - Start Application button enabled check");
			test.log(isSuccess ? Status.PASS : Status.FAIL, 
					"Apply Now-HeroCart International Journey " + (isSuccess ? "Successful" : "Failed"));
			System.out.println("Apply Now-HeroCart International Journey Successful");

		} catch (Exception e) {
			test.log(Status.FAIL, "International Flow Failed: " + e.getMessage());
			softAssert.fail("International Flow Failed - " + e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}



	//--------------Are you ready to take the next step in your career ?----------Blue RFI----------------------------------

	@Test(priority = 4)
	public void BlueRFI() {
		test = reports.createTest("Blue RFI Indian & International (HomePage)");

		try {
			// Close popups and initialize
			ApplyNowHeroCart.closescholarshippopup();
			ApplyNowHeroCart.closePopupIfPresent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// ========== INDIAN FLOW ==========
			try {
				// Scroll to element and wait for visibility
				WebElement applyNowText = wait.until(ExpectedConditions.visibilityOf(blueRFI.applynowtextElement));
				js.executeScript("arguments[0].scrollIntoView();", applyNowText);
				

				// Generate test data
				String randomName = "TestQA" + " " + random.GetRandomName();
				String randomMobileNumber = "239" + random.getRandomMobileNumber();
				String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

				test.info("Random Name: " + randomName);
				test.info("Random Email: " + randomEmail);
				test.info("Random Mobile Number: " + randomMobileNumber);

				// Fill form
				blueRFI.fullnamElement.sendKeys(randomName);
				blueRFI.phonenumberElement.sendKeys(randomMobileNumber);
				blueRFI.emailidElement.sendKeys(randomEmail);

				// Submit form
				WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(blueRFI.submitbuttonElement));
				js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", submitBtn);

				// Optional degree selection
				try {
					String handle = driver.getWindowHandle();
					driver.switchTo().window(handle);

					wait.until(ExpectedConditions.elementToBeClickable(blueRFI.degreeElement))
					.sendKeys("PG");
					wait.until(ExpectedConditions.elementToBeClickable(blueRFI.programElement))
					.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");

					WebElement submitBtn2 = wait.until(ExpectedConditions.elementToBeClickable(blueRFI.submittbutton2));
					js.executeScript("arguments[0].click();", submitBtn2);

					test.info("Degree and Program selection completed successfully.");
				} catch (Exception e) {
					test.warning("Degree/Program selection step skipped: " + e.getMessage());
				}
				
			
				
				wait.until(ExpectedConditions.elementToBeClickable(blueRFI.OTPCell1));
				// OTP verification
				blueRFI.OTPCell1.sendKeys(random.GetOTP());
				blueRFI.OTPCell2.sendKeys(random.GetOTP());
				blueRFI.OTPCell3.sendKeys(random.GetOTP());
				blueRFI.OTPCell4.sendKeys(random.GetOTP());
				blueRFI.OTPCell5.sendKeys(random.GetOTP());
				blueRFI.OTPCell6.sendKeys(random.GetOTP());
				blueRFI.VerifyOTP.click();

				// Final verification
				boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
						blueRFI.startapplicationElement)).isEnabled();

				softAssert.assertTrue(isSuccess, "Indian flow - Start Application button enabled check");
				test.log(isSuccess ? Status.PASS : Status.FAIL, 
						"Blue RFI Indian Journey " + (isSuccess ? "Successful" : "Failed"));
				System.out.println("Blue RFI Indian Journey Successful");

			} catch (Exception e) {
				test.log(Status.FAIL, "Indian Flow Failed: " + e.getMessage());
				softAssert.fail("Indian Flow Failed - " + e.getMessage());
			} finally {
				// Return to previous page
				driver.navigate().back();
			}

			// ========== INTER	NATIONAL FLOW ==========
			try {
				// Re-initialize and scroll to element
				WebElement applyNowTextInternational = wait.until(ExpectedConditions
					    .visibilityOfElementLocated(By.xpath("//div[@class='LeadForm_formSection__TxeSn']//h2[contains(text(),'Apply Now')]")));
					js.executeScript("arguments[0].scrollIntoView();", applyNowTextInternational);

				// Generate test data
				String randomName = "TestQA" + " " + random.GetRandomName();
				String randomMobileNumber = "239" + random.getRandomMobileNumber2();
				String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

				test.info("Random Name: " + randomName);
				test.info("Random Email: " + randomEmail);
				test.info("Random Mobile Number: " + randomMobileNumber);

				// Fill form
				blueRFI.fullnamElement.sendKeys(randomName);
				blueRFI.countrycodElement.click();
				new Actions(driver).moveToElement(blueRFI.internationalunitedstatElement).click().perform();
				blueRFI.phonenumberElement.sendKeys(randomMobileNumber);
				blueRFI.emailidElement.sendKeys(randomEmail);

				// Submit form
				WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(blueRFI.submitbuttonElement));
				js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", submitBtn);

				// Optional degree selection
				try {
					String handle = driver.getWindowHandle();
					driver.switchTo().window(handle);

					wait.until(ExpectedConditions.elementToBeClickable(blueRFI.degreeElement))
					.sendKeys("PG");
					wait.until(ExpectedConditions.elementToBeClickable(blueRFI.programElement))
					.sendKeys("MASTER OF COMPUTER APPLICATIONS WITH SPECIALIZATION IN CYBER SECURITY");

					WebElement submitBtn2 = wait.until(ExpectedConditions.elementToBeClickable(blueRFI.submittbutton2));
					js.executeScript("arguments[0].click();", submitBtn2);

					test.info("Degree and Program selection completed successfully.");
				} catch (Exception e) {
					test.warning("Degree/Program selection step skipped: " + e.getMessage());
				}

				// Final verification
				boolean isSuccess = wait.until(ExpectedConditions.elementToBeClickable(
						blueRFI.startapplicationElement)).isEnabled();

				softAssert.assertTrue(isSuccess, "International flow - Start Application button enabled check");
				test.log(isSuccess ? Status.PASS : Status.FAIL, 
						"Blue RFI International Journey " + (isSuccess ? "Successful" : "Failed"));
				System.out.println("Blue RFI International Journey Successful");

			} catch (Exception e) {
				test.log(Status.FAIL, "International Flow Failed: " + e.getMessage());
				softAssert.fail("International Flow Failed - " + e.getMessage());
			}

		} catch (Exception e) {
			test.log(Status.FAIL, "Blue RFI Test Failed: " + e.getMessage());
			softAssert.fail("Blue RFI Test Failed - " + e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}


	//-------------------MBA Homepage open form------------------------------------

	@Test(priority = 5)
	public void Open_form_MBA_India_International() throws InterruptedException {
		test = reports.createTest("Open_form_MBA_India & International (PG Programs)");
		driver.navigate().to("https://amityonline.com/master-of-business-administration-online");
	Thread.sleep(2000);
		wait=new WebDriverWait(driver, Duration.ofSeconds(25));

		// ========== INDIAN FLOW ==========
		try {

			String mbaName = "TestQA" + " " + random.GetRandomName();
			String mbaMobileNumber = "239" + random.getRandomMobileNumber();
			String mbaEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + mbaName);
			test.info("Random Mobile Number: " + mbaMobileNumber);
			test.info("Random Email: " + mbaEmail);

			openformmba.name.sendKeys(mbaName);
			//openformmba.selectdropdown();
			openformmba.mobile.sendKeys(mbaMobileNumber);
			openformmba.email.sendKeys(mbaEmail);
			openformmba.clicksubmit();

			Thread.sleep(6000);
            wait.until(ExpectedConditions.elementToBeClickable(openformmba.OTPCell1));
			openformmba.OTPCell1.sendKeys(random.GetOTP());
			openformmba.OTPCell2.sendKeys(random.GetOTP());
			openformmba.OTPCell3.sendKeys(random.GetOTP());
			openformmba.OTPCell4.sendKeys(random.GetOTP());
			openformmba.OTPCell5.sendKeys(random.GetOTP());
			openformmba.OTPCell6.sendKeys(random.GetOTP());

			openformmba.VerifyOTP.click();

			wait.until(ExpectedConditions.visibilityOf(openformmba.startapplicationElement));
			if (openformmba.startapplicationElement.isEnabled()) {

				test.log(Status.PASS, "MBA Open form for India is submitted successfully" );
				softAssert.assertTrue(true, "MBA Open form is submitted successfully");
				System.out.println("MBA Open form is submitted successfully");


			}else {
				test.log(Status.FAIL, "MBA Open form is failed");
				softAssert.fail("MBA Open form is failed");
			}

		}catch (Exception e) {
			test.log(Status.FAIL, "MBA Open form is failed" + e.getMessage());
			softAssert.fail("MBA Open form is failed");
			System.out.println("MBA Open form is failed");



		} 
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);

		//---------------MBA open form for international----------------------------

		try {

			String mbaName = "TestQA" + " " + random.GetRandomName();
			String mbaMobileNumber = "23" + random.getRandomMobileNumber();
			String mbaEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + mbaName);
			test.info("Random Mobile Number: " + mbaMobileNumber);
			test.info("Random Email: " + mbaEmail);

			openformmba.name.sendKeys(mbaName);
			driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_countryCode__yswjf ']")).click();
			Actions codeActions1=new Actions(driver);
			WebElement unitedstate =driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_dropdownMenu__cyPE8']//div[2]//span[1]"));
			codeActions1.moveToElement(unitedstate).click().perform();
			openformmba.mobile.sendKeys(mbaMobileNumber);
			openformmba.email.sendKeys(mbaEmail);
			openformmba.clicksubmit();


			wait.until(ExpectedConditions.visibilityOf(openformmba.startapplicationElement));
			if (openformmba.startapplicationElement.isEnabled()) {

				test.log(Status.PASS, "MBA Open form for international is submitted successfully" );
				softAssert.assertTrue(true, "MBA Open form for international is submitted successfully");
				System.out.println("MBA Open form for international is submitted successfully");

			}else {
				test.log(Status.FAIL, "MBA Open form for international is failed");
				softAssert.fail("MBA Open form for international is failed");
			}

		}catch (Exception e) {
			test.log(Status.FAIL, "MBA Open form for international is failed" + e.getMessage());
			softAssert.fail("MBA Open form for international is failed" + e.getMessage());
			System.out.println("MBA Open form for international is failed");



		} 

		softAssert.assertAll();


	
	}




	//---------------------------Download Brochure--------------------------------------------------------------

	@Test(priority = 6)
	public void Download_Brochure_India_International() {
		test = reports.createTest("Download_Brochure_India & International (PG Programs)");
		driver.navigate().to("https://amityonline.com/master-of-business-administration-online");
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// ========== DOWNLOAD BROCHURE - INDIA ==========
		try {
			dbrochure.clickbrochure();
			String handle4 = driver.getWindowHandle();
			driver.switchTo().window(handle4);

			// Generate test data
			String dname = "TestQA " + random.GetRandomName();
			String dmobilenumber = "239" + random.getRandomMobileNumber();
			String demailid = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + dname);
			test.info("Random Mobile Number: " + dmobilenumber);
			test.info("Random Email: " + demailid);

			// Fill and submit form
			dbrochure.contactnumberElement.sendKeys(dmobilenumber);
			dbrochure.usernamElement.sendKeys(dname);
			dbrochure.useremailElement.sendKeys(demailid);
			dbrochure.usersubmitbuttonElement.click();

			// OTP Entry
			wait.until(ExpectedConditions.elementToBeClickable(dbrochure.OTPCell1));
			dbrochure.OTPCell1.sendKeys(random.GetOTP());
			dbrochure.OTPCell2.sendKeys(random.GetOTP());
			dbrochure.OTPCell3.sendKeys(random.GetOTP());
			dbrochure.OTPCell4.sendKeys(random.GetOTP());
			dbrochure.OTPCell5.sendKeys(random.GetOTP());
			dbrochure.OTPCell6.sendKeys(random.GetOTP());

			wait.until(ExpectedConditions.elementToBeClickable(dbrochure.VerifyOTP)).click();

			// Proceed & validate
			wait.until(ExpectedConditions.elementToBeClickable(dbrochure.proceedbutton)).click();

		
			//dbrochure.switchwindow();
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();

			while (iterator.hasNext()) {
				String currentHandle = iterator.next();
				if (!currentHandle.equals(handle4)) {
					driver.switchTo().window(currentHandle);
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(dbrochure.startapplicationElement));

			boolean isIndiaSuccess = dbrochure.startapplicationElement.isEnabled();
			softAssert.assertTrue(isIndiaSuccess, "India Brochure Start Application check");
			test.log(isIndiaSuccess ? Status.PASS : Status.FAIL,
					"Download Brochure for India " + (isIndiaSuccess ? "is working" : "is not working"));
			System.out.println("Download Brochure for India is working (PG Programs)");

			driver.close(); // close child tab
			driver.switchTo().window(handle4);

			// Close pop-up
			WebElement closePopup = driver.findElement(By.xpath("//div[@class='bg-white Modal_dialog__e3Pgf']//*[name()='svg']"));
			wait.until(ExpectedConditions.elementToBeClickable(closePopup)).click();

		} catch (Exception e) {
			test.log(Status.FAIL, "Download Brochure for India failed: " + e.getMessage());
			softAssert.fail("Download Brochure for India failed: " + e.getMessage());
			System.out.println("Download Brochure for India is not working (PG Programs)");
		}

		// ========== DOWNLOAD BROCHURE - INTERNATIONAL ==========
		try {
			dbrochure.clickbrochure();

			String handle5 = driver.getWindowHandle();
			driver.switchTo().window(handle5);

			String dname = "TestQA " + random.GetRandomName();
			String dmobilenumber = "23" + random.getRandomMobileNumber();
			String demailid = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + dname);
			test.info("Random Mobile Number: " + dmobilenumber);
			test.info("Random Email: " + demailid);

			// Select international code
			WebElement codeDropdown = driver.findElement(By.xpath("//div[@class='flex gap-2']//div//div[contains(@class,'CustomDropdownGlobal_countryCode__yswjf')][normalize-space()='IN +91']"));
			wait.until(ExpectedConditions.elementToBeClickable(codeDropdown)).click();
			WebElement unitedStates = driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_dropdownMenu__cyPE8']//div[2]//span[1]"));
			new Actions(driver).moveToElement(unitedStates).click().perform();

			// Fill and submit
			dbrochure.contactnumberElement.sendKeys(dmobilenumber);
			dbrochure.usernamElement.sendKeys(dname);
			dbrochure.useremailElement.sendKeys(demailid);
			dbrochure.usersubmitbuttonElement.click();

			wait.until(ExpectedConditions.elementToBeClickable(dbrochure.proceedbutton)).click();
			dbrochure.switchwindow();

			WebElement isInternationalSuccess = wait.until(ExpectedConditions.visibilityOf(dbrochure.startapplicationElement));
			if(isInternationalSuccess.isDisplayed()) {
				softAssert.assertTrue(true, "International Brochure Start Application check");
				test.log(Status.PASS, "Download Brochure for International is working (PG Programs");
				System.out.println("Download Brochure for International is working (PG Programs)");

			}
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Download Brochure for International failed: " + e.getMessage());
			softAssert.fail("Download Brochure for International failed: " + e.getMessage());
			System.out.println("Download Brochure for International is not working (PG Programs)");
		}
			softAssert.assertAll();
		
	}

//-------------BCA-cloud-security-online----------------------------

	@Test(priority = 7)
	public void BCA_specialization() {
		test = reports.createTest("BCA-cloud-security-online (UG Specialization)");

		try {
			driver.navigate().to("https://amityonline.com/bca-cloud-security-online");
			Thread.sleep(2000);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// ========== INDIAN FLOW ==========
			try {
				String bcaName = "TestQA " + random.GetRandomName();
				String bcaMobile = "239" + random.getRandomMobileNumber();
				String bcaEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

				test.info("Indian Flow -> Name: " + bcaName);
				test.info("Indian Flow -> Mobile: " + bcaMobile);
				test.info("Indian Flow -> Email: " + bcaEmail);
                wait.until(ExpectedConditions.elementToBeClickable(bcaspecialization.name));
				bcaspecialization.name.sendKeys(bcaName);
				bcaspecialization.mobile.sendKeys(bcaMobile);
				bcaspecialization.email.sendKeys(bcaEmail);
				bcaspecialization.clicksubmit();

				wait.until(ExpectedConditions.elementToBeClickable(bcaspecialization.OTPCell1));
				bcaspecialization.OTPCell1.sendKeys(random.GetOTP());
				bcaspecialization.OTPCell2.sendKeys(random.GetOTP());
				bcaspecialization.OTPCell3.sendKeys(random.GetOTP());
				bcaspecialization.OTPCell4.sendKeys(random.GetOTP());
				bcaspecialization.OTPCell5.sendKeys(random.GetOTP());
				bcaspecialization.OTPCell6.sendKeys(random.GetOTP());
				wait.until(ExpectedConditions.elementToBeClickable(bcaspecialization.VerifyOTP)).click();

				WebElement isIndianSuccess = wait.until(ExpectedConditions.visibilityOf(
						bcaspecialization.startapplicationElement));
				if(isIndianSuccess.isDisplayed()) {
					softAssert.assertTrue(true, "Indian flow - Start Application button enabled check");
					test.log(Status.PASS, "Indian flow - Start Application button enabled check");
							
					System.out.println("BCA Specialization Indian Flow Successful");
				}else {
					softAssert.assertTrue(false, "BCA Specialization Indian Flow failed");
					test.log(Status.PASS, "Indian flow - Start Application button enabled check");
				}
			} catch (Exception e) {
				test.log(Status.FAIL, "BCA Specialization Indian Flow Failed: " + e.getMessage());
				softAssert.fail("BCA Specialization Indian Flow Failed - " + e.getMessage());
				System.out.println("BCA Specialization Indian Flow not Successful");
			} 
				driver.navigate().back();
			

			Thread.sleep(2000);
			// ========== INTERNATIONAL FLOW ==========
			try {
				

				String bcaName = "TestQA " + random.GetRandomName();
				String bcaMobile = "23" + random.getRandomMobileNumber();
				String bcaEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

				test.info("International Flow -> Name: " + bcaName);
				test.info("International Flow -> Mobile: " + bcaMobile);
				test.info("International Flow -> Email: " + bcaEmail);
                wait.until(ExpectedConditions.elementToBeClickable(bcaspecialization.name));
				bcaspecialization.name.sendKeys(bcaName);
				driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_countryCode__yswjf ']")).click();
				Actions actions= new Actions(driver);
				WebElement element = driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_dropdownMenu__cyPE8']//div[2]//span[1]"));
				actions.moveToElement(element).click().perform();

				bcaspecialization.mobile.sendKeys(bcaMobile);
				bcaspecialization.email.sendKeys(bcaEmail);
				bcaspecialization.clicksubmit();

				 WebElement isInternationalSuccess = wait.until(ExpectedConditions.elementToBeClickable(
						bcaspecialization.startapplicationElement));
				 if(isInternationalSuccess.isDisplayed()) {
					 softAssert.assertTrue(true, "International flow - Start Application button enabled check");
						test.log(Status.PASS, "International flow - Start Application button enabled check");
						System.out.println("BCA Specialization International Flow Successful");
				 }

				

			} catch (Exception e) {
				test.log(Status.FAIL, "BCA International Flow Failed: " + e.getMessage());
				softAssert.fail("BCA International Flow Failed - " + e.getMessage());
				System.out.println("BCA Specialization International Flow not Successful");
			}

		} catch (Exception e) {
			test.log(Status.FAIL, "Test Setup Failed: " + e.getMessage());
			softAssert.fail("Test Setup Failed - " + e.getMessage());
		}
			softAssert.assertAll();
		


	}




//----------bca-cloud-security-online_Specialization_Download Brochure----------------------------------------------		


	@Test(priority = 8)
	public void BcaDownloadBrochure() throws InterruptedException {
		test = reports.createTest("Download_Brochure_India_International (UG Specialization)");
		driver.navigate().to("https://amityonline.com/bca-cloud-security-online");
		Thread.sleep(2000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		// ========== INDIAN FLOW ==========

		try {
			WebElement downloadBrochureBtn = wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.downloadbrochurElement));
			downloadBrochureBtn.click();
			Thread.sleep(2000);
			String mainWindow = driver.getWindowHandle();
			driver.switchTo().window(mainWindow);

			String dname = "TestQA " + random.GetRandomName();
			String dmobilenumber = "239" + random.getRandomMobileNumber();
			String demailid = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + dname);
			test.info("Random Mobile Number: " + dmobilenumber);
			test.info("Random Email: " + demailid);
			wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.contactnumberElement));
			bcaBrochure.contactnumberElement.sendKeys(dmobilenumber);
			bcaBrochure.usernamElement.sendKeys(dname);
			bcaBrochure.useremailElement.sendKeys(demailid);
			bcaBrochure.usersubmitbuttonElement.click();

			wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.OTPCell1));
			bcaBrochure.OTPCell1.sendKeys(random.GetOTP());
			bcaBrochure.OTPCell2.sendKeys(random.GetOTP());
			bcaBrochure.OTPCell3.sendKeys(random.GetOTP());
			bcaBrochure.OTPCell4.sendKeys(random.GetOTP());
			bcaBrochure.OTPCell5.sendKeys(random.GetOTP());
			bcaBrochure.OTPCell6.sendKeys(random.GetOTP());

			bcaBrochure.VerifyOTP.click();

			wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.proceedbutton)).click();

			//dbrochure.switchwindow();
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();

			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(mainWindow)){
					driver.switchTo().window(windowHandle);
					break;
				}
			}


			WebElement indianSuccess = wait.until(ExpectedConditions.visibilityOf(bcaBrochure.startapplicationElement));
if(indianSuccess.isEnabled()) {
	softAssert.assertTrue(true, "Indian Brochure flow");
	test.log(Status.PASS, "BCA Specialization Download Brochure for India is working");
	System.out.println("BCA Specialization Download Brochure for India is working");
}
			

			// Close child windows and return to parent
			driver.close();
			driver.switchTo().window(mainWindow);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='bg-white Modal_dialog__e3Pgf']//*[name()='svg']"))).click();

		} catch (Exception e) {
			test.log(Status.FAIL, "BCA Specialization Download Brochure for India failed: " + e.getMessage());
			softAssert.fail("India flow failed - " + e.getMessage());
			System.out.println("BCA Specialization Download Brochure for India is not working");
		}

		// ========== INTERNATIONAL FLOW ==========
		
		Thread.sleep(2000);
		try {
			WebElement downloadBrochureBtn = wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.downloadbrochurElement));
			downloadBrochureBtn.click();
			Thread.sleep(2000);
			String mainWindow = driver.getWindowHandle();
			driver.switchTo().window(mainWindow);

			String dname = "TestQA " + random.GetRandomName();
			String dmobilenumber = "23" + random.getRandomMobileNumber();
			String demailid = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Random Name: " + dname);
			test.info("Random Mobile Number: " + dmobilenumber);
			test.info("Random Email: " + demailid);

			// Switch country code
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='flex gap-2']//div[contains(@class,'CustomDropdownGlobal_countryCode__yswjf')][normalize-space()='IN +91']")))
			.click();

			WebElement unitedStates = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[@class='CustomDropdownGlobal_dropdownMenu__cyPE8']//div[2]//span[1]")));
			Actions codeActions2=new Actions(driver);
			WebElement unitedstate =driver.findElement(By.xpath("//div[@class='CustomDropdownGlobal_dropdownMenu__cyPE8']//div[2]//span[1]"));
			codeActions2.moveToElement(unitedstate).click().perform();

			bcaBrochure.contactnumberElement.sendKeys(dmobilenumber);
			bcaBrochure.usernamElement.sendKeys(dname);
			bcaBrochure.useremailElement.sendKeys(demailid);
			bcaBrochure.usersubmitbuttonElement.click();

			wait.until(ExpectedConditions.elementToBeClickable(bcaBrochure.proceedbutton)).click();

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();

			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(mainWindow)){
					driver.switchTo().window(windowHandle);
					break;
				}
			}


			WebElement intlSuccess = wait.until(ExpectedConditions.visibilityOf(bcaBrochure.startapplicationElement));
if(intlSuccess.isDisplayed()) {
	softAssert.assertTrue(true, "International Brochure flow");
	test.log(Status.PASS, "BCA Specialization Download Brochure for International is working");
	System.out.println("BCA Specialization Download Brochure for International is working");
}
			

		} catch (Exception e) {
			test.log(Status.FAIL, "BCA Specialization Download Brochure for International failed: " + e.getMessage());
			softAssert.fail("International flow failed - " + e.getMessage());
			System.out.println("BCA Specialization Download Brochure for International is not working");
		}
			softAssert.assertAll();
		


	}



	//-----------------------Scholarship------------------------------
	@Test(priority = 9)
	public void TakeInstantScholarshipTest() {
		test = reports.createTest("Scholarship Test");
		ApplyNowHeroCart.closescholarshippopup();
		try {
			wait.until(ExpectedConditions.visibilityOf(scholarship.scholershipyelloWebElement));
			js.executeScript("arguments[0].scrollIntoView();", scholarship.scholershipyelloWebElement);
			js.executeScript("arguments[0].click();", scholarship.scholershipyelloWebElement);
			test.log(Status.PASS, "Clicked on July'25 Session - Take Instant Scholarship Test");

			Thread.sleep(3000);

			String currentHandle = driver.getWindowHandle();
			driver.switchTo().window(currentHandle);

			if (scholarship.Reserve_Your_Spot_Now.getText().equalsIgnoreCase("Reserve Your Spot Now")) {
				test.log(Status.PASS, "Yellow Strip is Working");
			} else {
				test.log(Status.FAIL, "Yellow Strip text not matched");
				softAssert.fail("Text mismatch in Yellow Strip");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, "Yellow Strip is not Working: " + e.getMessage());
			softAssert.fail("Yellow Strip failed: " + e.getMessage());
		}

		// Fill the Apply Now Form
		//test = reports.createTest("Reserve Your Spot Now Form Submission");
		try {
			String randomMobileNumber = "239" + random.getRandomMobileNumber();
			String randomName = "TestQA " + random.GetRandomName();
			String randomEmail = "TestQA_" + random.GetRamdonEmailID() + "@gmail.com";

			test.info("Name: " + randomName);
			test.info("Email: " + randomEmail);
			test.info("Mobile: " + randomMobileNumber);

			scholarship.mobiElement.sendKeys(randomMobileNumber);
			scholarship.nameElement.sendKeys(randomName);
			scholarship.emailElement.sendKeys(randomEmail);
			scholarship.submitElement.click();
			Thread.sleep(3000);

			try {
				wait.until(ExpectedConditions.elementToBeClickable(scholarship.degreeElement)).sendKeys("PG");
				wait.until(ExpectedConditions.elementToBeClickable(scholarship.programs_Element))
				.sendKeys("MASTER OF COMPUTER APPLICATIONS");
				scholarship.submit2elElement.click();
				Thread.sleep(6000);
				test.info("Degree and Program selection completed.");
			} catch (Exception inner) {
				test.warning("Degree/Program selection skipped: " + inner.getMessage());
			}

			// Fill OTP
			wait.until(ExpectedConditions.elementToBeClickable(scholarship.OTPCell1));
			scholarship.OTPCell1.sendKeys(random.GetOTP());
			scholarship.OTPCell2.sendKeys(random.GetOTP());
			scholarship.OTPCell3.sendKeys(random.GetOTP());
			scholarship.OTPCell4.sendKeys(random.GetOTP());
			scholarship.OTPCell5.sendKeys(random.GetOTP());
			scholarship.OTPCell6.sendKeys(random.GetOTP());

			scholarship.VerifyOTP.click();
			Thread.sleep(7000);

			if (scholarship.Start_Scholership_Test.isEnabled()) {
				test.log(Status.PASS, "Reserve Your Spot Now Form Submission completed successfully.");
				softAssert.assertTrue(true, "Reserve Your Spot Now Form Submission Journey is Successful");
			} else {
				test.log(Status.FAIL, "Reserve Your Spot Now Form Submission is Failed.");
				softAssert.fail("Reserve Your Spot Now Form Submission is Failed");
			}

		} catch (Exception e) {
			test.log(Status.FAIL, "July'25 Session - Take Instant Scholarship Test failed: " + e.getMessage());
			softAssert.fail("July'25 Session - Take Instant Scholarship Test Failed: " + e.getMessage());
		}

		//test = reports.createTest("Please Read the Instructions Carefully Scholership Page");
		try{
			wait.until(ExpectedConditions.elementToBeClickable(scholarship.Start_Scholership_Test));
			js.executeScript("arguments[0].scrollIntoView();", scholarship.Start_Scholership_Test);
			js.executeScript("arguments[0].click();", scholarship.Start_Scholership_Test);

			wait.until(ExpectedConditions.elementToBeClickable(scholarship.attempt_test_element));
			js.executeScript("arguments[0].click()", scholarship.attempt_test_element);

		}catch (Exception e) {
			test.log(Status.FAIL, "July'25 Session - Take Instant Scholarship Test failed: " + e.getMessage());
			softAssert.fail("July'25 Session - Take Instant Scholarship Test Failed: " + e.getMessage());
		}


		// Only the suffixes (answers) are hard coded
		List<String> answerSuffixes = Arrays.asList(
				"1_answer0","2_answer1","3_answer1","4_answer2","5_answer1","6_answer0","7_answer3","8_answer2","9_answer1","10_answer2","11_answer3","12_answer0",
				"13_answer1",
				"14_answer2",
				"15_answer0"
				);

		try {
			// Step 1: Get dynamic prefix (e.g., q3198)
			WebElement firstAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[contains(@id,'_answer')]")
					));
			String fullId = firstAnswer.getAttribute("id");  // e.g., q3198:1_answer0
			String dynamicPrefix = fullId.split(":")[0];
			System.out.println("Dynamic question prefix: " + dynamicPrefix);

			// Step 2: Locate all 15 question containers
			List<WebElement> questionDivs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//div[contains(@class,'que multichoice')]")
					));

			// Step 3: Answer each question one by one
			for (int i = 0; i < questionDivs.size() && i < answerSuffixes.size(); i++) {
				try {
					WebElement questionDiv = questionDivs.get(i);
					String answerId = dynamicPrefix + ":" + answerSuffixes.get(i);

					WebElement answerOption = questionDiv.findElement(By.xpath(".//input[@id='" + answerId + "']"));

					// Scroll and click
					js.executeScript("arguments[0].scrollIntoView(true);", answerOption);
					js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", answerOption);
					Thread.sleep(800);
					js.executeScript("arguments[0].click();", answerOption);
					// answerOption.click();
					test.log(Status.PASS, "Scholership form ✅ Answered Q" + (i + 1) + " using ID:"  + answerId );
					softAssert.assertTrue(true, "Scholership form ✅ Answered Q" + (i + 1) + " using ID:"  + answerId );
					Thread.sleep(3000);

				} catch (Exception e) {
					test.log(Status.FAIL, "❌ Failed to answer Q" + (i + 1) + ": " + e.getMessage());
					softAssert.fail("❌ Failed to answer Q" + (i + 1) + ": " + e.getMessage());
				}
			}

			// Step 1: Click Submit if found finshed button
			try {
				Thread.sleep(2000);
				WebElement submitBtn = driver.findElement(By.xpath("//input[@id='mod_quiz-next-nav']"));
				js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
				submitBtn.click();
				test.log(Status.PASS, "Finished Test Button completed successfully.");
				softAssert.assertTrue(true, "Finished Test Button completed successfully.");
			} catch (Exception e) {
				test.log(Status.FAIL, "Finished Test Button Test Failed: " + e.getMessage());
				softAssert.fail("Finished Test Button Test Failed: " + e.getMessage());
			}

			// Step 2: Click Submit for fnished
			try {
				Thread.sleep(2000);
				WebElement submitBtn2 = driver.findElement(By.xpath("//button[normalize-space()='Submit']"));
				js.executeScript(
						"arguments[0].style.scrollMarginTop='100px'; arguments[0].scrollIntoView({behavior:'instant', block:'end'});", submitBtn2);
				submitBtn2.click();

				test.log(Status.PASS, "Submit Test Button completed successfully.");
				softAssert.assertTrue(true, "Submit Test Button completed successfully.");
			} catch (Exception e) {
				test.log(Status.FAIL, "Submit Test Button Test Failed: " + e.getMessage());
				softAssert.fail("Submit Test Button Test Failed: " + e.getMessage());
			}

			// Step 3: Click Submit for fnished popup final
			try {
				Thread.sleep(2000);
				WebElement submitBtn3 = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Submit']"));
				js.executeScript("arguments[0].scrollIntoView(true);", submitBtn3);
				submitBtn3.click();
				test.log(Status.PASS, "✅ Submitted Popup Button completed successfully.");
				softAssert.assertTrue(true, "✅ Submitted Popup Button completed successfully.");
			} catch (Exception e) {
				test.log(Status.FAIL, "⚠️ Submit button pop up not found: " + e.getMessage());
				softAssert.fail("⚠️ Submit button pop up not found: " + e.getMessage());
				Thread.sleep(4000);
			}

		} catch (Exception e) {
			System.out.println("⚠️ Error during automation: " + e.getMessage());
		}finally {
			softAssert.assertAll();
		}

	}




}




