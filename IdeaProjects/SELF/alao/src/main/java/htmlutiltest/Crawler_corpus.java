
package htmlutiltest;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class Crawler_corpus {
    @Test
    public void test() throws IOException {
        //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = null;
        
        
        for (int a = 1; a < 5; a++) {
        	for (int b = 1; b < 9; b++) {
        		for (int c = 1; c < 10; c++) {
        			for (int d = 1; d < 10; d++) {
        				
        				try {
        		            for (int i = 0; i < 140; i++) {

        		            }
        		            page = webClient.getPage("http://www.aihanyu.org/api/v2/topic_detail.aspx?info="+a+"_"+b+"_"+c+"&start="+d+"&size=1");
        		            System.out.println(a);
        		            System.out.println(b);
        		            System.out.println(c);
        		            System.out.println(d);
        		        
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
        		        
        		        //if (int loc = contenu.indexOf("HSKstats"); loc!=-1) {
        		        	
        		        //}
        		        int length=contenu.length();
        		        
        		        if(length>20){
        		        	//删除关键词之后的内容 
            		        int loc = contenu.indexOf("HSKstats");
            		        String newStr = contenu.substring(0,loc);
            		        
            		        
            		        //剔除不需要的内容
            		        String cms =newStr.replace("{\"data\":[{\"Sens\":[", "").replace("\"come\":", "").replace("\"","").replace("]","");
            		        String[] countryStr = cms.split(",");
            		        
            		        
            		        //将读取的内容写入新文档
            		        
            		        String line="";		
            		        for(int i = 0;i < countryStr.length ; i++){
            		        	System.out.println(countryStr[i]);
            		        	if (countryStr[i]!=null) {
            		        		FileUtils.writeStringToFile(new File("test.txt"),countryStr[i]+ "\r\n",true);
                		        	line = countryStr[i];
            		        	}
            		        	
            		        }
            		        //System.out.println(line);
            		        
            		        
            		        //将读取文件最后一行改为新文档标题并覆盖旧文档
            		        //int length1=line.length();
            		        //System.out.println(length1);
            		        //if(length1>5){
            		        	
            		        
            		        try {//你要监视的程式码放在 try 区块里即可。在 try 区块之后紧接著在 catch 子句里指定你希望捕捉的例外型态
            		        	FileUtils.moveFile(
            		        			FileUtils.getFile("test.txt"), 
                		        		FileUtils.getFile(line+".txt"));
            		         }
            		         catch(IOException e) //如果上面的代码有错误，这里就捕获
            		         {} ;//错误后不进行操作
            		         
            		         
            		         
            		         
            		         //}
            		        	
        		        }
        		        
        			}
        			}
        		}
        	}
        }
}




