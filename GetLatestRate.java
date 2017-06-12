/**
 * Created by chentao on 17/4/8.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class GetLatestRate
{
    public String ratetime;
    public String ratehuilv;
    public boolean warn;
    public void getrate(){
        warn=true;
        // 定义即将访问的链接
        String url = "http://www.huilv.cc/";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try
        {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null)
            {
                if (line.contains("美元<span class=\"huilv_gray\">兑换</span><span class=\"huilv_pink\">"))
                {
                    ratehuilv=line.substring(130,136);
                }
                if (line.contains("<div class=\"topleft\">本站汇率更新时间: <span id=\"topupdatetime\">"))
                {
                    ratetime=line.substring(56,75);
                }
            }
        } catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            warn=false;
            e.printStackTrace();
        } // 使用finally来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }
}
