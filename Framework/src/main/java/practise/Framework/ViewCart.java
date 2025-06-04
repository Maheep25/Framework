package practise.Framework;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class ViewCart extends AbstractComp {

	WebDriver driver;
	public ViewCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy (css = "div.cartSection h3")
	List<WebElement> verify_cart;
	
	public WebElement cartItems()
	{
		cart.click();
		return cart;
	}
	
	public List<WebElement> cartListItem()
	{
		//System.out.println(verify_cart.size());
		return verify_cart;
	}
	
	public boolean cartItems_assert(String productName)
	{
		boolean cart_new = cartListItem().stream().anyMatch(m -> m.getText().contains(productName));
		return cart_new;
	}
	
	

}
