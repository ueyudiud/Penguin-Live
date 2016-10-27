package com.tieba.onsn;

import com.tieba.onsn.regex.Regex;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Onsn on 2016/10/27.
 *
 * @author OnSN
 */
public class Settings {
    private File ini = new File("settings.ini");
    private BufferedReader br;
    private BufferedWriter bw;

    private Map<String, String> settings = new HashMap<>();

    Settings () {
        try {
            if (!ini.isFile()) {
                ini.createNewFile();
            }
            if (settings.isEmpty()) {
                settings.put("BDUSS", null);
                settings.put("page", null);
                settings.put("screenShot", null);
                settings.put("hotKey", "113");
            }

            br = new BufferedReader(new FileReader(ini));
            bw = new BufferedWriter(new FileWriter(ini));

            if (!ini.isFile()) {
                bw.write(
                        "BDUSS = "+
                        "\npage =" +
                        "\nscreenShot = "+
                        "\nhotKey = 113");
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh () {
        write();
        read();
    }
    public void write () {
        String s1 = settings.get("BDUSS");
        String s2 = settings.get("page");
        String s3 = settings.get("screenShot");
        String s4 = settings.get("hotKey");

        try {
            bw.write(
                            "BDUSS = "+s1+
                            "\npage =" +s2+
                            "\nscreenShot = "+s3+
                            "\nhotKey = "+s4);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void read () {
        Pattern p = Pattern.compile("(.*) = (.*)");
        Matcher r;
        String temp;
        String s1;
        String s2;
        try {
            while ((temp = br.readLine()) != null) {
                String[] s = temp.split("=");
                settings.put(s[0].replace(" ", ""), s[1].replace(" ", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBDUSS (String s) {
        settings.put("BDUSS", s);
    }
    public void setPage (String s) {
        settings.put("page", s);
    }
    public void setScreenShot (String s) {
        settings.put("screenShot", s);
    }
    public void setHotKey (String s) {
        settings.put("hotKey", s);
    }

    public String getBDUSS () {
        return settings.get("BDUSS");
    }
    public String getPage () {
        return settings.get("page");
    }
    public String getScreenShot () {
        return settings.get("screenShot");
    }
    public String getHotkey () {
        return settings.get("hotKey");
    }

}
