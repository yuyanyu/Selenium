package com.iie.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDemo {
	/**
	 * @param args
	 */
	private static String email="yuyanyuwo@126.com";
	private static String pass="yuyanyuface123";
	// 创建实例
	private static WebDriver dr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\res\\chromedriver.exe");
		dr =new ChromeDriver();
		Login();
		Search("习近平");
	}
	
	public static void Login(){
		// 创建实例
		dr.get("https://www.facebook.com/");
		// 另一种访问方式
		// driver.navigate().to("http://www.google.com");
		
		//等待元素出现
		WebDriverWait wait = new WebDriverWait(dr, 10);  
		wait.until(ExpectedConditions.elementToBeClickable(By.id("u_0_l")));  

		// 注册框
		WebElement lastname = dr.findElement(By.name("email"));
		lastname.sendKeys(email);
		WebElement passw = dr.findElement(By.name("pass"));
		passw.sendKeys(pass);
		// 提交表单，WebDriver会自动查找表单并提交
		passw.submit();
		// 显示查询结果title
		System.out.println("登录成功--" + dr.getTitle());
	}
	
	//查询账号信息
	public static void Search(String SearchKey){
		System.out.println("进入查询");
		//dr.get("https://www.facebook.com/?sk=welcome");  
//      dr.navigate().to(BASE_URL);//与dr.get()效果相同  
//      dr.navigate().forward();//让浏览器前进一步  
//      dr.navigate().back();//让浏览器后退一步  
 //    Log.info(dr.getPageSource());
		
		//加载，等待出现
		WebDriverWait wait = new WebDriverWait(dr, 5);  
		wait.until(ExpectedConditions.elementToBeClickable(By.className("navLink"))); 
		//寻找搜索框
		WebElement element= dr.findElement(By.id("q"));
		element.sendKeys(SearchKey);
		//自动寻找提交，或者寻找点击后提交
		element.submit();
		//dr.get("https://www.facebook.com/search/results/?q="+SearchKey);
		wait = new WebDriverWait(dr, 5);  
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_1yt"))); 
		WebElement results=dr.findElement(By.className("_1yt"));
		List<WebElement> re= results.findElements(By.className("_gll"));
		for(WebElement we:re){
			//获取账户信息
			System.out.println(we.findElement(By.tagName("a")).getAttribute("href").toString());
		}
		
		dr.get(re.get(re.size()-6).findElement(By.tagName("a")).getAttribute("href").toString());
		
		//等待加载
		System.out.println("正在操作Report过程");
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("fbPageTimelineActionSelector")));
		WebElement sE=dr.findElement(By.id("fbPageTimelineActionSelector"));
		sE.click();
		WebElement sElement=dr.findElement(By.id("u_0_1p"));
		List<WebElement> reports= sElement.findElements(By.tagName("li"));
		//返回Ajax操作条框
		reports.get(4).click();
		
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("u_6_4")));
		WebElement garbage= dr.findElement(By.id("u_6_4"));
		garbage.click();
		garbage.submit();
		
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("report_type_9")));
		WebElement report_type= dr.findElement(By.id("report_type_9"));
		report_type.click();
		report_type.submit();
		
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_14")));
		List<WebElement> finall= dr.findElements(By.className("_14"));
		for(WebElement we:finall){
			System.out.println(we.findElement(By.tagName("a")).getAttribute("href").toString());
			if(we.findElement(By.tagName("a")).getAttribute("href").toString().equals("https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1471753823066289?ref=br_rs#"))
				we.click();
		}
		System.out.println("操作完成");
	}
}
