package new_Work;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constant;
import Utils.ExcelUtils;
import Utils.Log;

public class validationNews_Feed {
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

		driver.findElement(By.linkText("News feeds")).click();
		Thread.sleep(5000);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(2000);

		// news feed save button click
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// News title should be of 10 to 40 characters
		String title_check = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/ul/li")).getText();
		title_check.contains(Constant.titleError_check);

		// executing row 1 from excel and then passing values
		int row1 = 3;
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(1).getStringCellValue());

		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(2).getStringCellValue());

		Thread.sleep(2000);
		// select location
		driver.findElement(By.id("id_newsLocation"));
		check.dropdown(driver,3,3);
		// select category
		driver.findElement(By.id("id_newsCategory"));
		check.dropdown(driver,3,4);

		// selecting image in pdf format so,it should give error
		driver.findElement(By.linkText("Add another News image")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage")).clear();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row1).getCell(5).getStringCellValue());
		Thread.sleep(5000);

		// click on save button but gives error for the title limit
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// limit check for title whether the error present or not(News title
		// should be of 10 to 40 characters)
		String checkError_title = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/ul/li")).getText();
		checkError_title.contains(Constant.titleError_check);

		// image error check if we wl try 2 upload pdf and other formats
		String checkImage_error = driver
				.findElement(By
						.xpath("//html/body/div/div[3]/div/form/div/div[1]/div/fieldset/table/tbody/tr[1]/td[2]/ul/li"))
				.getText();
		checkImage_error.contains(Constant.image_error);

		// taking 2nd row now for checking the description limit
		int row2 = 4;
		// taking news title
		driver.findElement(By.id("id_newsTitle")).clear();
		driver.findElement(By.id("id_newsTitle"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(1).getStringCellValue());
		Thread.sleep(5000);

		// taking new description
		driver.findElement(By.id("id_newsDescription")).clear();
		driver.findElement(By.id("id_newsDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(2).getStringCellValue());
		Thread.sleep(5000);

		// selecting image in xlsx format so it should give error
		driver.findElement(By.linkText("Add another News image")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage")).clear();
		Thread.sleep(5000);
		driver.findElement(By.id("id_newsImages-0-newsImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row2).getCell(5).getStringCellValue());
		Thread.sleep(5000);

		// save button click
		driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		// description limit check after clicking on the save button
		String description_error = driver.findElement(By.xpath("//*[@id='newsfeed_form']/div/ul/li")).getText();
		description_error.contains(Constant.description_error);

	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}
