package madhaparTesting;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constant;
import Utils.ExcelUtils;
import Utils.Log;
import Utils.Function;

public class EventValidation {

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

		// checking title of login page
		Assert.assertTrue(Constant.LOGIN_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// passing username and password
		int row = 1;
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(2).getStringCellValue());

		// click on log in button
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		// site administration title check
		Assert.assertTrue(Constant.SELECT_FEED_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// site administration(click on events)
		driver.findElement(By.linkText("Events")).click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Assert.assertTrue(Constant.EVENT_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// click on add event
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();

		// check the title after clicking on the add title
		Assert.assertTrue(Constant.ADD_EVENT_TITLE.contains(driver.getTitle()));
		// click on save button for checking the mandatory fields
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(2000);

		// taking 8th row
		int row1 = 8;

		// taking value of event Title from excel
		driver.findElement(By.xpath("//*[@id='id_eventTitle']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		// taking date(from)
		DataFormatter formatter = new DataFormatter(); // creating formatter
		XSSFCell cell = ExcelUtils.ExcelWSheet.getRow(row1).getCell(2);
		String dateFrom = formatter.formatCellValue(cell);
		System.out.println(dateFrom);
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_0']")).sendKeys(dateFrom);

		// taking time(from)
		XSSFCell cell1 = ExcelUtils.ExcelWSheet.getRow(row1).getCell(3);
		String timeFrom = formatter.formatCellValue(cell1);
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_1']")).sendKeys(timeFrom);

		// taking date(to)
		XSSFCell cell2 = ExcelUtils.ExcelWSheet.getRow(row1).getCell(4);
		String dateTo = formatter.formatCellValue(cell2);
		driver.findElement(By.xpath("//*[@id='id_eventToDate_0']")).sendKeys(dateTo);

		// taking time(to)
		XSSFCell cell3 = ExcelUtils.ExcelWSheet.getRow(row1).getCell(5);
		String timeTo = formatter.formatCellValue(cell3);
		driver.findElement(By.xpath("//*[@id='id_eventToDate_1']")).sendKeys(timeTo);

		// taking cover image
		driver.findElement(By.xpath("//*[@id='id_eventCoverImage']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(6).getStringCellValue());

		// taking the organize field
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='id_eventOrganizedBy']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(7).getStringCellValue());

		// add location
		driver.findElement(By.xpath("//*[@id='add_id_eventLocation']/img")).click();
		Function.selectLocation(driver, 1, 8, 8);

		// edit location
		driver.findElement(By.xpath("//*[@id='change_id_eventLocation']/img")).click();
		Function.selectLocation(driver, 2, 8, 9);

		// delete location
		driver.findElement(By.xpath("//*[@id='delete_id_eventLocation']/img")).click();
		Function.selectLocation(driver, 3, 0, 0);

		// taking the location from excel
		driver.findElement(By.id("id_eventLocation"));
		Function.dropDown(driver, 8, 10);
		Thread.sleep(5000);

		// taking address
		driver.findElement(By.xpath("//*[@id='id_eventAddress']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(11).getStringCellValue());

		// taking description
		driver.findElement(By.xpath("//*[@id='id_eventDescription']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(12).getStringCellValue());

		// uploading image
		driver.findElement(By.id("id_eventPhotos-0-eventPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(13).getStringCellValue());

		// click on save
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(2000);

		// click on the that we have added
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();

		// deleting image
		driver.findElement(By.id("id_eventPhotos-0-DELETE")).click();
		Thread.sleep(2000);

		// uploading new photo that we are taking from excel
		driver.findElement(By.id("id_eventPhotos-1-eventPhoto")).sendKeys("/home/smart/Downloads/diwali.jpg");
		Thread.sleep(5000);

		// again click on save and continue editing button
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[3]")).click();
		Thread.sleep(2000);

		// click on save event
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// click on the event again
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();
		Thread.sleep(5000);

		// now click on delete
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/p/a")).click();
		Thread.sleep(5000);

		// click on yes iam sure for deleting the event
		driver.findElement(By.xpath("//*[@id='content']/form/div/input[2]")).click();
		Thread.sleep(5000);

		// changing value of boolean to true
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
