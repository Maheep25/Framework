package practise.Framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest_Main {

	public static void main(String[] args) {

		String user = "msd7@gmail.com";
		String password = "Qwerty@123";
		String item = "ZARA";
		// String[] items = { "ZARA", "ADIDAS" };
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		
		
		System.out.println(driver.getTitle());

		driver.findElement(By.cssSelector("input#userEmail")).sendKeys(user);
		driver.findElement(By.cssSelector("input#userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.card-body")));
		List<WebElement> list = driver.findElements(By.cssSelector("div.card-body"));
		System.out.println(list.size());

		WebElement prod = list.stream().filter(m -> m.findElement(By.cssSelector("b")).getText().contains(item))
				.findFirst().orElse(null);

		prod.findElement(By.className("w-10")).click();
		WebDriverWait wait_p = new WebDriverWait(driver, Duration.ofSeconds(5));
		System.out.println(
				wait_p.until(ExpectedConditions.visibilityOfElementLocated((By.id("toast-container")))).getText());

		wait_p.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

		// View Cart Code
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cart = driver.findElements(By.cssSelector("div.cartSection h3"));
		System.out.println(cart.size());

		boolean flag = cart.stream().anyMatch(m -> m.getText().contains(item));

		Assert.assertTrue(flag);

		// Checkout code

		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

		// Address Select

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.cssSelector(".form-group"))).click().sendKeys("Aus").build()
				.perform();

		List<WebElement> country = driver.findElements(By.cssSelector("span.ng-star-inserted"));

		WebElement click_add = country.stream().filter(m -> m.getText().contains("Australia")).findFirst().orElse(null);
		click_add.click();

		// Place order Submit
		driver.findElement(By.className("action__submit")).click();

		// Order id-
		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().split(" ")[1].trim());

		driver.quit();
	}

}

/*
 * for (WebElement ls : list) //for(int i = 0; i< list.size(); i++) { String
 * productName = ls.getText(); if(productName.contains("ZARA.*")) {
 * driver.findElement(By.className("w-10")).click(); break; } else {
 * System.out.println("Not found"); }
 * 
 * 
 * }
 */
