package madhaparTesting;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.ss.usermodel.DataFormatter;
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

public class Events {

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

		int row = 1;
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(2).getStringCellValue());
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		Thread.sleep(2000);

		// site administration(click on events)
		driver.findElement(By.linkText("Events")).click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(2000);

		// click on add event
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();

		// click on save button for checking the mandatory fields
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(2000);

		// mandatory field error check after clicking on the save button
		String eventTitleError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[1]/ul/li"))
				.getText();
		eventTitleError.contains(Constant.MANDATORY_ERROR);

		String fromDateError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		fromDateError.contains(Constant.MANDATORY_ERROR);

		String toDateError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[3]/ul/li")).getText();
		toDateError.contains(Constant.MANDATORY_ERROR);

		String coverImageError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		coverImageError.contains(Constant.MANDATORY_ERROR);

		String oranginzedByError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[5]/ul/li"))
				.getText();
		oranginzedByError.contains(Constant.MANDATORY_ERROR);

		String locationError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[6]/ul/li"))
				.getText();
		locationError.contains(Constant.MANDATORY_ERROR);

		String addressError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[7]/ul/li")).getText();
		addressError.contains(Constant.MANDATORY_ERROR);

		String descriptionError = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[9]/ul/li"))
				.getText();
		descriptionError.contains(Constant.MANDATORY_ERROR);

		// taking 9th row
		int row1 = 9;

		// taking event title from row 7
		driver.findElement(By.xpath("//*[@id='id_eventTitle']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		// taking event from date
		DataFormatter formatter = new DataFormatter();
		XSSFCell cell = ExcelUtils.ExcelWSheet.getRow(row1).getCell(2);
		String saveFDate = formatter.formatCellValue(cell);
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_0']")).sendKeys(saveFDate);

		// taking time(from)
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_1']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(3).getStringCellValue());

		// taking to date
		XSSFCell cell1 = ExcelUtils.ExcelWSheet.getRow(row1).getCell(4);
		String saveTDate = formatter.formatCellValue(cell1);

		driver.findElement(By.xpath("//*[@id='id_eventToDate_0']")).sendKeys(saveTDate);

		// click on time (to)
		driver.findElement(By.xpath("//*[@id='id_eventToDate_1']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(5).getStringCellValue());

		// adding image
		driver.findElement(By.xpath("//*[@id='id_eventCoverImage']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(6).getStringCellValue());

		// adding organized by
		driver.findElement(By.xpath("//*[@id='id_eventOrganizedBy']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(7).getStringCellValue());
		Thread.sleep(5000);

		// select location
		driver.findElement(By.id("id_eventLocation"));
		Function.dropDown(driver, 9, 10);
		Thread.sleep(5000);

		// add address
		driver.findElement(By.xpath("//*[@id='id_eventAddress']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(11).getStringCellValue());

		// add description
		driver.findElement(By.xpath("//*[@id='id_eventDescription']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(12).getStringCellValue());

		// uploading image
		driver.findElement(By.id("id_eventPhotos-0-eventPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(13).getStringCellValue());

		// click on save
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// error check for invalid time
		String timeF_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[2]/ul/li")).getText();
		timeF_error.contains(Constant.TIME_ERROR);

		String imageError_check = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		imageError_check.contains(Constant.IMAGE_ERROR);

		String imageCheck_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		imageCheck_error.contains(Constant.IMAGE_ERROR);

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
