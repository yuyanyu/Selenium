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
	// 创建实例
	private static WebDriver dr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				".\\res\\chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		Login();
		ArrayList<String> arrayList = Search("习近平");
		int i = 0;
		for (String s : arrayList) {
			// 调用举报方式
			i++;
			if (i > 11)
				//try {
					Report("https://www.facebook.com/lgbtscenekidandproud2001");
					//Report("https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1443778882541617");
					//异常的网址
					//Report(s);
				//} catch (Exception e) {
					// TODO: handle exception
			//		System.err.println("异常抛出，no success!record!");
			//	}
				
		}
		//dr.quit();
	}

	public static void Login() {
		// 创建实例
		dr.get("https://www.facebook.com/");
		// 另一种访问方式
		// driver.navigate().to("http://www.google.com");
		// 等待元素出现
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

	// 查询账号信息
	public static ArrayList<String> Search(String SearchKey) {
		ArrayList<String> arrayList = new ArrayList<String>();
		;
		System.out.println("进入查询");
		// dr.get("https://www.facebook.com/");
		// dr.get("https://www.facebook.com/?sk=welcome");
		// dr.navigate().to(BASE_URL);//与dr.get()效果相同
		// dr.navigate().forward();//让浏览器前进一步
		// dr.navigate().back();//让浏览器后退一步
		// Log.info(dr.getPageSource());

		// 加载，等待出现
		WebDriverWait wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.className("navLink")));
		// 寻找搜索框
		WebElement element = dr.findElement(By.id("q"));
		element.sendKeys(SearchKey);
		// 自动寻找提交，或者寻找点击后提交
		element.submit();
		// dr.get("https://www.facebook.com/search/results/?q="+SearchKey);
		wait = new WebDriverWait(dr, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_1yt")));
		WebElement results = dr.findElement(By.className("_1yt"));
		List<WebElement> re = results.findElements(By.className("_gll"));
		for (WebElement we : re) {
			// 获取账户信息
			System.out.println(we.findElement(By.tagName("a"))
					.getAttribute("href").toString());
			arrayList.add(we.findElement(By.tagName("a")).getAttribute("href")
					.toString());
		}
		// 开放小组
		arrayList.add("https://www.facebook.com/groups/1406130876314875");
		// 个人主页
		// 主页
		arrayList.add("https://www.facebook.com/SWETTCHINA");
		
		//https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1515574165329336
		//https://www.facebook.com/pages/%E4%B9%A0%E8%BF%91%E5%B9%B3/1443778882541617
		
		return arrayList;
	}

	private static void Report(String url) {
		// TODO Auto-generated method stub
		System.out.println("正在操作Report过程");
		dr.get(url);
		//等待加载
		WebDriverWait wait = new WebDriverWait(dr, 5);
		if(utils.ElementExist(dr,By.xpath("//h2[@class='_4-dp']"))){
			//页面不存在，删除成功
			System.out.println("Pages is not exists,record!");
			return;
		}
			
		if (utils.ElementExist(dr, By.xpath("//div[@class='uiToggle wrap']"))) {
			// 处理个人主页
			System.out.println("个人主页，转向个人主页处理方式");
			wait = new WebDriverWait(dr, 5);
			System.out.println("处理三个小点或者锯齿加三角的举报方式，展开有1|6|8个选择，含举报");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='uiToggle wrap']"));
			div.click();
			WebElement sElement = div.findElement(By
					.xpath("//ul[@class='uiMenuInner']"));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 1) {
				//为个人举报，暂时还没有解决逻辑单选框问题
				// 返回Ajax操作条框
				wait = new WebDriverWait(dr, 5);
				reports.get(0).click();
				// 找到举报处理框
				// <DIV>为"？？？？"的处理过程
				System.out.println("找到举报框，举报为‘垃圾或者诈骗’");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='file_report']")));
				WebElement ul = dr.findElement(By.xpath("//ul[@class='uiList _4kg _6-h _6-j _6-i']"));
				List<WebElement> li =ul.findElements(By.tagName("li"));
				li.get(1).click();
				wait = new WebDriverWait(dr, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='mts']")));
				List<WebElement> in = dr.findElements(By.xpath("//div[@class='mts']/div"));  //尚未解决
				in.get(1).click();

				in.get(1).submit();
				ul = dr.findElement(By.xpath("//ul[@class='uiList _4kg _6-h _6-j _6-i']"));
				li =ul.findElements(By.tagName("li"));
				li.get(4).click();
				wait = new WebDriverWait(dr, 5);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='mts']")));
				in = dr.findElements(By.xpath("//div[@class='mts']/div"));  //尚未解决
				in.get(3).click();
				wait = new WebDriverWait(dr, 2);
				in.get(3).submit();
			} else if (reports.size() == 6) {
				wait = new WebDriverWait(dr, 5);
				reports.get(2).click();
				wait = new WebDriverWait(dr, 5);
				// <DIV>为"举报此专页"的处理过程
				System.out.println("找到举报框，举报为‘垃圾或者诈骗’");
				wait.until(ExpectedConditions.elementToBeClickable(By
						.id("report_type_9")));
				WebElement li = dr.findElement(By.id("report_type_9"));
				li.click();
				WebElement button = dr.findElement(By
						.xpath("//input[@value='提交']"));
				button.click();
				wait = new WebDriverWait(dr, 2);
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//input[@value='确定']")));
				button = dr.findElement(By.xpath("//input[@value='确定']"));
				button.click();
			} else if (reports.size() == 8) {
				WebElement report = dr.findElement(By
						.id("fbpage_report_action"));
				report.click();
				// 返回Ajax操作条框
				wait = new WebDriverWait(dr, 5);
				// <DIV>为"举报专页的原因是？"的处理过程
				System.out.println("找到举报框，举报为‘垃圾或者诈骗’");
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
					System.out.println("点击确定有反应");
				}
			}
		}

		// 处理开放小组
		else if (utils.ElementExist(dr,
				By.xpath("//div[@class='_6a _6b uiPopover']"))) {
			System.out.println("开放小组，转向开放小组处理方式");
			wait = new WebDriverWait(dr, 5);
			System.out.println("处理锯齿的举报方式，展开有2个选择，含举报");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='_6a _6b uiPopover']"));
			String temp = div.findElement(By.tagName("a")).getAttribute("id")
					.toString();
			// 转换，因为id为减1
			String temp1 = temp.substring(temp.length() - 1, temp.length());
			char[] ct = temp1.toCharArray();
			temp = temp.substring(0, temp.length() - 1) + (char) (ct[0] - 1);
			div.click();
			WebElement sElement = dr.findElement(By.id(temp));
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			if (reports.size() == 2) {
				// 返回Ajax操作条框
				wait = new WebDriverWait(dr, 5);
				reports.get(1).click();
			}
			// 找到举报处理框
			wait = new WebDriverWait(dr, 5);
			// <DIV>为"此小组是否对你或你的好友造成骚扰？"的处理过程
			System.out.println("找到举报框，举报为‘垃圾或者诈骗’");
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
				System.out.println("点击确定有反应");
			}

		} else {
			System.out.println("为个人主页，转向个人主页处理方式");
			wait = new WebDriverWait(dr, 5);
			System.out.println("处理三个大点的举报方式，展开有5个选择，含举报");
			WebElement div = dr.findElement(By
					.xpath("//div[@class='_6a uiPopover']"));
			div.click();
			String temp = div.getAttribute("id").toString();
			// 转换，因为id为加1
			String temp1 = temp.substring(temp.length() - 1, temp.length());
			char[] ct = temp1.toCharArray();
			temp = temp.substring(0, temp.length() - 1) + (char) (ct[0] + 1);
			// + String.valueOf(Integer.valueOf(temp1) + 1);
			WebElement sElement = dr.findElement(By.id(temp));
			//System.out.println(temp);
			List<WebElement> reports = sElement.findElements(By.tagName("li"));
			reports.get(1).click();
			wait = new WebDriverWait(dr, 5);
			// <DIV>为"举报专页的原因是？"的处理过程
			System.out.println("找到举报框，举报为‘垃圾或者诈骗’");
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//input[@value='spam']")));
			WebElement input = dr.findElement(By
					.xpath("//input[@value='spam']"));
			input.click();
			wait = new WebDriverWait(dr, 5);
			input.submit();
			// wait = new WebDriverWait(dr, 2);
			// WebElement button;// =
			// dr.findElement(By.xpath("//input[@value='继续']"));
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
				System.out.println("点击确定有反应");
			}
		}
		System.out.println("操作完成");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
