package com.tieba.onsn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.tieba.onsn.PenguinLive.log;

/**
 * Created by Onsn on 2016/10/28.
 *
 * @author OnSN
 */
public class NumberOfFiles {
    public static File oldestFile (File dir) {
        List<String> array = new ArrayList<>();
        List<Long> timeArray = new ArrayList<>();
        File[] list = dir.listFiles();
        for (File f:
             list) {
            array.add(String.valueOf(f.lastModified()));
            array.add(f.getName());
        }
        for (int i = 1; i < array.size()/2; i++) {
            timeArray.add(Long.parseLong(array.get(i%2 == 1 ? i-1 : i)));
        }
        long l = Collections.min(timeArray);
        String s = array.get(array.indexOf(String.valueOf(l))+1);
        return new File(dir, s);
    }
    public static File newestFile (File dir) {
        List<String> array = new ArrayList<>();
        List<Long> timeArray = new ArrayList<>();
        File[] list = dir.listFiles();
        for (File f:
                list) {
            array.add(String.valueOf(f.lastModified()));
            array.add(f.getName());
        }
        for (int i = 1; i < array.size()/2; i++) {
            timeArray.add(Long.parseLong(array.get(i%2 == 1 ? i-1 : i)));
        }
        long l = Collections.max(timeArray);
        String s = array.get(array.indexOf(String.valueOf(l))+1);
        return new File(dir, s);
    }
}
