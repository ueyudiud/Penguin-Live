package com.tieba.onsn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Onsn on 2016/10/28.
 *
 * @author OnSN
 */
public class Log {
    File file;
    BufferedWriter br;
    public Log () {
        if (!(file = new File("log")).isDirectory()) {
            file.mkdirs();
        } else {
            File[] files = file.listFiles();
            if ((files != null ? files.length : 0) >= 6) {
                file = NumberOfFiles.oldestFile(file);
                file.delete();
            }
        }
        file = new File("log/" + getTimeForFile() + "_log.log");
        try {
            System.out.println(file.getPath());
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addLog("加载LOG服务。");
    }

    private String getTime () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd ah:m:s");
        return formatter.format(LocalDateTime.now());
    }
    private String getTimeForFile () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd_k-m-s");
        return formatter.format(LocalDateTime.now());
    }

    public void addLog (String log) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
            String finalLog = getTime() + " [log] " + log;
            br.write(finalLog + "\n");
            System.out.println(finalLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
