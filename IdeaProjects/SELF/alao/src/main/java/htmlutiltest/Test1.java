package htmlutiltest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class Test1  {
    @Test
    public void test() {
        //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = null;
        try {
            for (int i = 0; i < 140; i++) {

            }
            page = webClient.getPage("http://www.aihanyu.org/api/v2/topic_detail.aspx?info=1_1_1&start=1&size=1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束

        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串
        //System.out.println(pageXml);

        //TODO 下面的代码就是对字符串的操作了,常规的爬虫操作,用到了比较好用的Jsoup库

        Document document = Jsoup.parse(pageXml);//获取html文档
        //System.out.println(document);
        //String contenu = document.body(data.get("Sens")).text();
        
        String contenu = document.body().text();
        System.out.println(contenu);
        
        Map<String, String> map = new HashMap<String, String>();
        String str_json = contenu;
        
        //map= Map<String, Object> json2map(String str_json);
        
        System.out.println(map);
        //String [] temp;
        //temp = contenu.split(",");
        //for (int i = 0; i < 3; i++) {
        //System.out.println(temp[i]);
        //}
}
    
    
public class string2mapConvert{
	public Map<String, Object> json2map(String str_json) {
		Map<String, Object> res = null;
			try {
				Gson gson = new Gson();
				res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
					
				}.getType());
					} catch (JsonSyntaxException e) {
						
					}

            return res;
            
	}
	
    }
	static void json2map(Map res) {
		System.out.println(res);
	}
}

        
        
//*[@id="main-container"]/div/div[2]/div/div/div/div[1]/div
