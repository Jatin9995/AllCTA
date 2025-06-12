package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyNow_HeroCart {


	WebDriver driver;

	// Constructor to initialize WebDriver and PageFactory elements
	public ApplyNow_HeroCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize PageFactory elements
	}

	@FindBy(xpath = "//div[contains(@class,'header_menuContainer__00BgW')]//span[contains(@class,'ClientSideButton_btnText__5gMgu')][normalize-space()='APPLY NOW']")
	public WebElement ApplynowBTN;

	@FindBy(xpath = "//button[@class='NationalInternational_national__FBIVx']")
	public WebElement indianBTN;

	@FindBy(xpath = "//button[@class='NationalInternational_interNational__RKrPp NationalInternational_notSelectedButton__lh0q7']")
	public WebElement internationalElement;

	@FindBy(xpath = "//div[@class='basis-1/2']//input[@placeholder='Enter your full name']")
	public WebElement nameElement;

	@FindBy(css = "div[class='mt-[0.4166666667rem] sm:mt-[0.3rem]'] div[class='CustomDropdownGlobal_countryCode__yswjf ']")
	public WebElement countrycodeElement;

	@FindBy(xpath = "//div[contains(@class,'basis-1/2')]//input[contains(@placeholder,'Enter your no.')]")
	public WebElement mobiElement;

	@FindBy(xpath = "//div[@class='basis-1/2']//input[@placeholder='abc@xyz.com']")
	public WebElement emailElement;

	@FindBy(xpath = "//form[@class='ConnectModalForm_root__zYe2H pr-[2rem]']//button[@type='submit'][normalize-space()='Submit']")
	public WebElement submitElement;

	@FindBy(xpath ="//div[@class='basis-1/2']//select[@class='appearance-none lining-nums w-full text-stone-500 bg-white text-lg leading-loose tracking-tight outline-none pr-10 false Input_selectDropdown__rRnfH']")
	public WebElement degreeElement;


	@FindBy(xpath = "//div[@class='ConnectModalForm_commonMargins__efaYq w-full']//select[@class='appearance-none lining-nums w-full text-stone-500 bg-white text-lg leading-loose tracking-tight outline-none pr-10 false Input_selectDropdown__rRnfH']")
	public WebElement prograElement;

	@FindBy(xpath = "//form[@class='ConnectModalForm_root__zYe2H pr-[2rem]']//button[@type='submit'][normalize-space()='Submit']")
	public WebElement submit2elElement;

	@FindBy(xpath = "//input[@id='otp_0']")
	public WebElement OTPCell1;

	@FindBy(xpath = "//input[@id='otp_1']")
	public WebElement OTPCell2;

	@FindBy(xpath = "//input[@id='otp_2']")
	public WebElement OTPCell3;

	@FindBy(xpath = "//input[@id='otp_3']")
	public WebElement OTPCell4;

	@FindBy(xpath = "//input[@id='otp_4']")
	public WebElement OTPCell5;

	@FindBy(xpath = "//input[@id='otp_5']")
	public WebElement OTPCell6;

	@FindBy(xpath = "//span[normalize-space()='VERIFY OTP']")
	public WebElement VerifyOTP;

	@FindBy(xpath = "//button[@id='startApplicationgta']")
	public WebElement startapplicationElement;

	@FindBy(xpath = "//div[@class='swiper-button-next']")
	public WebElement rightCarousElement;

	@FindBy(xpath = "//div[@class='banner-content']//div//span[@class='ClientSideButton_btnText__5gMgu'][normalize-space()='APPLY NOW']")
	public WebElement applynoWebElement;
	
	@FindBy(css = "path[fill='#fff'][stroke='#fff']")
	public WebElement closescholarshipopElement;


	//-------------------------------------------Handling Carousel--------------------------------------------------	


	public void clickCarouselLeft() {

		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", rightCarousElement);

	}

	public void ClickIfPresentOrSwitchThenClick(WebElement elementToClick) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			wait.until(ExpectedConditions.visibilityOf(elementToClick));
			if(elementToClick.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView();", elementToClick);
				js.executeScript("arguments[0].click();", elementToClick);

			}else {
				clickCarouselLeft();

				wait.until(ExpectedConditions.visibilityOf(elementToClick));
				if(elementToClick.isDisplayed()) {
					js.executeScript("arguments[0].scrollIntoView();", elementToClick);
					js.executeScript("arguments[0].click();", elementToClick);
				}else {
					throw new RuntimeException("Element not found on either slide");
				}
			}
		}catch (Exception e) {
			throw new RuntimeException("Failed to click element: " + e.getMessage());
		}



	}

	public void ApplyNowButton() {
		ClickIfPresentOrSwitchThenClick(applynoWebElement);
	}


	public void closescholarshippopup() {
		 try {
			 WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement popscholarship = wait2.until(ExpectedConditions.elementToBeClickable(closescholarshipopElement));
		popscholarship.click();
		System.out.println("Scholarship Popup closed.");
  } catch (Exception e) {
      System.out.println("No Scholarship popup found.");
  }
}
	
	
	// Closing popup if appears
   public void closePopupIfPresent() {
       try {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(33));
           WebElement popupCloseButton = wait.until(
                   ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='bg-white padding-0-imp Modal_callUsModalClass__gzcBP Modal_dialog__e3Pgf']//*[name()='svg']")));
           popupCloseButton.click();
           System.out.println("30 second Popup closed.");
       } catch (Exception e) {
           System.out.println("30 second No popup found.");
       }
   }
	
	
	

}
