package com.iie.clutter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.iie.utils.*;

public class CopyOfFaceBookDemo {
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
			if (i > 5)
				Report(s);
		}
		dr.quit();
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
		// dr.get(re.get(1).findElement(By.tagName("a")).getAttribute("href").toString());

		// dr.get(re.get(re.size()-7).findElement(By.tagName("a")).getAttribute("href").toString());
		// ����С��
		arrayList.add("https://www.facebook.com/groups/1406130876314875");
		// ������ҳ

		// ��ҳ
		arrayList.add("https://www.facebook.com/SWETTCHINA");

		return arrayList;
		// �ȴ�����

	}

	private static void Report(String url) {
		// TODO Auto-generated method stub
		System.out.println("���ڲ���Report����");

		dr.get(url);
		WebDriverWait wait = new WebDriverWait(dr, 5);

		if (utils.ElementExist(dr, By.xpath("//div[@class='uiToggle wrap']"))) {
			// ���������ҳ
			System.out.println("������ҳ��ת�������ҳ����ʽ");
			wait = new WebDriverWait(dr, 5);
			System.out.println("��������С����߾�ݼ����ǵľٱ���ʽ��չ����1|6|8��ѡ�񣬺��ٱ�");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='uiToggle wrap']"));

			// ����ٱ�
			// String temp1=temp.substring(temp.length()-2, temp.length());
			// String temp=temp.substring(0,
			// temp.length()-2)+String.valueOf(Integer.valueOf(temp1)+1);
			// String temp =
			// div.findElement(By.tagName("a")).getAttribute("id").toString();
			// //ת������ΪidΪ��1
			// String temp1 = temp.substring(temp.length() - 1, temp.length());
			// char[] ct =temp1.toCharArray();
			// temp = temp.substring(0, temp.length() - 1)+(char)(ct[0]-1);
			div.click();
			WebElement sElement = div.findElement(By
					.xpath("//ul[@class='uiMenuInner']"));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 1) { // ��Ϊ���
				// ����Ajax��������
				wait = new WebDriverWait(dr, 5);
				reports.get(0).click();
				// �ҵ��ٱ������
				wait = new WebDriverWait(dr, 5);
				// <DIV>Ϊ"��С���Ƿ�������ĺ������ɧ�ţ�"�Ĵ������
				System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='file_report']")));
				WebElement li = dr.findElement(By
						.xpath("//input[@value='file_report']"));
				li.click();
				wait = new WebDriverWait(dr, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='1']")));
				WebElement in = dr.findElement(By.xpath("//input[@value='1']"));
				in.click();

				in.submit();
				// WebElement button;
				// wait = new WebDriverWait(dr, 2);
				// button =
				// dr.findElement(By.xpath("//input[@value='file_report']"));
				// button.click();
				//
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
				// List<WebElement> reports =
				// sElement.findElements(By.tagName("li"));
				// ����Ajax��������
				// reports.get(4).click();

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
				// WebElement button =
				// dr.findElement(By.xpath("//input[@value='����']"));
				// button.click();
				// wait = new WebDriverWait(dr, 2);
				wait = new WebDriverWait(dr, 5);
				if (utils
						.ElementExist(
								dr,
								By.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
					WebElement button = dr
							.findElement(By
									.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
					button.click();
				}
				// wait = new WebDriverWait(dr, 2);
				// WebElement button;// =
				// dr.findElement(By.xpath("//input[@value='����']"));
				// button.click();
				// wait = new WebDriverWait(dr, 2);
				// button = dr.findElement(By.xpath("//a[@role='button']"));
				// button.click();
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

			// ����ٱ�
			// String temp1=temp.substring(temp.length()-2, temp.length());
			// String temp=temp.substring(0,
			// temp.length()-2)+String.valueOf(Integer.valueOf(temp1)+1);
			String temp = div.findElement(By.tagName("a")).getAttribute("id")
					.toString();
			// ת������ΪidΪ��1
			String temp1 = temp.substring(temp.length() - 1, temp.length());
			char[] ct = temp1.toCharArray();
			temp = temp.substring(0, temp.length() - 1) + (char) (ct[0] - 1);
			div.click();
			WebElement sElement = dr.findElement(By.id(temp));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 2) { // ��Ϊ���
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
							By.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
				WebElement button = dr
						.findElement(By
								.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
				button.click();
			}

		} else {// if (until.ElementExist(dr,
				// By.id("fbPageTimelineActionSelector"))) {
			// System.out.println("Ϊ������ҳ��ת�򹫹���ҳ����ʽ");
			// wait.until(ExpectedConditions.elementToBeClickable(By
			// .id("fbPageTimelineActionSelector")));
			// WebElement sE = dr.findElement(By
			// .id("fbPageTimelineActionSelector"));
			// sE.click();
			// WebElement sElement =
			// dr.findElement(By.id("fbpage_report_action"));
			// sElement.click();
			// //List<WebElement> reports =
			// sElement.findElements(By.tagName("li"));
			// // ����Ajax��������
			// //reports.get(4).click();
			//
			// wait = new WebDriverWait(dr, 5);
			// // <DIV>Ϊ"�ٱ�רҳ��ԭ���ǣ�"�Ĵ������
			// System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='spam']")));
			// WebElement input =
			// dr.findElement(By.xpath("//input[@value='spam']"));
			// input.click();
			// wait = new WebDriverWait(dr, 5);
			// input.submit();
			// //wait = new WebDriverWait(dr, 2);
			// WebElement button;// =
			// dr.findElement(By.xpath("//input[@value='����']"));
			// //button.click();
			// //wait = new WebDriverWait(dr, 2);
			// button = dr.findElement(By.xpath("//a[@role='button']"));
			// button.click();
			// //wait = new WebDriverWait(dr, 2);
			// //WebElement button;// =
			// dr.findElement(By.xpath("//input[@value='����']"));
			// //button.click();
			// //wait = new WebDriverWait(dr, 2);
			// //button = dr.findElement(By.xpath("//a[@role='button']"));
			// //button.click();
			//
			// } else {
			System.out.println("Ϊ������ҳ��ת�������ҳ����ʽ");

			wait = new WebDriverWait(dr, 5);
			// if (until
			// .ElementExist(
			// dr,
			// By.xpath("//span[@class='lastItem uiButtonGroupItem selectorItem']")))
			// {
			// // �����ݺ�����״�ľٱ���ʽ��չ����6��ѡ�񣬺��ٱ�
			// System.out.println("�����ݺ�����״�ľٱ���ʽ��չ����6��ѡ�񣬺��ٱ�");
			// WebElement div = dr.findElement(By
			// .xpath("//div[@class='uiToggle wrap']"));
			// // String temp=div.getAttribute("id").toString();
			// // String temp1=temp.substring(temp.length()-2, temp.length());
			// // temp=temp.substring(0,
			// // temp.length()-2)+String.valueOf(Integer.valueOf(temp1)+1);
			// // WebElement sElement = dr.findElement(By.id(temp));
			// // sElement.click();
			// div.click();
			// // ����ٱ�
			// // String temp1=temp.substring(temp.length()-2, temp.length());
			// // String temp=temp.substring(0,
			// // temp.length()-2)+String.valueOf(Integer.valueOf(temp1)+1);
			// WebElement sElement = dr.findElement(By
			// .xpath("//ul[@class='uiMenuInner']"));
			// List<WebElement> reports = sElement.findElements(By
			// .tagName("li"));
			// if (reports.size() == 6) { // ��Ϊ���
			// // ����Ajax��������
			// wait = new WebDriverWait(dr, 5);
			// reports.get(2).click();
			// // �ҵ��ٱ������
			// wait = new WebDriverWait(dr, 5);
			// // <DIV>Ϊ"�ٱ���רҳ"�Ĵ������
			// System.out.println("�ҵ��ٱ��򣬾ٱ�Ϊ����������թƭ��");
			// wait.until(ExpectedConditions.elementToBeClickable(By
			// .id("report_type_9")));
			// WebElement li = dr.findElement(By.id("report_type_9"));
			// li.click();
			// WebElement button = dr.findElement(By
			// .xpath("//input[@value='�ύ']"));
			// button.click();
			// wait = new WebDriverWait(dr, 2);
			// button = dr.findElement(By.xpath("//input[@value='ȷ��']"));
			// button.click();
			//
			// } else {
			// wait = new WebDriverWait(dr, 5);
			// reports.get(0).click();
			// }
			// } else {
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
			System.out.println(temp);
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			// if (reports.size() == 5) // ��Ϊ���
			// ����Ajax��������
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
			// wait = new WebDriverWait(dr, 2);
			// button = dr.findElement(By.xpath("//a[@role='button']"));
			// button.click();
			wait = new WebDriverWait(dr, 5);
			if (utils
					.ElementExist(
							dr,
							By.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"))) {
				WebElement button = dr
						.findElement(By
								.xpath("//div[@class='pam uiOverlayFooter uiBoxGray topborder']"));
				button.click();
			}
			// }

		}
		//
		// // �ҵ��ٱ������
		// wait = new WebDriverWait(dr, 5);
		// // ��λ����<li>��ǩ��Ѱ�ҡ�������һ��
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li")));
		// List<WebElement> lis = dr.findElements(By.xpath("//label"));
		// for (WebElement li : lis) {
		// System.out.println(li.getText().toString());
		// if (li.getText().toString().startsWith("����")) {
		// li.click();
		// li.submit();
		// }
		// }
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='_1v5 rfloat _ohf']")));
		// WebElement div=
		// dr.findElement(By.xpath("//div[@class='_1v5 rfloat _ohf']"));
		//
		// //
		// System.out.println(div.findElement(By.tagName("div")).getAttribute("id").toString());
		// String
		// temp=div.findElement(By.tagName("div")).getAttribute("id").toString();
		// // �ı�������֣�����ѡ��ٱ�����
		// temp=temp.substring(0, temp.length()-1)+"4";
		// wait.until(ExpectedConditions.elementToBeClickable(By.id(temp)));
		// WebElement garbage = dr.findElement(By.id(temp));
		// garbage.click();
		// garbage.submit();

		// wait = new WebDriverWait(dr, 5);
		// wait.until(ExpectedConditions.elementToBeClickable(By
		// .id("report_type_9")));
		// // �ı����֣������ٴ�ѡ��ٱ�����
		// WebElement report_type = dr.findElement(By.id("report_type_9"));
		// report_type.click();
		// report_type.submit();
		// �Ѿ�������ϣ���ʱ�ò������������û�н���
		// wait = new WebDriverWait(dr, 5);
		// wait.until(ExpectedConditions.elementToBeClickable(By.className("_14")));
		// List<WebElement> finall = dr.findElements(By.className("_14"));
		// for (WebElement we : finall) {
		// System.out.println(we.findElement(By.tagName("a"))
		// .getAttribute("href").toString());
		// if (we.findElement(By.tagName("a"))
		// .getAttribute("href")
		// .toString()
		// .equals("https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1471753823066289?ref=br_rs#"))
		// we.click();
		// }
		System.out.println("�������");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
