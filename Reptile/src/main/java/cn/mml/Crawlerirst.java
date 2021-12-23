package cn.mml;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Crawlerirst {
    public static void main(String[] args) throws IOException {
        //1,打开浏览器，创建httpClient
        CloseableHttpClient chc = HttpClients.createDefault();

        //2输入网址
        HttpGet hg = new HttpGet("http://www.morningstar.cn/main/default.aspx");
        //3,按回车发起请求
        CloseableHttpResponse response= chc.execute(hg);
        //4,解析响应获取数据
        if(200 == response.getStatusLine().getStatusCode()){
            HttpEntity he =response.getEntity();
            String s =EntityUtils.toString(he,"utf8");
            System.out.println(s);
        }
    }
}
