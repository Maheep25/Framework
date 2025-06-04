package practise.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class OrderHistory extends AbstractComp{

	WebDriver driver;
	public OrderHistory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}


	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement history;
	
	@FindBy(xpath="(//tr[@class='ng-star-inserted']/th)[1]")
	WebElement verify_history;

	
	public WebElement orderDetails()
	{
		history.click();
		return history;
	}
	
	public WebElement verifyOrder()
	{
		System.out.println(verify_history.getText());
		return verify_history;
	}
}
