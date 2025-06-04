package practise.Framework;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class Address extends AbstractComp {

	WebDriver driver;
	public Address(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".form-group")
	WebElement address_view;
	
	@FindBy (css = "span.ng-star-inserted")
	List<WebElement> list_countries;
	
	public WebElement ship()
	{
		Actions action = new Actions(driver);
		action.moveToElement(address_view).click().sendKeys("Aus").build().perform();
		return address_view;
		
	}
	
	public List<WebElement> countriesList()
	{
		return list_countries;
	}
	
	public WebElement correct_country()
	{
		WebElement click_add = countriesList().stream().filter(m -> m.getText().contains("Australia")).findFirst().orElse(null);
		click_add.click();
		return click_add;
	}
	
	
	

}
