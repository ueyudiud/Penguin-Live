package com.tieba.onsn;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Onsn on 2016/10/27.
 *
 * @author OnSN
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class Settings {
    public static final Settings settings = new Settings();
    private Map<String, String> stringHashMap = new HashMap<>();
    private File settingsINI = new File("settings.ini");

    Settings() {
        if (!settingsINI.isFile())
            try {
            boolean success = settingsINI.createNewFile();
            if (!success) {
                throw new IOException();
            }
            try (BufferedWriter bw = getWriter()) {
                if (bw != null) {
                    bw.write("BDUSS=\n" +
                            "page=\n" +
                            "screenShot=\n" +
                            "hotKey=113");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        readAll();
        settingCorrect();
    }
    private void settingCorrect() {
        readAll();
        if (!new File(getSettings("screenShot")).isDirectory()) {
            setSettings("screenShot", "");
        }
        writeAll();
    }

    private BufferedWriter getWriter() {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(settingsINI), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private BufferedReader getReader() {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(settingsINI), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void readAll() {
        String tmp1;
        try (BufferedReader br = getReader()) {
            if (br != null) {
                while ((tmp1 = br.readLine()) != null) {
                    String[] tmp2 = tmp1.split("=");
                    if (tmp2.length == 1) {
                        stringHashMap.put(tmp2[0].replace(" ", ""), "");
                    } else {
                        stringHashMap.put(tmp2[0].replace(" ", ""), tmp2[1].replace(" ", ""));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAll() {
        try (BufferedWriter bw = getWriter()) {
            if (bw != null) {
                bw.write("BDUSS = " + stringHashMap.get("BDUSS") +
                        "\npage = " + stringHashMap.get("page") +
                        "\nscreenShot = " + stringHashMap.get("screenShot") +
                        "\nhotKey = " + stringHashMap.get("hotKey"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key "BDUSS" or "page" or "screenShot" or "hotKey".
     * @param value Anything.
     */
    public void setSettings (String key, String value) {
        if (key.equals("BDUSS") || key.equals("page") || key.equals("screenShot") || key.equals("hotKey")) {
            stringHashMap.put(key, value);
        }
    }
    public String getSettings (String key) {
        if (key.equals("BDUSS") || key.equals("page") || key.equals("screenShot") || key.equals("hotKey")) {
            return stringHashMap.get(key);
        }
        return null;
    }
}
