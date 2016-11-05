package com.tieba.onsn.swing;

import com.tieba.onsn.NumOfFiles;
import com.tieba.onsn.PenguinLive;
import com.tieba.onsn.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * Created by Onsn on 2016/10/29.
 *
 * @author OnSN
 */
class ImagePanel extends JPanel {
    private URL imageFile;

    ImagePanel() {
        super();
        imageFile = newestFile();
    }
    public void paint(Graphics g) {
        Image paintImg = Toolkit.getDefaultToolkit().getImage(imageFile);
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(paintImg, 0, 0, 400, 225, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setFile() {
        imageFile = newestFile();
        repaint();
    }

    private URL newestFile() {
        URL tmpFile;
        try {
            File newestFile = new File(Settings.settings.getSettings("screenShot"));
            if (newestFile == null) {
                throw new Exception();
            }
            tmpFile = NumOfFiles.newestFile(newestFile).toURI().toURL();
        } catch (Exception e) {
            e.printStackTrace();
            tmpFile = PenguinLive.class.getResource("/img.png");
        }
        return tmpFile;
    }
}
