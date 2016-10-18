package new_Work;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constant;
import Utils.ExcelUtils;
import Utils.Log;

public class Events {

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
		String eventTitle_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[1]/ul/li"))
				.getText();
		eventTitle_error.contains(Constant.Mandatory_error);

		String fromDate_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		fromDate_error.contains(Constant.Mandatory_error);

		String toDate_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[3]/ul/li")).getText();
		toDate_error.contains(Constant.Mandatory_error);

		String coverImgage_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		coverImgage_error.contains(Constant.Mandatory_error);

		String oranginzedBy_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[5]/ul/li"))
				.getText();
		oranginzedBy_error.contains(Constant.Mandatory_error);

		String location_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[6]/ul/li"))
				.getText();
		location_error.contains(Constant.Mandatory_error);

		String address_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[7]/ul/li"))
				.getText();
		address_error.contains(Constant.Mandatory_error);

		String description_error = driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[9]/ul/li"))
				.getText();
		description_error.contains(Constant.Mandatory_error);

		int row = 7;
		// taking event title from row 7
		driver.findElement(By.xpath("//*[@id='id_eventTitle']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());

		// taking event from date
		DataFormatter formatter=new DataFormatter();
		XSSFCell cell=ExcelUtils.ExcelWSheet.getRow(row).getCell(2);
		String saveFDate=formatter.formatCellValue(cell);
		
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_0']"))
				.sendKeys(saveFDate);

		// taking time(from)
		driver.findElement(By.xpath("//*[@id='id_eventFromDate_1']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(3).getStringCellValue());

		// taking to date
		XSSFCell cell1=ExcelUtils.ExcelWSheet.getRow(row).getCell(4);
		String saveTDate=formatter.formatCellValue(cell1);
		
		driver.findElement(By.xpath("//*[@id='id_eventToDate_0']"))
				.sendKeys(saveTDate);

		// click on time (to)
		driver.findElement(By.xpath("//*[@id='id_eventToDate_1']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(5).getStringCellValue());

		// adding image
		driver.findElement(By.xpath("//*[@id='id_eventCoverImage']"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(6).getStringCellValue());

		// adding organized by
		driver.findElement(By.xpath("//*[@id='id_eventOrganizedBy']")).sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(7).getStringCellValue());
		Thread.sleep(5000);

		// select location
		driver.findElement(By.id("id_eventLocation"));
		String save_Location = ExcelUtils.ExcelWSheet.getRow(row).getCell(8).getStringCellValue();
		List<WebElement> event_Location = driver.findElements(By.tagName("option"));
		String s5 = "cat";
		for (int i = 0; i < event_Location.size(); i++) {
			
			if (event_Location.get(i).getText().equals(save_Location)) {

				Log.info("this is if condtion");
				WebElement ele = event_Location.get(i);
				Thread.sleep(5000);
				ele.click();
				s5 = "dog";
				break;
			}
			
		}
		if (s5.equals("cat")) {

			event_Location.get(1).click();

		}
		Thread.sleep(5000);


		// add address
		driver.findElement(By.xpath("//*[@id='id_eventAddress']")).sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(9).getStringCellValue());

		// add description
		driver.findElement(By.xpath("//*[@id='id_eventDescription']")).sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(10).getStringCellValue());

		// uploading image
		driver.findElement(By.id("id_eventPhotos-0-eventPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(11).getStringCellValue());
		// click on save
		driver.findElement(By.xpath("//*[@id='event_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);
       
		//error check for invalid time 
		String timeF_error=driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[2]/ul/li")).getText();
		timeF_error.contains(Constant.Time_error);
		
		String imageError_check=driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li")).getText();
		imageError_check.contains(Constant.image_error);
		
		
		String imageCheck_error=driver.findElement(By.xpath("//*[@id='event_form']/div/fieldset/div[4]/ul/li")).getText();
		imageCheck_error.contains(Constant.image_error);
		
		
	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}
}
