package com.tieba.onsn.swing;

import com.tieba.onsn.PenguinLive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Onsn on 2016/10/25.
 *
 * @author OnSN
 */
class TiebaDialog extends JDialog {

    TiebaDialog(JFrame owner) {
        super(owner, "登录");
        setSize(450, 250);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        setModal(true);


        String[] names = new String[]{"BDUSS: ", "帖子地址: "};
        JLabel label1 = new JLabel(names[0]);
        JLabel label2 = new JLabel(names[1]);

        JTextField field1 = new JTextField(21);
        JTextField field2 = new JTextField(21);

        label1.setFont(PenguinLive.YaHei);
        label2.setFont(PenguinLive.YaHei);
        field1.setFont(PenguinLive.YaHei);
        field2.setFont(PenguinLive.YaHei);

        JButtonX yes = new JButtonX("确定");
        yes.setPreferredSize(new Dimension(200, 100));

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

        yes.addActionListener(e -> {
            TiebaDialog.this.setVisible(false);
            PenguinLive.settings.setSettings("BDUSS", field1.getText());
            PenguinLive.settings.setSettings("page", field2.getText());
            PenguinLive.settings.writeAll();
        });

        mainPanel.add(YBox);

        getContentPane().add(mainPanel);
    }
}
