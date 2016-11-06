package com.tieba.onsn;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.tieba.onsn.swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import static com.tieba.onsn.PenguinLiveDebug.log;
import java.io.IOException;


/**
 * Created by Onsn on 2016/10/21.
 *
 * @author OnSN
 */
public class PenguinLiveDebug {
    public static Log log = new Log();
    public static final Font YaHei = new Font("微软雅黑", Font.BOLD, 15);
    public static final Settings settings = new Settings();

    private MainFrame mainFrame = new MainFrame();

    private PenguinLiveDebug() throws IOException {
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        log.addLog("设置全局字体...");
        setUIFont();
        log.addLog("设置界面样式...");
        UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        log.addLog("设置界面样式完毕。");
        new PenguinLiveDebug().start();
    }

    private void start() {
        mainFrame.setVisible(true);
    }

    public void post(String type) {
        switch (type) {

        }
    }

    private static void setUIFont() {
        Font f = new Font("微软雅黑", Font.PLAIN, 18);
        log.addLog("设置全局字体：设置的字体为：" + f.getName());

        String names[] = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", f);
            log.addLog("设置全局字体：设置" + item + "中...");
        }
        log.addLog("设置全局字体：全局字体设置完毕。");
    }
}