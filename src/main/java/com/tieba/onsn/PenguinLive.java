package com.tieba.onsn;

import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.tieba.onsn.swing.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Onsn on 2016/10/29.
 *
 * @author OnSN
 */
public class PenguinLive {
    public static void main(String[] args) {
        new PenguinLive().start();
    }

    private void start() {

        Log.log.addLog("Log Service started.");
        setUI();
        /* **************** */
        Log.log.addLog("MainFrame Creating...");
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private void setUI() {
        Log.log.addLog("Set GUI Font.");
        {
            Font f = new Font("微软雅黑", Font.PLAIN, 18);
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
            }
        } //Set UI Font.
        Log.log.addLog("Set Look and Feel.");
        {
            try {
                LookAndFeel wclf = new WindowsClassicLookAndFeel();
                UIManager.setLookAndFeel(wclf);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        } //Set Look and Feel
    }
}
