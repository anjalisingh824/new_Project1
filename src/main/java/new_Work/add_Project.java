package new_Work;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Constant;
import Utils.ExcelUtils;

public class add_Project {

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

		// check title for the login page
		Assert.assertTrue(Constant.LogIn_Title.contains(driver.getTitle()));
		Thread.sleep(2000);
		// passing username and password
		driver.findElement(By.id("id_username")).clear();
		driver.findElement(By.id("id_username")).sendKeys(Constant.mobileNo);
		driver.findElement(By.id("id_password")).clear();
		driver.findElement(By.id("id_password")).sendKeys(Constant.password);
		// click on the log in button
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		// check for the title of the site administration
		Assert.assertTrue(Constant.SelectFeed_Title.contains(driver.getTitle()));
		Thread.sleep(5000);

		// click on project from site administration
		driver.findElement(By.xpath("//*[@id='content-main']/div[1]/table/tbody/tr[8]/th/a")).click();

		// select project form site administration
		Assert.assertTrue(Constant.selectProject_Title.contains(driver.getTitle()));
		Thread.sleep(2000);

		// click on add project
		driver.findElement(By.xpath("//*[@id='content-main']/ul/li/a")).click();
		Thread.sleep(5000);
		// check for the title
		Assert.assertTrue(Constant.addProject_Title.contains(driver.getTitle()));
		Thread.sleep(2000);

		// click on save button to check for the mandatory fields
		driver.findElement(By.xpath("//*[@id='project_form']/div/div[2]/input[1]")).click();

		// project tile error after clicking on save saying project title
		// between 5 to 40 characters
		String projTitle_error = driver.findElement(By.xpath("//*[@id='project_form']/div/ul/li")).getText();
		projTitle_error.contains(Constant.projectTitle_error);

		// checking mandatory fields after clicking on the save (This field is
		// required)
		String title_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[1]/ul/li"))
				.getText();
		title_error.contains(Constant.Mandatory_error);

		String fromDate_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[2]/ul/li"))
				.getText();
		fromDate_error.contains(Constant.Mandatory_error);

		String toDate_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[3]/ul/li"))
				.getText();
		toDate_error.contains(Constant.Mandatory_error);

		String projectCover_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[4]/ul/li"))
				.getText();
		projectCover_error.contains(Constant.Mandatory_error);

		String projectManager_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[5]/ul"))
				.getText();
		projectManager_error.contains(Constant.Mandatory_error);

		String projectLocation_error = driver.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[6]/ul/li"))
				.getText();
		projectLocation_error.contains(Constant.Mandatory_error);

		String projectDescription_error = driver
				.findElement(By.xpath("//*[@id='project_form']/div/fieldset/div[7]/ul/li")).getText();
		projectDescription_error.contains(Constant.Mandatory_error);

		Thread.sleep(5000);
		// taking values drom excel
		int row = 9;
		// project Title
		driver.findElement(By.id("id_projectName"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(1).getStringCellValue());

		// project from date
		DataFormatter formatter = new DataFormatter();
		XSSFCell cell = ExcelUtils.ExcelWSheet.getRow(row).getCell(2);
		String dateFrom = formatter.formatCellValue(cell);
		driver.findElement(By.id("id_projectFromDate")).sendKeys(dateFrom);

		// project to Date
		XSSFCell cell1 = ExcelUtils.ExcelWSheet.getRow(row).getCell(3);
		String dateTo = formatter.formatCellValue(cell1);
		driver.findElement(By.id("id_projectToDate")).sendKeys(dateTo);

		// project cover
		driver.findElement(By.id("id_projectCoverImage"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(4).getStringCellValue());

		// project Manager
		driver.findElement(By.id("id_projectHandleby"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(5).getStringCellValue());

		// add location
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id='add_id_newsLocation']/img")).click();
		Set s = driver.getWindowHandles();
		Iterator ite = s.iterator();
		while (ite.hasNext()) {
			String popupHandle = ite.next().toString();
			if (!popupHandle.contains(mainWindow)) {
				driver.switchTo().window(popupHandle);
				WebElement add_Location = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				Thread.sleep(2000);
				add_Location.sendKeys("surat");
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();

			}
		}
		driver.switchTo().window(mainWindow);
		Thread.sleep(5000);

		// edit Location
		driver.findElement(By.xpath("//*[@id='change_id_newsLocation']/img")).click();
		Set s1 = driver.getWindowHandles();
		Iterator ite1 = s1.iterator();
		while (ite1.hasNext()) {
			String popupHandle = ite1.next().toString();
			if (!popupHandle.contains(mainWindow)) {
				driver.switchTo().window(popupHandle);
				WebElement change_Location = driver.findElement(By.xpath("//*[@id='id_locationName']"));
				change_Location.clear();
				Thread.sleep(2000);
				change_Location.sendKeys("Newcastle");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
			}
		}
		driver.switchTo().window(mainWindow);
		Thread.sleep(5000);

		// delete Location
		driver.findElement(By.xpath("//*[@id='delete_id_newsLocation']/img")).click();
		Set s2 = driver.getWindowHandles();
		Iterator ite2 = s2.iterator();
		while (ite2.hasNext()) {
			String popupHandle = ite2.next().toString();
			if (!popupHandle.contains(mainWindow)) {
				driver.switchTo().window(popupHandle);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]")).click();
			}
		}
		driver.switchTo().window(mainWindow);
		Thread.sleep(5000);

		// project location
		driver.findElement(By.id("id_newsLocation"));
		String store_ValuefromEcxel = ExcelUtils.ExcelWSheet.getRow(row).getCell(6).getStringCellValue();
		List<WebElement> list = driver.findElements(By.tagName("option"));
		String se = "cat";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(store_ValuefromEcxel)) {
				WebElement e = list.get(i);
				Thread.sleep(2000);
				e.click();
				se = "dog";
				break;
			}

		}
		if (se.equals("cat")) {

			list.get(1).click();
			System.out.println("entering if if loop");
		}

		// project description
		driver.findElement(By.id("id_projectDescription"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(7).getStringCellValue());
		Thread.sleep(5000);

		// taking project total cost
		driver.findElement(By.id("id_projectTotalCost"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(8).getStringCellValue());

		// taking project total raised
		driver.findElement(By.id("id_projectTotalRaised"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(9).getStringCellValue());

		// taking project photo
		driver.findElement(By.id("id_projectPhotos-0-projectPhoto"))
				.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(10).getStringCellValue());

		// click on save
		driver.findElement(By.xpath("//*[@id='project_form']/div/div[2]/input[1]")).click();
		Thread.sleep(5000);

		
		//click on the event that was saved 
		driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/th/a")).click();
		Thread.sleep(5000);
	
	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}
}
