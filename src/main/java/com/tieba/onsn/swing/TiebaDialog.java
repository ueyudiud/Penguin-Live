package com.tieba.onsn.swing;


import com.tieba.onsn.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Onsn on 2016/10/25.
 *
 * @author OnSN
 */
class TiebaDialog extends JDialog {

    TiebaDialog(JFrame owner) {
        //Init Tieba Dialog.
        super(owner, "登录");
        setSize(450, 250);
        setModal(true);

        //Create new Components.
        JPanel mainPanel = new JPanel();
        String[] names = new String[]{"BDUSS: ", "帖子地址: "};
        JLabel label1 = new JLabel(names[0]);
        JLabel label2 = new JLabel(names[1]);
        JTextField field1 = new JTextField(21);
        JTextField field2 = new JTextField(21);
        JButtonX yes = new JButtonX("确定");

        //Init Components.
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        label1.setFont(MainFrame.YaHei);
        label2.setFont(MainFrame.YaHei);
        field1.setFont(MainFrame.YaHei);
        field2.setFont(MainFrame.YaHei);
        yes.setPreferredSize(new Dimension(200, 100));
        field1.setText(Settings.settings.getSettings("BDUSS"));
        field2.setText(Settings.settings.getSettings("page"));

        //Add Components on Boxes and JPanel.
        Box xBox1 = Box.createHorizontalBox();
        xBox1.add(label1);
        xBox1.add(field1);
        Box XBox2 = Box.createHorizontalBox();
        XBox2.add(label2);
        XBox2.add(field2);
        Box XBox3 = Box.createHorizontalBox();
        XBox3.add(yes);
        Box YBox = Box.createVerticalBox();
        YBox.add(xBox1);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox2);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox3);
        mainPanel.add(YBox);
        getContentPane().add(mainPanel);

        //Add Action Listener.
        yes.addActionListener(e -> {
            TiebaDialog.this.setVisible(false);
            Settings.settings.setSettings("BDUSS", field1.getText());
            Settings.settings.setSettings("page", field2.getText());
            Settings.settings.writeAll();
        });


    }
}
