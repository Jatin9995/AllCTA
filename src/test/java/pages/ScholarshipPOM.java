package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ScholarshipPOM {
	
	WebDriver driver;
	
	public ScholarshipPOM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//div[@class='basis-1/2']//input[@placeholder='Enter your full name']")
	public WebElement nameElement;

	@FindBy(css = ".appearance-none.lining-nums.false.text-stone-500.bg-white.text-lg.leading-loose.tracking-tight.outline-none.pr-10.false.Input_selectDropdown__rRnfH")
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

    @FindBy(xpath = "//div[@class='ConnectModalForm_commonMargins__efaYq w-full lg:mt-4']//select[@class='appearance-none lining-nums w-full text-stone-500 bg-white text-lg leading-loose tracking-tight outline-none pr-10 false Input_selectDropdown__rRnfH']")
    public WebElement programs_Element;

    @FindBy(xpath = "//button[normalize-space()='Start Scholarship Test']")
    public WebElement Start_Scholership_Test;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement attempt_test_element;
    
    @FindBy(css = "path[fill='#fff'][stroke='#fff']")
	public WebElement closescholarshipopElement;
    
    
    @FindBy(xpath = "//button[@class='StripData_stripContainerNew__Slybs']//span[1]")
	public WebElement scholershipyelloWebElement;

	@FindBy(xpath = "//h2[normalize-space()='Reserve Your Spot Now']")
	public WebElement Reserve_Your_Spot_Now;
	
	@FindBy(xpath = "//button[@class='StripData_stripContainerNew__Slybs']//span[1]")
	public WebElement yellowstripElement;
	


	public void YStrip() {
		JavascriptExecutor js3=((JavascriptExecutor)driver);
		js3.executeScript("arguments[0].click();", yellowstripElement);
	}
    
	

}
