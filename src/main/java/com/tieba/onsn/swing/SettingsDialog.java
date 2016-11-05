package com.tieba.onsn.swing;


import com.tieba.onsn.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


/**
 * Created by Onsn on 2016/10/25.
 *
 * @author OnSN
 */
class SettingsDialog extends JDialog {
    private int hotKeyCode = 113;
    private boolean hotKey = false;
    SettingsDialog(JFrame owner) {
        //Init Setting Dialog.
        super(owner, "登录");
        setSize(450, 250);
        setModal(true);

        //Create new Components.
        JPanel mainPanel = new JPanel();
        String[] names = new String[]{"热键： ", "截图路径： "};
        JLabel label1 = new JLabel(names[1]);
        JLabel label2 = new JLabel(names[0]);
        JTextField field1 = new JTextField(18);
        JTextField field2 = new JTextField(2);
        JButtonX fileButton = new JButtonX("...");
        JFileChooserX jFileChooserX = new JFileChooserX();
        JButtonX yes = new JButtonX("确定");
        Box xBox1 = Box.createHorizontalBox();
        Box XBox2 = Box.createHorizontalBox();
        Box XBox4 = Box.createHorizontalBox();
        Box XBox3 = Box.createHorizontalBox();
        Box YBox = Box.createVerticalBox();

        //Init Components.
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        label1.setFont(MainFrame.YaHei);
        label2.setFont(MainFrame.YaHei);
        field1.setFont(MainFrame.YaHei);
        field2.setFont(MainFrame.YaHei);
        fileButton.setPreferredSize(new Dimension(48, 24));
        yes.setPreferredSize(new Dimension(200, 100));
        field1.setPreferredSize(new Dimension(400, 25));
        field1.setText(Settings.settings.getSettings("screenShot"));
        field2.setText(KeyEvent.getKeyText(Integer.parseInt(Settings.settings.getSettings("hotKey"))));

        //Add Components on Boxes and JPanel.
        xBox1.add(Box.createHorizontalStrut(5));
        xBox1.add(label1);
        xBox1.add(field1);
        xBox1.add(fileButton);
        xBox1.add(Box.createHorizontalStrut(5));
        XBox2.add(Box.createHorizontalStrut(80));
        XBox2.add(XBox4);
        XBox2.add(Box.createHorizontalStrut(80));
        XBox3.add(yes);
        XBox4.add(label2);
        XBox4.add(field2);
        YBox.add(xBox1);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox2);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox3);
        mainPanel.add(YBox);
        this.getContentPane().add(mainPanel);

        //Add Action Listener.
        yes.addActionListener(e -> {
            if (hotKey) {
                JOptionPane.showMessageDialog(this, "重启后热键生效。");
                hotKey = false;
            }
            SettingsDialog.this.setVisible(false);
            Settings.settings.setSettings("screenShot", field1.getText());
            Settings.settings.setSettings("hotKey", String.valueOf(hotKeyCode));
            Settings.settings.writeAll();
        });
        field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                field2.setText("");
            }
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                field2.setText(key);
                hotKeyCode = code;
                hotKey = true;
            }
        });
        fileButton.addActionListener(e -> {
            int returnV = jFileChooserX.showOpenDialog(SettingsDialog.this);
            if (returnV == JFileChooserX.APPROVE_OPTION) {
                File file = jFileChooserX.getSelectedFile();
                field1.setText(file.getPath());
            }
        });


    }
}