package com.iie.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iie.utils.*;

public class FaceBookDemo {
	/**
	 * @param args
	 */
	private static String email = "yuyanyuwo@126.com";
	private static String pass = "yuyanyuface123";
	// ����ʵ��
	private static WebDriver dr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				".\\res\\chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		Login();
		ArrayList<String> arrayList = Search("ϰ��ƽ");
		int i = 0;
		for (String s : arrayList) {
			// ���þٱ���ʽ
			i++;
			if (i > 11)
				//try {
					Report("https://www.facebook.com/lgbtscenekidandproud2001");
					//Report("https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1443778882541617");
					//�쳣����ַ
					//Report(s);
				//} catch (Exception e) {
					// TODO: handle exception
			//		System.err.println("�쳣�׳���no success!record!");
			//	}
				
		}
		//dr.quit();
	}

	public static void Login() {
		// ����ʵ��
		dr.get("https://www.facebook.com/");
		// ��һ�ַ��ʷ�ʽ
		// driver.navigate().to("http://www.google.com");
		// �ȴ�Ԫ�س���
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

	// ��ѯ�˺���Ϣ
	public static ArrayList<String> Search(String SearchKey) {
		ArrayList<String> arrayList = new ArrayList<String>();
		;
		System.out.println("�����ѯ");
		// dr.get("https://www.facebook.com/");
		// dr.get("https://www.facebook.com/?sk=welcome");
		// dr.navigate().to(BASE_URL);//��dr.get()Ч����ͬ
		// dr.navigate().forward();//�������ǰ��һ��
		// dr.navigate().back();//�����������һ��
		// Log.info(dr.getPageSource());

		// ���أ��ȴ�����
		WebDriverWait wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.className("navLink")));
		// Ѱ��������
		WebElement element = dr.findElement(By.id("q"));
		element.sendKeys(SearchKey);
		// �Զ�Ѱ���ύ������Ѱ�ҵ�����ύ
		element.submit();
		// dr.get("https://www.facebook.com/search/results/?q="+SearchKey);
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_1yt")));
		WebElement results = dr.findElement(By.className("_1yt"));
		List<WebElement> re = results.findElements(By.className("_gll"));
		for (WebElement we : re) {
			// ��ȡ�˻���Ϣ
			System.out.println(we.findElement(By.tagName("a"))
					.getAttribute("href").toString());
			arrayList.add(we.findElement(By.tagName("a")).getAttribute("href")
					.toString());
		}
		// ����С��
		arrayList.add("https://www.facebook.com/groups/1406130876314875");
		// ������ҳ
		// ��ҳ
		arrayList.add("https://www.facebook.com/SWETTCHINA");
		
		//https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1515574165329336
		//https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1443778882541617
		
		return arrayList;
	}

	private static void Report(String url) {
		// TODO Auto-generated method stub
		System.out.println("���ڲ���Report����");
		dr.get(url);
		//�ȴ�����
		WebDriverWait wait = new WebDriverWait(dr, 5);
		if(utils.ElementExist(dr,By.xpath("//h2[@class='_4-dp']"))){
			//ҳ�治���ڣ�ɾ���ɹ�
			System.out.println("Pages is not exists,record!");
			return;
		}
			
		if (utils.ElementExist(dr, By.xpath("//div[@class='uiToggle wrap']"))) {
			// ���������ҳ
			System.out.println("������ҳ��ת�������ҳ����ʽ");
			wait = new WebDriverWait(dr, 5);
			System.out.println("��������С����߾�ݼ����ǵľٱ���ʽ��չ����1|6|8��ѡ�񣬺��ٱ�");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='uiToggle wrap']"));
			div.click();
			WebElement sElement = div.findElement(By
					.xpath("//ul[@class='uiMenuInner']"));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 1) {
				//Ϊ���˾ٱ�����ʱ��û�н���߼���ѡ������
				// ����Ajax��������
				wait = new WebDriverWait(dr, 5);
				reports.get(0).click();
				// �ҵ��ٱ������
				// <DIV>Ϊ"��������"�Ĵ������
				System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='file_report']")));
				WebElement ul = dr.findElement(By.xpath("//ul[@class='uiList _4kg _6-h _6-j _6-i']"));
				List<WebElement> li =ul.findElements(By.tagName("li"));
				li.get(1).click();
				wait = new WebDriverWait(dr, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='mts']")));
				List<WebElement> in = dr.findElements(By.xpath("//div[@class='mts']/div"));  //��δ���
				in.get(1).click();

				in.get(1).submit();
				ul = dr.findElement(By.xpath("//ul[@class='uiList _4kg _6-h _6-j _6-i']"));
				li =ul.findElements(By.tagName("li"));
				li.get(4).click();
				wait = new WebDriverWait(dr, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='mts']")));
				in = dr.findElements(By.xpath("//div[@class='mts']/div"));  //��δ���
				in.get(3).click();
				wait = new WebDriverWait(dr, 2);
				in.get(3).submit();
			} else if (reports.size() == 6) {
				wait = new WebDriverWait(dr, 5);
				reports.get(2).click();
				wait = new WebDriverWait(dr, 5);
				// <DIV>Ϊ"�ٱ���רҳ"�Ĵ������
				System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.id("report_type_9")));
				WebElement li = dr.findElement(By.id("report_type_9"));
				li.click();
				WebElement button = dr.findElement(By
						.xpath("//input[@value='�ύ']"));
				button.click();
				wait = new WebDriverWait(dr, 2);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='ȷ��']")));
				button = dr.findElement(By.xpath("//input[@value='ȷ��']"));
				button.click();
			} else if (reports.size() == 8) {
				WebElement report = dr.findElement(By
						.id("fbpage_report_action"));
				report.click();
				// ����Ajax��������
				wait = new WebDriverWait(dr, 5);
				// <DIV>Ϊ"�ٱ�רҳ��ԭ���ǣ�"�Ĵ������
				System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='spam']")));
				WebElement input = dr.findElement(By
						.xpath("//input[@value='spam']"));
				input.click();
				wait = new WebDriverWait(dr, 5);
				input.submit();
				wait = new WebDriverWait(dr, 5);
				if (utils
						.ElementExist(
								dr,
								By.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
					WebElement button = dr
							.findElement(By
									.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
					button.click();
					System.out.println("���ȷ���з�Ӧ");
				}
			}
		}

		// ������С��
		else if (utils.ElementExist(dr,
				By.xpath("//div[@class='_6a _6b uiPopover']"))) {
			System.out.println("����С�飬ת�򿪷�С�鴦��ʽ");
			wait = new WebDriverWait(dr, 5);
			System.out.println("�����ݵľٱ���ʽ��չ����2��ѡ�񣬺��ٱ�");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='_6a _6b uiPopover']"));
			String temp = div.findElement(By.tagName("a")).getAttribute("id")
					.toString();
			// ת������ΪidΪ��1
			String temp1 = temp.substring(temp.length() - 1, temp.length());
			char[] ct = temp1.toCharArray();
			temp = temp.substring(0, temp.length() - 1) + (char) (ct[0] - 1);
			div.click();
			WebElement sElement = dr.findElement(By.id(temp));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 2) {
				// ����Ajax��������
				wait = new WebDriverWait(dr, 5);
				reports.get(1).click();
			}
			// �ҵ��ٱ������
			wait = new WebDriverWait(dr, 5);
			// <DIV>Ϊ"��С���Ƿ�������ĺ������ɧ�ţ�"�Ĵ������
			System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.id("report_type_9")));
			WebElement li = dr.findElement(By.id("report_type_9"));
			li.click();
			li.submit();
			if (utils
					.ElementExist(
							dr,
							By.xpath("//div/div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
				WebElement button = dr
						.findElement(By
								.xpath("//div/div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
				button.click();
				System.out.println("���ȷ���з�Ӧ");
			}

		} else {
			System.out.println("Ϊ������ҳ��ת�������ҳ����ʽ");
			wait = new WebDriverWait(dr, 5);
			System.out.println("�����������ľٱ���ʽ��չ����5��ѡ�񣬺��ٱ�");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='_6a uiPopover']"));
			div.click();
			String temp = div.getAttribute("id").toString();
			// ת������ΪidΪ��1
			String temp1 = temp.substring(temp.length() - 1, temp.length());
			char[] ct = temp1.toCharArray();
			temp = temp.substring(0, temp.length() - 1) + (char) (ct[0] + 1);
			// + String.valueOf(Integer.valueOf(temp1) + 1);
			WebElement sElement = dr.findElement(By.id(temp));
			//System.out.println(temp);
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			reports.get(1).click();
			wait = new WebDriverWait(dr, 5);
			// <DIV>Ϊ"�ٱ�רҳ��ԭ���ǣ�"�Ĵ������
			System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//input[@value='spam']")));
			WebElement input = dr.findElement(By
					.xpath("//input[@value='spam']"));
			input.click();
			wait = new WebDriverWait(dr, 5);
			input.submit();
			// wait = new WebDriverWait(dr, 2);
			// WebElement button;// =
			// dr.findElement(By.xpath("//input[@value='����']"));
			// button.click();
			wait = new WebDriverWait(dr, 5);
			if (utils
					.ElementExist(
							dr,
							By.xpath("//div/div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
				WebElement button = dr
						.findElement(By
								.xpath("//div/div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
				button.click();
				System.out.println("���ȷ���з�Ӧ");
			}
		}
		System.out.println("�������");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
