package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Magenta;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Function {

	public static void selectLocation(WebDriver driver, int type, int row, int cell) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				if (type == 1) {

					WebElement addLoc = driver.findElement(By.xpath("//*[@id='id_locationName']"));
					Thread.sleep(5000);
					addLoc.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(cell).getStringCellValue());
					driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);
				}

				else if (type == 2) {
					WebElement changeLoc = driver.findElement(By.xpath("//*[@id='id_locationName']"));
					changeLoc.clear();
					changeLoc.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(cell).getStringCellValue());
					driver.findElement(By.xpath("//*[@id='location_form']/div/div/input")).click();
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);

				} else if (type == 3) {
					WebElement delLocation = driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]"));
					delLocation.click();
					Thread.sleep(5000);
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);
				}
			}
		}
	}

	public static void selectCategory(WebDriver driver, int type, int row, int cell) throws Exception {
		String openWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);

				if (type == 1) {

					driver.findElement(By.xpath("//*[@id='id_categoryName']"))
							.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(cell).getStringCellValue());
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);
				}

				else if (type == 2) {

					WebElement changeCat = driver.findElement(By.xpath("//*[@id='id_categoryName']"));
					changeCat.clear();
					changeCat.sendKeys(ExcelUtils.ExcelWSheet.getRow(row).getCell(cell).getStringCellValue());
					Thread.sleep(2000);
					driver.findElement(By.xpath("//*[@id='category_form']/div/div/input")).click();
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);

				} else if (type == 3) {

					driver.findElement(By.xpath("//*[@id='content']/form/div/input[4]")).click();
					Thread.sleep(5000);
					driver.switchTo().window(openWindowHandle);
					Thread.sleep(5000);
				}
			}

		}
	}

	// selecting dropdown
	public static void dropDown(WebDriver driver, int row, int cellno) throws Exception {
		List<WebElement> elementList = driver.findElements(By.tagName("option"));
		System.out.println(elementList.size());
		String str = ExcelUtils.ExcelWSheet.getRow(row).getCell(cellno).getStringCellValue();
		boolean check = false;

		for (int i = 0; i < elementList.size(); i++) {

			if (elementList.get(i).getText().equals(str)) {
				Log.info("entering if condition");
				WebElement elementClick = elementList.get(i);
				Thread.sleep(5000);
				elementClick.click();
				check = true;
				break;
			}

		}
		if (!check) {
			elementList.get(1).click();
		}

	}

	// sending reports
	public static void sendingReports(WebDriver driver, boolean changeValue) {

		final String username = "anjali.smartsensesolutions@gmail.com";
		final String password = "smartAnjali";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		if (changeValue) {
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-anjali.smartsensesolutions@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to-smartsensetab@gmail.com"));
				message.setSubject("Successful execution report");
				message.setText("Successful execution of test case," + "\n\n sending report");
				Transport.send(message);
				Log.info("SUCCESSFULLY DONE");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else {

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-anjali.smartsensesolutions@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to-smartsensetab@gmail.com"));
				message.setSubject("Failure execution report");
				message.setText("Failure  of test case," + "\n\n sending report");
				Transport.send(message);
				Log.info("FAILURE DONE");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	}
}
