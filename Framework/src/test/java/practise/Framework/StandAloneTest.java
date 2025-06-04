package practise.Framework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import practise.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {

//	String user = "msd7@gmail.com";
//	String password = "Qwerty@123";
//	String productName = "ZARA";

	@Test(dataProvider = "dataJson", groups = {"MakeOrder"})
	public void submitOrder(String user, String password, String productName ) throws IOException {

		// Login loginDetails = launchApp();

		System.out.println(driver.getTitle());

		loginDetails.loginPage(user, password);

		HomePage homePage = new HomePage(driver);
		List<WebElement> items = homePage.itemList();
		items.size();
		homePage.addToCart(productName);

		ViewCart cart_page = new ViewCart(driver);
		cart_page.cartItems();

		boolean cart_new = cart_page.cartItems_assert(productName);
		AssertJUnit.assertTrue(cart_new);
		System.out.println(cart_page.verify_cart.size());

		// Checkout code
		Checkout checkoutPay = new Checkout(driver);
		checkoutPay.checkoutPage();

		// Address Select
		Address send_add = new Address(driver);
		send_add.ship();
		send_add.countriesList();
		send_add.correct_country();

		// Place order Submit
		Order_OrderId o_id = new Order_OrderId(driver);
		o_id.paid();

		// Order id-
		o_id.order_id();

	}

	@Test(dataProvider = "dataJson" ,dependsOnMethods = { "submitOrder" },  groups = {"MakeOrder"})
	public void order_info(String user, String password, String productName) {
		loginDetails.loginPage(user, password);
		OrderHistory ord = new OrderHistory(driver);
		
		ord.orderDetails();
		ord.verifyOrder();
	}
	
	@DataProvider
	public Object[][] dataJson()
	{
		return new Object [] [] {{"msd7@gmail.com", "Qwerty@123", "ZARA"}, {"msd712@gmail.com", "Qwerty@123", "ADIDAS"}};
		
	}

}
