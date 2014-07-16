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
	// ����ʵ��
	private static WebDriver dr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\res\\chromedriver.exe");
		dr =new ChromeDriver();
		Login();
		Search("ϰ��ƽ");
	}
	
	public static void Login(){
		// ����ʵ��
		dr.get("https://www.facebook.com/");
		// ��һ�ַ��ʷ�ʽ
		// driver.navigate().to("http://www.google.com");
		
		//�ȴ�Ԫ�س���
		WebDriverWait wait = new WebDriverWait(dr, 10);  
		wait.until(ExpectedConditions.elementToBeClickable(By.id("u_0_l")));  

		// ע���
		WebElement lastname = dr.findElement(By.name("email"));
		lastname.sendKeys(email);
		WebElement passw = dr.findElement(By.name("pass"));
		passw.sendKeys(pass);
		// �ύ����WebDriver���Զ����ұ����ύ
		passw.submit();
		// ��ʾ��ѯ���title
		System.out.println("��¼�ɹ�--" + dr.getTitle());
	}
	
	//��ѯ�˺���Ϣ
	public static void Search(String SearchKey){
		System.out.println("�����ѯ");
		//dr.get("https://www.facebook.com/?sk=welcome");  
//      dr.navigate().to(BASE_URL);//��dr.get()Ч����ͬ  
//      dr.navigate().forward();//�������ǰ��һ��  
//      dr.navigate().back();//�����������һ��  
 //    Log.info(dr.getPageSource());
		
		//���أ��ȴ�����
		WebDriverWait wait = new WebDriverWait(dr, 5);  
		wait.until(ExpectedConditions.elementToBeClickable(By.className("navLink"))); 
		//Ѱ��������
		WebElement element= dr.findElement(By.id("q"));
		element.sendKeys(SearchKey);
		//�Զ�Ѱ���ύ������Ѱ�ҵ�����ύ
		element.submit();
		//dr.get("https://www.facebook.com/search/results/?q="+SearchKey);
		wait = new WebDriverWait(dr, 5);  
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_1yt"))); 
		WebElement results=dr.findElement(By.className("_1yt"));
		List<WebElement> re= results.findElements(By.className("_gll"));
		for(WebElement we:re){
			//��ȡ�˻���Ϣ
			System.out.println(we.findElement(By.tagName("a")).getAttribute("href").toString());
		}
		
		dr.get(re.get(re.size()-6).findElement(By.tagName("a")).getAttribute("href").toString());
		
		//�ȴ�����
		System.out.println("���ڲ���Report����");
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("fbPageTimelineActionSelector")));
		WebElement sE=dr.findElement(By.id("fbPageTimelineActionSelector"));
		sE.click();
		WebElement sElement=dr.findElement(By.id("u_0_1p"));
		List<WebElement> reports= sElement.findElements(By.tagName("li"));
		//����Ajax��������
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
		System.out.println("�������");
	}
}
