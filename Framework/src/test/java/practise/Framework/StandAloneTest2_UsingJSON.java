package practise.Framework;

import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import practise.TestComponents.BaseTest;

public class StandAloneTest2_UsingJSON extends BaseTest {

	@Test(dataProvider = "dataJson", groups = { "MakeOrder" })
	public void submitOrder(HashMap<String, String> inputs) throws IOException {

		// Login loginDetails = launchApp();

		System.out.println(driver.getTitle());

		loginDetails.loginPage(inputs.get("user"), inputs.get("password"));

		HomePage homePage = new HomePage(driver);
		List<WebElement> items = homePage.itemList();
		items.size();
		homePage.addToCart(inputs.get("productName"));

		ViewCart cart_page = new ViewCart(driver);
		cart_page.cartItems();

		boolean cart_new = cart_page.cartItems_assert(inputs.get("productName"));
		Assert.assertTrue(cart_new);
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

	@Test(dataProvider = "dataJson", dependsOnMethods = { "submitOrder" }, groups = { "MakeOrder" })
	public void order_info(HashMap<String, String> inputs) {
		loginDetails.loginPage(inputs.get("user"), inputs.get("password"));
		OrderHistory ord = new OrderHistory(driver);

		ord.orderDetails();
		ord.verifyOrder();
	}
	
	
	//ScreenShot
	
	public File screenShot(String tcName) throws IOException
	{
		TakesScreenshot ss = (TakesScreenshot)driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File file_name= new File(System.getProperty("user.dir" + "/test-output" + tcName +".png"));
		FileUtils.copyFile(src, file_name);
		return file_name;
	}

	@DataProvider
	public Object[][] dataJson() throws IOException {

		List<HashMap<String, String>> data = readJsonFile(
				System.getProperty("user.dir") + "/src/test/java/practise/data/PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	@Test(dependsOnMethods = { "order_info" })
	public void message() {
		System.out.println("This TC is with JSONData");
	}

}
