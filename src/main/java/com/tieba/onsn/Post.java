package com.tieba.onsn;


import com.tieba.onsn.regex.Regex;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Onsn on 2016/10/21.
 *
 * @author OnSN
 */
public class Post implements Runnable {
    private CookieStore cookieStore = new BasicCookieStore();
    private File image;
    private String bduss;
    private String contents;
    private String URL;

    public Post (String contents, String tieURL, String bduss, File image) {
        this.contents = contents;
        this.bduss = bduss;
        this.URL = tieURL;
        this.image = image;
    }
    private void postContents() {
        HttpResponse httpResponse;

        httpResponse = httpGetWithHeader(URL, (new BasicHeader("Cookie", "BDRCVFR[k2U9xfnuVt6]=mk3SLVN4HKm; " +
                "H_PS_PSSID=1442_18241_21119_21406_21377_21189_21372_20929; " +
                "BAIDUID=07AD2B471D734265B6425FA662698A97:FG=1; " +
                "BDUSS=" + bduss + "; " +
                "TIEBAUID=251e382d4053b3698f036ce3; " +
                "TIEBA_USERTYPE=7c2a31589a0e6889c8f7d37a; " +
                "LONGID=339081636")));
        String result = sourceGet(httpResponse.getEntity(), -1);
        Log.log.addLog("Logged in: " + result.substring(result.indexOf("is_login")+11, result.indexOf("is_login")+12).equals("1"));
        String kw, tid, fid, tbs;
        {
            kw = (new Regex(result, "fname=\"(.*?)\"").group(1));
            Log.log.addLog("kw is: " + kw);
            tid = (new Regex(URL, "(http://tieba.baidu.com/p/)(\\d*).*")).group(2);
            Log.log.addLog("tid is: " + tid);
            fid = (new Regex(result, "fid:'(\\d*?)'").group(1));
            Log.log.addLog("fid is: " + fid);
            tbs = (new Regex(result, "\"tbs\": \"(.{20,30})\"").group(1));
            Log.log.addLog("tbs is: " + tbs);
        }

        String imageResult = postImage(this.image);
        String image = new Regex(imageResult, "pic_water\":\"(.*?.jpg)\"").group(1).replaceAll("\\\\", "");
        String type = new Regex(imageResult, "\"pic_type\":(.)").group(1);
        String width = Integer.valueOf(new Regex(imageResult, "\"fullpic_width\":(.*?),").group(1)) >= 560 ? "560" : new Regex(imageResult, "\"fullpic_width\":(.*?),").group(1);
        String height = Integer.valueOf(new Regex(imageResult, "\"fullpic_height\":(.*?),").group(1)) >= 308 ? "308" : new Regex(imageResult, "\"fullpic_height\":(.*?),").group(1);

        List<NameValuePair> params = new ArrayList<>();
        {
            params.add(new BasicNameValuePair("ie", "utf-8"));
            params.add(new BasicNameValuePair("kw", kw));
            params.add(new BasicNameValuePair("fid", fid));
            params.add(new BasicNameValuePair("tid", tid));
            params.add(new BasicNameValuePair("vcode_md5", ""));
            params.add(new BasicNameValuePair("floor_num", "1"));
            params.add(new BasicNameValuePair("rich_text", "1"));
            params.add(new BasicNameValuePair("tbs", tbs));
            Log.log.addLog("Content is: " + contents);
            params.add(new BasicNameValuePair("content", "[img pic_type="+type+" width="+width+" height="+height+"]"+image+"[/img]" + "[br]" + contents));
            params.add(new BasicNameValuePair("files", "%5B%5D"));
            params.add(new BasicNameValuePair("mouse_pwd", "109%2C103%2C120%2C96%2C103%2C101%2C103%2C93%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C93%2C101%2C98%2C101%2C100%2C98%2C93%2C101%2C109%2C102%2C100%2C120%2C101%2C100%2C108%2C100%2C14772177721840"));
            params.add(new BasicNameValuePair("mouse_pwd_t", "1477217772184"));
            params.add(new BasicNameValuePair("mouse_pwd_isclick", "0"));
            params.add(new BasicNameValuePair("__type__", "reply"));
        }

        httpResponse = httpPostWithCookieAndHeader("http://tieba.baidu.com/f/commit/post/add", params,
                new BasicHeader("Cookie", "TIEBA_USERTYPE=8e3276f7e65b63f120f52155; b" +
                "dshare_firstime=1463635022332; " +
                "PSTM=1466347841; BIDUPSID=BA39DB75B2AC259ADFC3D8C9F92DE9A9; " +
                "rpln_guide=1; IS_NEW_USER=57f1b4eb5becbf60d194a2f8; " +
                "BAIDUID=3EE749D301BD2B7442573F1562EDD8E9:FG=1; " +
                "Hm_lvt_287705c8d9e2073d13275b18dbd746dc=1477132437,1477132561,1477133113,1477184326; " +
                "BDUSS=" + bduss + "; " +
                "TIEBAUID=251e382d4053b3698f036ce3; " +
                "PSINO=6; " +
                "H_PS_PSSID=21233_1465_21102_20239_21407_21378_21193_21373_21307; " +
                "wise_device=0; " +
                "LONGID=339081636"));

        Log.log.addLog("Post result: " + sourceGet(httpResponse.getEntity(), -1));
    }

    /**
     * @param url URL for Get.
     * @return Get HttpResponse.
     */
    private HttpResponse httpGetWithHeader(String url, Header... headers) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        for (Header header:headers) {
            httpGet.addHeader(header);
        }
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            cookieStore = (((AbstractHttpClient)httpClient).getCookieStore());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    /**
     * @param url URL for Post.
     * @param params The Params for Post.
     * @return Post HttpResponse.
     */
    private HttpResponse httpPostWithCookieAndHeader(String url, List<NameValuePair> params, Header... header) {
        HttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        HttpPost httpPost = new HttpPost(url);
        for (Header h:header) {
            httpPost.addHeader(h);
        }
        HttpResponse httpResponse = null;
        ((AbstractHttpClient)httpClient).setCookieStore(cookieStore);
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    /**
     * @param httpEntity Response Entity.
     * @param index Index of Line.
     * @return Return String.
     */
    private String sourceGet(HttpEntity httpEntity, int index) {
        try {
            //新建BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "utf-8"));
            //读取一行
            String temp = bufferedReader.readLine();
            //新建一个StringBuilder
            StringBuilder stringBuilder = new StringBuilder();
            //如果读取的一行不为null
            while (temp != null) {
                //添加读取的一行
                stringBuilder.append(temp).append("\n");
                //重新读取
                temp = bufferedReader.readLine();
            }
            //将StringBuilder转为String
            temp = stringBuilder.toString();
            if (index != -1) {
                //分割String
                String[] temps = temp.split("\n");

                return temps[index];
            } else {
                return temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String postImage(File imageFile) {
        HttpClient httpClient;
        String result = null;
        HttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://upload.tieba.baidu.com/upload/pic?tbs=5b3aefb18a0b594b014784231450125500_1&fid=304736&save_yun_album=1");
            httpPost.addHeader("Cookie", "TIEBA_USERTYPE=8e3276f7e65b63f120f52155; b" +
                    "dshare_firstime=1463635022332; " +
                    "PSTM=1466347841; BIDUPSID=BA39DB75B2AC259ADFC3D8C9F92DE9A9; " +
                    "rpln_guide=1; IS_NEW_USER=57f1b4eb5becbf60d194a2f8; " +
                    "BAIDUID=3EE749D301BD2B7442573F1562EDD8E9:FG=1; " +
                    "Hm_lvt_287705c8d9e2073d13275b18dbd746dc=1477132437,1477132561,1477133113,1477184326; " +
                    "BDUSS=" + bduss + "; " +
                    "TIEBAUID=251e382d4053b3698f036ce3; " +
                    "PSINO=6; " +
                    "H_PS_PSSID=21233_1465_21102_20239_21407_21378_21193_21373_21307; " +
                    "wise_device=0; " +
                    "LONGID=339081636");

            FileBody fileBody = new FileBody(imageFile);

            HttpEntity requestEntity = MultipartEntityBuilder.create()
                    .addPart("file", fileBody)
                    .build();

            httpPost.setEntity(requestEntity);
            httpResponse = httpClient.execute(httpPost);

            result = sourceGet(httpResponse.getEntity(), -1);
            Log.log.addLog("Image Post result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void run() {
        postContents();
    }

}
