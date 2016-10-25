package madhaparTesting;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import Utils.Function;
import Utils.Log;

public class Projects {

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

		// check title for the login page
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
		// click on the log in button
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		// check for the title of the site administration
		Assert.assertTrue(Constant.SELECT_FEED_TITLE.contains(driver.getTitle()));
		Thread.sleep(5000);

		// click on project from site administration
		driver.findElement(By.xpath("//*[@id='content-main']/div[1]/table/tbody/tr[8]/th/a")).click();

		// select project form site administration
		Assert.assertTrue(Constant.SELECT_PROJECT_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// click on add project
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(5000);
		// check for the title
		Assert.assertTrue(Constant.ADD_PROJECT_TITLE.contains(driver.getTitle()));
		Thread.sleep(2000);

		// click on save button to check for the mandatory fields
		driver.findElement(By.xpath("//*[@id='project_form']/div/div[2]/input[1]")).click();

		// project tile error after clicking on save saying project title
		// between 5 to 40 characters
		String projectTitleError = driver.findElement(By.xpath("//*[@id='project_form']/div/ul/li")).getText();
		projectTitleError.contains(Constant.PROJECT_TITLE_CHECK);

		// checking mandatory fields after clicking on the save (This field is
		// required)
		String titleError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[1]/ul/li")).getText();
		titleError.contains(Constant.MANDATORY_ERROR);

		String fromDateError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		fromDateError.contains(Constant.MANDATORY_ERROR);

		String toDateError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[3]/ul/li"))
				.getText();
		toDateError.contains(Constant.MANDATORY_ERROR);

		String projectCoverError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		projectCoverError.contains(Constant.MANDATORY_ERROR);

		String projectManagerError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[5]/ul"))
				.getText();
		projectManagerError.contains(Constant.MANDATORY_ERROR);

		String projectLocationError = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[6]/ul/li"))
				.getText();
		projectLocationError.contains(Constant.MANDATORY_ERROR);

		String projectDescriptionError = driver
				.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[7]/ul/li")).getText();
		projectDescriptionError.contains(Constant.MANDATORY_ERROR);
		Thread.sleep(5000);

		// taking 11th row
		int row1 = 11;

		// project Title
		driver.findElement(By.id("id_projectName"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		// project from date
		DataFormatter formatter = new DataFormatter();
		XSSFCell cell = ExcelUtils.ExcelWSheet.getRow(row1).getCell(2);
		String dateFrom = formatter.formatCellValue(cell);
		driver.findElement(By.id("id_projectFromDate")).sendKeys(dateFrom);

		// project to Date
		XSSFCell cell1 = ExcelUtils.ExcelWSheet.getRow(row1).getCell(3);
		String dateTo = formatter.formatCellValue(cell1);
		driver.findElement(By.id("id_projectToDate")).sendKeys(dateTo);

		// project cover
		driver.findElement(By.id("id_projectCoverImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(4).getStringCellValue());

		// project Manager
		driver.findElement(By.id("id_projectHandleby"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(5).getStringCellValue());

		// add location
		driver.findElement(By.xpath("//*[@id='add_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 1, 11, 6);

		// edit Location
		driver.findElement(By.xpath("//*[@id='change_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 2, 11, 7);

		// delete Location
		driver.findElement(By.xpath("//*[@id='delete_id_newsLocation']/img")).click();
		Function.selectLocation(driver, 3, 0, 0);

		// project location
		driver.findElement(By.id("id_newsLocation"));
		Function.dropDown(driver, 11, 8);

		// project description
		driver.findElement(By.id("id_projectDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(9).getStringCellValue());
		Thread.sleep(5000);

		// taking project total cost
		driver.findElement(By.id("id_projectTotalCost"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(10).getStringCellValue());

		// taking project total raised
		driver.findElement(By.id("id_projectTotalRaised"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(11).getStringCellValue());

		// taking project photo
		driver.findElement(By.id("id_projectPhotos-0-projectPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(12).getStringCellValue());

		// click on save
		driver.findElement(By.xpath("//*[@id='project_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// click on the event that was saved
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();
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
