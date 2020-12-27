package htmlutiltest;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class HttpUtilsTest2 {
    private static final String TEST_URL = "http://www.aihanyu.org/api/v2/topic_detail.aspx?info=1_1_1&start=1&size=1";
    @Test
    public void testGetHtmlPageResponse() {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            String htmlPageStr = httpUtils.getHtmlPageResponse(TEST_URL);
            //TODO
            System.out.println(htmlPageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetHtmlPageResponseAsDocument() {
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        try {
            Document document = httpUtils.getHtmlPageResponseAsDocument(TEST_URL);
            //TODO
            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
