package madhaparTesting;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utils.Constant;
import Utils.ExcelUtils;
import Utils.Log;
import Utils.Function;

public class NewsFeed {
	private WebDriver driver;
	boolean check = false;

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
		int row = 1;
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());
		// passing password
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(2).getStringCellValue());
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
		String headlineCheck = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[1]/ul/li"))
				.getText();
		headlineCheck.contains(Constant.MANDATORY_ERROR);

		String descriptionCheck = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		descriptionCheck.contains(Constant.MANDATORY_ERROR);

		String LocationCheck = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[3]/ul/li"))
				.getText();
		LocationCheck.contains(Constant.MANDATORY_ERROR);

		String categoryCheck = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		categoryCheck.contains(Constant.MANDATORY_ERROR);
		Thread.sleep(5000);

		int row1 = 3;
		// taking value from excel headline
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		// taking description from excel
		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(2).getStringCellValue());

		// add location
		driver.findElement(By.xpath("//*[@id='add_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 1, 3, 3);
		// edit location
		driver.findElement(By.xpath("//*[@id='change_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 2, 3, 4);

		// delete Location
		driver.findElement(By.xpath("//*[@id='delete_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 3, 0, 0);

		// select location after going back to the window.and comparing from the
		// value that we have stored in the excel
		driver.findElement(By.id("id_newsLocation"));
		Function.dropDown(driver, 3, 7);
		Thread.sleep(5000);

		// add Category
		driver.findElement(By.xpath("//*[@id='add_id_newsCategory']")).click();
		Function.selectCategory(driver, 1, 3, 5);

		// edit Category
		driver.findElement(By.xpath("//*[@id='change_id_newsCategory']/img")).click();
		Function.selectCategory(driver, 2, 3, 6);

		// delete Category
		driver.findElement(By.xpath("//*[@id='delete_id_newsCategory']/img")).click();
		Function.selectCategory(driver, 3, 0, 0);

		// select category after going back to the window and comparing it from
		// the value that we have stored in the excel
		driver.findElement(By.xpath("//*[@id='id_newsCategory']"));
		Function.dropDown(driver, 3, 8);
		Thread.sleep(5000);

		// select image from excel
		driver.findElement(By.xpath("//*[@id='newsImages-group']/div/fieldset/table/tbody/tr[2]/td/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(9).getStringCellValue());

		// click on save button
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();

		// click on the news feed that we have added for the editing purpose
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();

		int row2 = 4;
		// taking news headline from 2nd row
		driver.findElement(By.id("id_newsTitle")).clear();
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(1).getStringCellValue());

		// taking description from excel
		driver.findElement(By.id("id_newsDescription")).clear();
		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(2).getStringCellValue());

		// taking news location
		driver.findElement(By.id("id_newsLocation"));
		Function.dropDown(driver, 4, 7);
		Thread.sleep(5000);

		driver.findElement(By.id("id_newsCategory"));
		Function.dropDown(driver, 4, 8);

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
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(9).getStringCellValue());

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
		
		//changing value of boolean  to true
		check = true;

	}

	@AfterMethod
	public void sendingReports() {
		Log.info("AFTER METHOD");
		Function.sendingReports(driver, check);
	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}
