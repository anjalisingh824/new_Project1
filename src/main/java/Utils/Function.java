package Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Function {

	// adding location
	public static void addLocation(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				WebElement addLoc = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				Thread.sleep(5000);
				addLoc.sendKeys(Constant.ADD_LOCATION);
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
				Thread.sleep(2000);

			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// changing location
	public static void changeLocation(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				WebElement changeLoc = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				changeLoc.clear();
				Thread.sleep(2000);
				changeLoc.sendKeys(Constant.EDIT_LOCATION);
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
				Thread.sleep(2000);
			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// deleting location
	public static void deleteLocation(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				WebElement delLocation = driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]"));
				delLocation.click();
				Thread.sleep(5000);
			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// adding category
	public static void addCategory(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				driver.findElement(By.xpath("//*[@id='id_categoryName']")).sendKeys(Constant.ADD_CATEGORY);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();

			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// changing category
	public static void changeCategory(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				WebElement changeCat = driver.findElement(By.xpath("//*[@id='id_categoryName']"));
				Thread.sleep(5000);
				changeCat.clear();
				changeCat.sendKeys(Constant.EDIT_CATEGORY);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();
			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// deleting category
	public static void deleteCategory(WebDriver driver) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]")).click();
			}
		}

		driver.switchTo().window(openWindowHandle);
		Thread.sleep(5000);

	}

	// selecting dropdown
	public static void dropDown(WebDriver driver, int row, int cellno) throws Exception {
		List<WebElement> elementList = driver.findElements(By.tagName("option"));
		System.out.println(elementList.size());
		String str = ExcelUtils.ExcelWSheet.getRow(row).getCell(cellno).getStringCellValue();
		boolean check = false;

		for (int i = 0; i < elementList.size(); i++) {

			if (elementList.get(i).getText().equals(str)) {
				Log.info("entering if condition");
				WebElement elementClick = elementList.get(i);
				Thread.sleep(5000);
				elementClick.click();
				check = true;
				break;
			}

		}
		if (!check) {
			elementList.get(1).click();
		}

	}

}
