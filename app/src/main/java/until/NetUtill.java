package until;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author：刘京源
 * @E-mail： 1179348728@qq.com
 * @Date： 2019/3/15 8:51
 * @Description：描述信息
 */
public class NetUtill {

    //panduan wangluo lianjie
    public  static  boolean isNetwork(Context context){
        if (context!=null){
            //获取网络管理类
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //信息类
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            //判断是否有网络
            if (networkInfo!=null){
                return networkInfo.isAvailable();
            }

        }
        return false;
    }

    //加载网络数据
    public  static String loadData(String url){

        HttpURLConnection connection=null;
        try {
            URL url1=new URL(url);
            //
            connection= (HttpURLConnection) url1.openConnection();
            //设置请求方式 连接超时 读取差事
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            //判断服务器是否正常响应
            if (connection.getResponseCode()==200){
                //获取服务器返回的数据流
                InputStream inputStream=connection.getInputStream();
                return inputStream2String(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;

    }

    //流转换字符串
    private  static  String inputStream2String(InputStream inputStream) throws IOException {
        return  new String(ByteStreams.toByteArray(inputStream));
    }



}
