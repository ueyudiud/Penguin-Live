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
    private Map<String, String> settings = new HashMap<>();
    private File settingsINI = new File("settings.ini");

    Settings () {
        if (!settingsINI.isFile()) {
            try {
                settingsINI.createNewFile();
                try (BufferedWriter bw = getWriter()) {
                    if (bw != null) {
                        bw.write("BDUSS = \n" +
                                "page = \n" +
                                "screenShot = \n" +
                                "hotKey = 113");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        readAll();
    }
    private BufferedWriter getWriter () {
        try {
            return new BufferedWriter(new FileWriter(settingsINI));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private BufferedReader getReader () {
        try {
            return new BufferedReader(new FileReader(settingsINI));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void readAll () {
        String tmp1;
        try (BufferedReader br = getReader()) {
            if (br != null) {
                while ((tmp1 = br.readLine()) != null) {
                    String[] tmp2 = tmp1.split("=");
                    settings.put(tmp2[0].replace(" ", ""), tmp2[1].replace(" ", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAll () {
        try (BufferedWriter bw = getWriter()) {
            if (bw != null) {
                bw.write("BDUSS = " + settings.get("BDUSS") +
                        "\npage = " + settings.get("page") +
                        "\nscreenShot = " + settings.get("screenShot") +
                        "\nhotKey = " + settings.get("hotKey"));
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
            settings.put(key, value);
        }
    }
    public String getSettings (String key) {
        if (key.equals("BDUSS") || key.equals("page") || key.equals("screenShot") || key.equals("hotKey")) {
            return settings.get(key);
        }
        return null;
    }
}
