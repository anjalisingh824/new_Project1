package new_Work;

import java.util.List;
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

public class dummy1 {

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

		Thread.sleep(2000);
		// passing mobile no
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username")).sendKeys(Constant.mobileNo);
		// passing password
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password")).sendKeys(Constant.password);
		// clicking on submit
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		// clicking on news feed
		driver.findElement(By.linkText("News feeds")).click();
		// waiting next page to load
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		// checking title for the news feed page
		Assert.assertTrue(Constant.NewsFeed_Title.contains(driver.getTitle()));
		Thread.sleep(2000);

		// clicking on the add news feed
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(2000);

		int row = 1;
		driver.findElement(By.id("id_newsLocation"));
		List<WebElement> list = driver.findElements(By.tagName("option"));
		System.out.println(list.size());
		String str = ExcelUtils.ExcelWSheet.getRow(row).getCell(3).getStringCellValue();
		String string = "cat";
		for (int i = 0; i < list.size(); i++) {
			System.out.println("entering into for loop");
			if (list.get(i).getText().equals(str)) {

				System.out.println("entering into if loop");
				WebElement ele = list.get(i);
				Thread.sleep(5000);
				ele.click();
				string = "dog";
			    break;
			}
			
		}
		Thread.sleep(5000);
		if (string.equals("cat")) {

			list.get(1).click();
			
		}
	Thread.sleep(5000);
	}
	

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}
