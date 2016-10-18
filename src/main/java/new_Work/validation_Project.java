package new_Work;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constant;
import Utils.ExcelUtils;

public class validation_Project {
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

		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username")).sendKeys(Constant.mobileNo);
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password")).sendKeys(Constant.password);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		Thread.sleep(5000);
		// click on project from site administration
		driver.findElement(By.xpath("//*[@id='content-main']/div[1]/table/tbody/tr[8]/th/a")).click();

		// click on add project
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(5000);

		int row = 10;

		// taking title
		driver.findElement(By.id("id_projectName"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());

		// taking special characters for date from excel
		driver.findElement(By.id("id_projectFromDate"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(2).getStringCellValue());

		driver.findElement(By.id("id_projectToDate"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(3).getStringCellValue());

		// taking project cover in pdf format ,so it should give error
		driver.findElement(By.id("id_projectCoverImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(4).getStringCellValue());

		// taking description
		driver.findElement(By.id("id_projectDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(7).getStringCellValue());

		// taking project total cost
		driver.findElement(By.id("id_projectTotalCost"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(8).getStringCellValue());

		// taking project total raised
		driver.findElement(By.id("id_projectTotalRaised"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(9).getStringCellValue());

		// default photo
		driver.findElement(By.id("id_projectPhotos-0-projectPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(10).getStringCellValue());
		Thread.sleep(5000);
		// click on add another image
		// 1st image
		driver.findElement(By.xpath("//*[@id='projectPhotos-group']/div/fieldset/table/tbody/tr[3]/td/a")).click();
		driver.findElement(By.id("id_projectPhotos-1-projectPhoto")).sendKeys("/home/smart/Desktop/cat1.jpg");
		Thread.sleep(5000);
		// click on another image
		// 2nd image
		driver.findElement(By.xpath("//*[@id='projectPhotos-group']/div/fieldset/table/tbody/tr[4]/td/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='id_projectPhotos-2-projectPhoto']"))
				.sendKeys("/home/smart/Desktop/images.png");
		Thread.sleep(5000);
		// click on another image
		// 3rd image
		driver.findElement(By.xpath("//*[@id='projectPhotos-group']/div/fieldset/table/tbody/tr[5]/td/a")).click();
		driver.findElement(By.id("id_projectPhotos-3-projectPhoto")).sendKeys("/home/smart/Desktop/profile.xlsx");
		Thread.sleep(5000);

		// click on save button ...so that date fields should give error.
		driver.findElement(By.xpath("//*[@id='project_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// error check for date from
		String projectDateFrom_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		projectDateFrom_error.contains(Constant.Date_error);

		// error check for date to
		String projectDateTo_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[3]/ul/li"))
				.getText();
		projectDateFrom_error.contains(Constant.Date_error);

		// description limit error check
		String projectDescription_error = driver.findElement(By.xpath("//*[@id='project_form']/div/ul/li")).getText();
		projectDescription_error.contains(Constant.descriptionProject_error);

		// image error if we r trying 2 upload any images other than jpeg and
		// png.
		String Projectimage_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		Projectimage_error.contains(Constant.image_error);

	    //project total cost error
		String projectTC_error=driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[8]/ul/li")).getText();
		projectTC_error.contains(Constant.projectCost_error);
		
		//project total raised error
		String projectTR_error=driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[8]/ul/li")).getText();
		projectTR_error.contains(Constant.projectCost_error);
	
	    
	
	
	
	
	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}