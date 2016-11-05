package com.tieba.onsn;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Onsn on 2016/10/28.
 *
 * @author OnSN
 */
public class Log {
    public static final Log log = new Log();
    private File file;

    private Log() {
        try {
            if (!(file = new File("log")).isDirectory()) {
                boolean mkdirs = file.mkdirs();
                if (!mkdirs) {
                    throw new IOException("Can't create dir.");
                }
            } else {
                File[] files = file.listFiles();
                if ((files != null ? files.length : 0) >= 6) {
                    file = NumOfFiles.oldestFile(file);
                    boolean delete = file.delete();
                    if (!delete) {
                        throw new IOException("Can't delete a log.");
                    }
                }
            }
            file = new File("log/" + getTimeForFile() + "_log.log");
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new IOException("Can't create a log.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd ah:m:s");
        return formatter.format(LocalDateTime.now());
    }
    private String getTimeForFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd_k-m-s");
        return formatter.format(LocalDateTime.now());
    }

    public void addLog (String log) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
            String finalLog = getTime() + " [Log] " + log;
            br.write(finalLog + "\n");
            System.out.println(finalLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addErr (String log) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
            String finalLog = getTime() + " [Error] " + log;
            br.write(finalLog + "\n");
            System.err.println(finalLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
