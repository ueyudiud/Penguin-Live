package com.tieba.onsn.swing;

import com.tieba.onsn.PenguinLiveDebug;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import static com.tieba.onsn.PenguinLiveDebug.log;
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
    private int codey = 113;
    private boolean hotKey = false;
    SettingsDialog(JFrame owner) {
        super(owner, "登录");
        log.addLog("创建设置提示框中...");
        setSize(450, 250);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        setModal(true);


        String[] names = new String[]{"热键： ", "截图路径： "};
        JLabel label1 = new JLabel(names[1]);
        JLabel label2 = new JLabel(names[0]);

        label1.setFont(PenguinLiveDebug.YaHei);
        label2.setFont(PenguinLiveDebug.YaHei);


        JTextField field1 = new JTextField(18);
        JTextField field2 = new JTextField(2);

        field1.setFont(PenguinLiveDebug.YaHei);
        field2.setFont(PenguinLiveDebug.YaHei);

        JButtonX fileButton = new JButtonX("...");
        JFileChooserX jFileChooserX = new JFileChooserX();
        fileButton.setPreferredSize(new Dimension(48, 24));
        JButtonX yes = new JButtonX("确定");
        yes.setPreferredSize(new Dimension(200, 100));

        Box xBox1 = Box.createHorizontalBox();
        xBox1.add(Box.createHorizontalStrut(5));
        xBox1.add(label1);
        xBox1.add(field1);
        xBox1.add(fileButton);
        xBox1.add(Box.createHorizontalStrut(5));
        field1.setPreferredSize(new Dimension(400, 25));
        field2.setText("F2");

        Box XBox2 = Box.createHorizontalBox();
        Box XBox4 = Box.createHorizontalBox();
        XBox4.add(label2);
        XBox4.add(field2);
        XBox2.add(Box.createHorizontalStrut(80));
        XBox2.add(XBox4);
        XBox2.add(Box.createHorizontalStrut(80));

        Box XBox3 = Box.createHorizontalBox();
        XBox3.add(yes);

        Box YBox = Box.createVerticalBox();
        YBox.add(xBox1);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox2);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox3);

        yes.addActionListener(e -> {
            log.addLog("按下了：设置提示框确认按钮。");
            if (hotKey) {
                JOptionPane.showMessageDialog(this, "重启后热键生效。");
                hotKey = false;
            }
            SettingsDialog.this.setVisible(false);
            PenguinLiveDebug.settings.setSettings("screenShot", field1.getText());
            PenguinLiveDebug.settings.setSettings("hotKey", String.valueOf(codey));
            PenguinLiveDebug.settings.writeAll();
        });

        mainPanel.add(YBox);

        field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                field2.setText("");
            }
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                field2.setText(key);
                codey = code;
                hotKey = true;
                log.addLog("热键为：" + key);
            }
        });
        fileButton.addActionListener(e -> {
            log.addLog("创建文件选择面板。");
            int returnV = jFileChooserX.showOpenDialog(SettingsDialog.this);
            if (returnV == JFileChooserX.APPROVE_OPTION) {
                File file = jFileChooserX.getSelectedFile();
                field1.setText(file.getPath());
            }
        });

        getContentPane().add(mainPanel);
    }
}