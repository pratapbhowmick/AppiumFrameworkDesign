package Pratap.AppiumFrameworkDesign.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Pratap.AppiumFrameworkDesign.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;
	
	public List<WebElement> getProductList() {
		return productList;
	}
	public double getProductSum() {
		int count=productList.size();
		double totalSum=0;
		for (WebElement product : productList) {
			String amountString =product.getText();
			Double price=getFormattedAmount(amountString);
			totalSum=totalSum+price;
		}
		return totalSum;
	}
	public Double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}
	public void acceptTermsConditions() {
		longPressAction(terms);
		acceptButton.click();		
	}
	public Double getFormattedAmount(String amount) {
		return Double.parseDouble(amount.substring(1));
	}
	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}
}
