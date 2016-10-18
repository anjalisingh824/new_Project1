package new_Work;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.ExcelUtils;
import Utils.Log;

public class check {

	

	public static void dropdown(WebDriver driver, int row, int cellno) throws Exception {
		List<WebElement> elementList = driver.findElements(By.tagName("option"));
		System.out.println(elementList.size());
		String str = ExcelUtils.ExcelWSheet.getRow(row).getCell(cellno).getStringCellValue();
		boolean b=false;
		
		for (int i = 0; i < elementList.size(); i++) {
			
			if (elementList.get(i).getText().equals(str)) {
				Log.info("entering if condition");
				WebElement ele = elementList.get(i);
				Thread.sleep(5000);
				ele.click();
				b=true;
				break;
			}

			
		}
		if(b==false)
		{
			elementList.get(1).click();
		}
		
	}


	
	
}
