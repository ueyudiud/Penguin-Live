package com.tieba.onsn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Onsn on 2016/10/30.
 *
 * @author OnSN
 */
public class NumOfFiles {
    public static File newestFile (File dir) {
        //Create two List.
        List<File> fileList = new ArrayList<>();
        List<Long> timeList = new ArrayList<>();

        //Get all the files in a Dir.
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                //Add file in a List.
                fileList.add(file);
                timeList.add(file.lastModified());
            }
        } else {
            return null;
        }
        //Get max.
        long timeMax = Collections.max(timeList);
        //Return.
        return fileList.get(timeList.indexOf(timeMax));
    }
    public static File oldestFile (File dir) {
        //Create two List.
        List<File> fileList = new ArrayList<>();
        List<Long> timeList = new ArrayList<>();

        //Get all the files in a Dir.
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                //Add file in a List.
                fileList.add(file);
                timeList.add(file.lastModified());
            }
        } else {
            return null;
        }
        //Get max.
        long timeMax = Collections.min(timeList);
        //Return.
        return fileList.get(timeList.indexOf(timeMax));
    }
}
