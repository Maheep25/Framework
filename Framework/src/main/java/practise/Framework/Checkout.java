package practise.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class Checkout extends AbstractComp {

	WebDriver driver;
	public Checkout(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement check;
	
	
	public WebElement checkoutPage()
	{
		check.click();
		return check;
		
	}
}
