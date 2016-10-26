<<<<<<< HEAD
package com.tieba.onsn;


import com.tieba.onsn.swing.MainFrame;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Onsn on 2016/10/21.
 *
 * @author OnSN
 */
public class PenguinLive {
    public static final Font YaHei = new Font("微软雅黑", Font.BOLD, 15);
    private MainFrame mainFrame = new MainFrame();

    public static void main(String[] args) {
        setUIFont();
        new PenguinLive().start();
    }
    public void start () {

        mainFrame.setVisible(true);
    }
    public void post (String type) {
        switch (type) {

        }
    }

    public static void setUIFont () {
        Font f = new Font("微软雅黑",Font.PLAIN,18);

        String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField","TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item+ ".font",f);
        }
    }
=======
package com.tieba.onsn;


import com.tieba.onsn.swing.MainFrame;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Onsn on 2016/10/21.
 *
 * @author OnSN
 */
public class PenguinLive {
    public static final Font YaHei = new Font("微软雅黑", Font.BOLD, 15);
    private MainFrame mainFrame = new MainFrame();

    public static void main(String[] args) {
        setUIFont();
        new PenguinLive().start();
    }
    public void start () {

        mainFrame.setVisible(true);
    }
    public void post (String type) {
        switch (type) {

        }
    }

    public static void setUIFont () {
        Font f = new Font("微软雅黑",Font.PLAIN,18);

        String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField","TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item+ ".font",f);
        }
    }
>>>>>>> origin/master
}