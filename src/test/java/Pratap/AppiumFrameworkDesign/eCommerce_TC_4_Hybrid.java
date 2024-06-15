package Pratap.AppiumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pratap.AppiumFrameworkDesign.pageObjects.android.CartPage;
import Pratap.AppiumFrameworkDesign.pageObjects.android.FormPage;
import Pratap.AppiumFrameworkDesign.pageObjects.android.ProductCatalogue;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_TC_4_Hybrid extends BaseTest{

	@Test
	public void verifyAmount() throws InterruptedException {
		
		formPage.setNameField("Pratap Bhowck");
		formPage.setGender("Female");
		formPage.setCountrySelection("Argentina");
		
		ProductCatalogue productCatalogue=formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage= productCatalogue.goToCartPage();
//Sync the page
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		double totalSum=cartPage.getProductSum();
		double displayFormattedSum=cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> contexts= driver.getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Pratap Bhowmick");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		String nativeTitle=driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(nativeTitle, "General Store");
	}
}
