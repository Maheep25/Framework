package practise.Framework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class Login extends AbstractComp{


	WebDriver driver;

	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "input#userEmail")
	WebElement userEmail;

	@FindBy(css = "input#userPassword")
	WebElement userPassword;
	
	@FindBy(id= "login")
	WebElement login_button;
	
	@FindBy(xpath = "//div[@role='alertdialog']")
	WebElement errorNotification;
	
	@FindBy(className = "invalid-feedback")
	WebElement emptyPwd;
	
	public void loginPage(String email, String passwrd)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(passwrd);
		login_button.click();
	}
	
	public String getErrorMessage()
	{
		waitInterval(errorNotification);
		return errorNotification.getText();
		
	}
	
	public void onlyUser(String email_1)
	{
		userEmail.sendKeys(email_1);
		login_button.click();
	}
	public String emptyPassword()
	{
		return emptyPwd.getText();
	}
	public void client_url()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
}
