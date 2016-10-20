package new_Work;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utils.Constant;
import Utils.ExcelUtils;
import Utils.Log;

public class Magadhpar_Test {
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/smart/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get(Constant.URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExcelUtils.getTableArray(Constant.FILE_FULL_PATH, Constant.SHEET_NAME);
	}

	@Test
	public void testMagadh() throws Exception {

		// checking title of the login page
		Assert.assertTrue(Constant.LOGIN_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);
		// passing mobile no
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username")).sendKeys(Constant.MOBILE_NO);
		// passing password
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password")).sendKeys(Constant.PASSWORD);
		// clicking on submit
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		// checking title for the site administration
		Assert.assertTrue(Constant.SELECT_FEED_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// clicking on news feed
		driver.findElement(By.linkText("News feeds")).click();
		// waiting next page to load
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		// checking title for the news feed page
		Assert.assertTrue(Constant.NEWS_FEED_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// clicking on the add news feed
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(2000);

		// checking title for add news feed page
		Assert.assertTrue(Constant.ADD_NEWSFEED_TITLE.contains(driver.getTitle()));

		// clicking on the save button to check for the mandatory fields
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// Error checks(Mandatory fields check)
		String headline_check = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[1]/ul/li"))
				.getText();
		headline_check.contains(Constant.MANDATORY_ERROR);

		String description_check = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		description_check.contains(Constant.MANDATORY_ERROR);

		String Location_check = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[3]/ul/li"))
				.getText();
		Location_check.contains(Constant.MANDATORY_ERROR);

		String category_check = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		category_check.contains(Constant.MANDATORY_ERROR);
		Thread.sleep(5000);

		// taking value from excel headline
		int row = 1;
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());

		// taking description from excel
		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(2).getStringCellValue());

		// add location
		String mainWindowHandle = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id='add_id_newsLocation']/img")).click();
		Thread.sleep(5000);
		Set s = driver.getWindowHandles();
		Iterator ite = s.iterator();
		while (ite.hasNext()) {
			String popupHandle = ite.next().toString();
			if (!popupHandle.contains(mainWindowHandle)) {
				driver.switchTo().window(popupHandle);
				WebElement send_Location = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				send_Location.sendKeys("vegas");
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
				Thread.sleep(2000);

			}

		}
		driver.switchTo().window(mainWindowHandle);
		Thread.sleep(5000);

		// edit location
		driver.findElement(By.xpath("//*[@id='change_id_newsLocation']/img")).click();
		Set s2 = driver.getWindowHandles();
		Iterator ite2 = s2.iterator();
		while (ite2.hasNext()) {
			String popupHandle = ite2.next().toString();
			if (!popupHandle.contains(mainWindowHandle)) {
				driver.switchTo().window(popupHandle);
				WebElement change_Location = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				change_Location.clear();
				Thread.sleep(2000);
				change_Location.sendKeys("Newcastle");
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
				Thread.sleep(2000);
			}

		}
		driver.switchTo().window(mainWindowHandle);
		Thread.sleep(5000);

		// delete Location
		driver.findElement(By.xpath("//*[@id='delete_id_newsLocation']/img")).click();
		Set s1 = driver.getWindowHandles();
		Iterator ite1 = s1.iterator();
		while (ite1.hasNext()) {
			String popupHandle = ite1.next().toString();
			if (!popupHandle.contains(mainWindowHandle)) {
				Thread.sleep(5000);
				driver.switchTo().window(popupHandle);
				WebElement delete_Location = driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]"));
				delete_Location.click();
				Thread.sleep(5000);
			}
		}
		driver.switchTo().window(mainWindowHandle);

		// select location after going back to the window.and comparing from the
		// value that we have stored in the excel
		driver.findElement(By.id("id_newsLocation"));
		check.dropdown(driver, 1, 3);
		Thread.sleep(5000);

		// add Category
		String mainwindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id='add_id_newsCategory']/img")).click();
		Set s3 = driver.getWindowHandles();
		Iterator ite3 = s3.iterator();
		while (ite3.hasNext()) {
			String popupHandle = ite3.next().toString();
			if (!popupHandle.contains(mainwindow)) {
				Thread.sleep(5000);
				driver.switchTo().window(popupHandle);
				driver.findElement(By.xpath("//*[@id='id_categoryName']")).sendKeys("hashtag");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();
				Thread.sleep(5000);
			}
		}
		driver.switchTo().window(mainwindow);
		Thread.sleep(5000);

		// edit Category

		driver.findElement(By.xpath("//*[@id='change_id_newsCategory']/img")).click();
		Set s4 = driver.getWindowHandles();
		Iterator ite4 = s4.iterator();
		while (ite4.hasNext()) {
			String popupHandle = ite4.next().toString();
			if (!popupHandle.contains(mainwindow)) {
				Thread.sleep(5000);
				driver.switchTo().window(popupHandle);
				WebElement clear1 = driver.findElement(By.xpath("//*[@id='id_categoryName']"));
				clear1.clear();
				Thread.sleep(5000);
				clear1.sendKeys("Life & Style");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();
				Thread.sleep(5000);
			}
		}
		driver.switchTo().window(mainwindow);
		Thread.sleep(5000);

		// delete Category
		driver.findElement(By.xpath("//*[@id='delete_id_newsCategory']/img")).click();
		Set s5 = driver.getWindowHandles();
		Iterator ite5 = s5.iterator();
		while (ite5.hasNext()) {
			String popupHandle = ite5.next().toString();
			if (!popupHandle.contains(mainwindow)) {
				Thread.sleep(5000);
				driver.switchTo().window(popupHandle);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]")).click();
				Thread.sleep(2000);

			}
		}
		driver.switchTo().window(mainwindow);
		Thread.sleep(5000);

		// select category after going back to the window and comparing it from
		// the value that we have stored in the excel
		driver.findElement(By.id("id_newsCategory"));
		check.dropdown(driver, 1, 4);
		Thread.sleep(5000);

		// select image from excel
		driver.findElement(By.xpath("//html/body/div/div[3]/div/form/div/div[1]/div/fieldset/table/tbody/tr[2]/td/a"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(5).getStringCellValue());

		// click on save button
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();

		// click on the news feed that we have added for the editing purpose
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();

		int row1 = 2;
		// taking news headline from 2nd row
		driver.findElement(By.id("id_newsTitle")).clear();
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		// taking description from excel
		driver.findElement(By.id("id_newsDescription")).clear();
		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(2).getStringCellValue());

		// taking news location
		driver.findElement(By.id("id_newsLocation"));
		check.dropdown(driver, 2, 3);
		Thread.sleep(5000);

		driver.findElement(By.id("id_newsCategory"));
		check.dropdown(driver, 2, 4);

		// deleting image
		driver.findElement(By.id("id_newsImages-0-DELETE")).click();

		// click on save
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// click on the event that we added after editing
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();

		Thread.sleep(5000);

		// adding image again
		driver.findElement(By.xpath("//html/body/div/div[3]/div/form/div/div[1]/div/fieldset/table/tbody/tr[2]/td/a"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(5).getStringCellValue());

		// click on save button again
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// click on the event
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();

		// now delete the news feed that we have added
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/p/a")).click();
		Thread.sleep(5000);

		// clicking on yes iam sure button for deleting
		driver.findElement(By.xpath("//*[@id='content']/form/div/input[2]")).click();
		Thread.sleep(5000);

	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}
