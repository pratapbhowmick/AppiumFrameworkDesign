package Pratap.AppiumFrameworkDesign;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Pratap.AppiumFrameworkDesign.pageObjects.android.FormPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	@BeforeClass
	public void configureAppium() throws MalformedURLException {

//		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
//				.withAppiumJS(
//						new File("C:\\Users\\prata\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723);
//		service = serviceBuilder.build();
		// service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("PIXEL_5_API_31");
		options.setChromedriverExecutable("E:\\EclipsePakage\\eclipse-workspace\\Appium\\src\\main\\java\\resources\\chromedriver.exe");
		options.setApp("E:\\EclipsePakage\\eclipse-workspace\\Appium\\src\\main\\java\\resources\\General-Store.apk");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage=new FormPage(driver);
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
	public Double formattedAmount(String amount) {
		return Double.parseDouble(amount.substring(1));
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
		// service.stop();
	}
}
