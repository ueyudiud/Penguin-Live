package com.tieba.onsn;


import com.tieba.onsn.regex.Regex;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Onsn on 2016/10/21.
 *
 * @author OnSN
 */
class Post {
    private static String bduss = "9aUkpVTHVzTm8xR3BlWTBYSkpESVhSeEtPa2UwRWFuc05NMEp6ZDhqZFZ2RE5ZSVFBQUFBJCQAAAAAAAAAAAEAAAA4BI5ZbWO5pNK1MrDJAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFUvDFhVLwxYbW";
    private CookieStore cookieStore = new BasicCookieStore();
    private String URL;

    Post (String tieURL, String bduss) {
        Post.bduss = bduss;
        URL = tieURL;

    }

    void postContents(String contents) throws IOException {
        HttpResponse httpResponse;

        httpResponse = httpGetWithHeader(URL, (new BasicHeader("Cookie", "BDRCVFR[k2U9xfnuVt6]=mk3SLVN4HKm; " +
                "H_PS_PSSID=1442_18241_21119_21406_21377_21189_21372_20929; " +
                "BAIDUID=07AD2B471D734265B6425FA662698A97:FG=1; " +
                "BDUSS=" + bduss + "; " +
                "TIEBAUID=251e382d4053b3698f036ce3; " +
                "TIEBA_USERTYPE=7c2a31589a0e6889c8f7d37a; " +
                "LONGID=339081636")));
        System.out.println("<<查看是否登录成功>>" + httpResponse.getStatusLine());
        String result = sourceGet(httpResponse.getEntity(), -1);
        System.out.println("结果： \n" + result);
        String kw;
        String fid;
        String tid;
        String tbs;
        {
            kw = (new Regex(result, "fname=\"(.*?)\"").group(1));
            System.out.println("kw = " + kw);
            tid = (new Regex(URL, "(http://tieba.baidu.com/p/)(\\d*).*")).group(2);
            System.out.println("tid = " + tid);
            fid = (new Regex(result, "fid:'(\\d*?)'").group(1));
            System.out.println("fid = " + fid);
            tbs = (new Regex(result, "\"tbs\": \"(.{20,30})\"").group(1));
            System.out.println("tbs = " + tbs);
        }

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
            params.add(new BasicNameValuePair("content", contents));
            params.add(new BasicNameValuePair("files", "%5B%5D"));
            params.add(new BasicNameValuePair("mouse_pwd", "109%2C103%2C120%2C96%2C103%2C101%2C103%2C93%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C120%2C101%2C120%2C100%2C93%2C101%2C98%2C101%2C100%2C98%2C93%2C101%2C109%2C102%2C100%2C120%2C101%2C100%2C108%2C100%2C14772177721840"));
            params.add(new BasicNameValuePair("mouse_pwd_t", "1477217772184"));
            params.add(new BasicNameValuePair("mouse_pwd_isclick", "0"));
            params.add(new BasicNameValuePair("__type__", "reply"));
        }

        httpResponse = httpPostWithCookieAndHeader("http://tieba.baidu.com/f/commit/post/add", params, (new BasicHeader("Cookie", "TIEBA_USERTYPE=8e3276f7e65b63f120f52155; b" +
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
                "LONGID=339081636")));

        System.out.println("<<发贴>>" + httpResponse.getStatusLine());
        System.out.println("结果： " + sourceGet(httpResponse.getEntity(), -1));
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


}
