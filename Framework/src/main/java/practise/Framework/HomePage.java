package practise.Framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComp;

public class HomePage extends AbstractComp {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.card-body")

	List<WebElement> items;

	By shop = By.cssSelector("div.card-body");
	By addtocart = By.className("w-10");
	By response = By.id("toast-container");
	
//	@FindBy(id = "toast-container")
//	WebElement invisible_response;
	
	By invisible_response = By.id("toast-container");

	public List<WebElement> itemList() {
		waitComponent(shop);
		return items;
	}

	public WebElement productNameList(String productName) {
		WebElement prod = itemList().stream()
				.filter(m -> m.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addToCart(String productName) {

		WebElement prod = productNameList(productName);
		prod.findElement(addtocart).click();
		waitComponent(response);
		invisibleComponent(invisible_response);
		
	}

}
