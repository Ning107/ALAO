package htmlutiltest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class test3 {
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
            page = webClient.getPage("http://www.aihanyu.org/api/v2/topic_detail.aspx?info=1_2_3&start=1&size=1");
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
        String contenu = document.body().text();
        //System.out.println(contenu);
        
        int loc = contenu.indexOf("HSKstats");//首先获取du字符的位置
        //然后调用字符串zhi截取
        String newStr = contenu.substring(0,loc);//再对字符串进行截取，获得dao想要得到的字符串
        
        

        String cms =newStr.replace("{\"data\":[{\"Sens\":[", "").replace("\"come\":", "").replace("\"","").replace("]","");
        String[] countryMapStr = cms.split(",");

        for(int i = 0; i < countryMapStr.length ; i++){
        	System.out.println(countryMapStr[i]);
        }
        }

    }


