package practise.Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class Order_OrderId extends AbstractComp {

	WebDriver driver;
	public Order_OrderId(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (className = "action__submit")
	WebElement submit_button;
	
	@FindBy (css = "label.ng-star-inserted")
	WebElement order_no;
	
	public WebElement paid()
	{
		submit_button.click();
		return submit_button;
		
	}
	
	public WebElement order_id()
	{
		System.out.println(order_no.getText().split(" ")[1].trim());
		return order_no;
		
	}

}
