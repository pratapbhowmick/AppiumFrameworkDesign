package Pratap.AppiumFrameworkDesign.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
	}
	public void longPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void scrollToEndAction() {
		Boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "percent", 3.0, "direction", "down"));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement element, String direction) {

		
		WebElement firstImage = driver.findElement(By.xpath("//android.widget.ImageView[1]"));
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}
	public void dragAndDropAction(WebElement element, int endX,int endY) {
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
				"endX",endX,"endY",endY));
	}
	public void scrolltoText(String text) {
		driver.findElement(AppiumBy.
				androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

	}
}
