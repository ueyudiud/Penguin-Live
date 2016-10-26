<<<<<<< HEAD
package com.tieba.onsn.swing;

import com.tieba.onsn.PenguinLive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Onsn on 2016/10/25.
 *
 * @author OnSN
 */
class SettingsDialog extends JDialog {

    SettingsDialog(JFrame owner) {
        super(owner, "登录");
        setSize(450, 250);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        setModal(true);


        String[] names = new String[]{"热键： ", "截图路径： "};
        JLabel label1 = new JLabel(names[1]);
        JLabel label2 = new JLabel(names[0]);

        label1.setFont(PenguinLive.YaHei);
        label2.setFont(PenguinLive.YaHei);


        JTextField field1 = new JTextField(15);
        JTextField field2 = new JTextField(4);

        field1.setFont(PenguinLive.YaHei);
        field2.setFont(PenguinLive.YaHei);

        JButtonX fileButton = new JButtonX("...");
        fileButton.setPreferredSize(new Dimension(48, 24));
        JButtonX yes = new JButtonX("确定");
        yes.setPreferredSize(new Dimension(200, 100));

        Box xBox1 = Box.createHorizontalBox();
        xBox1.add(Box.createHorizontalStrut(10));
        xBox1.add(label1);
        xBox1.add(field1);
        xBox1.add(fileButton);
        xBox1.add(Box.createHorizontalStrut(10));

        Box XBox2 = Box.createHorizontalBox();
        XBox2.add(Box.createHorizontalStrut(80));
        XBox2.add(label2);
        XBox2.add(field2);
        XBox2.add(Box.createHorizontalStrut(80));

        Box XBox3 = Box.createHorizontalBox();
        XBox3.add(yes);

        Box YBox = Box.createVerticalBox();
        YBox.add(xBox1);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox2);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox3);

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsDialog.this.setVisible(false);
            }
        });

        mainPanel.add(YBox);

        field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                System.out.println(key);
                field2.setText(key);
            }
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                field2.setText(key);
            }
        });

        getContentPane().add(mainPanel);
    }
}
=======
package com.tieba.onsn.swing;

import com.tieba.onsn.PenguinLive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Onsn on 2016/10/25.
 *
 * @author OnSN
 */
class SettingsDialog extends JDialog {

    SettingsDialog(JFrame owner) {
        super(owner, "登录");
        setSize(450, 250);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        setModal(true);


        String[] names = new String[]{"热键： ", "截图路径： "};
        JLabel label1 = new JLabel(names[1]);
        JLabel label2 = new JLabel(names[0]);

        label1.setFont(PenguinLive.YaHei);
        label2.setFont(PenguinLive.YaHei);


        JTextField field1 = new JTextField(15);
        JTextField field2 = new JTextField(4);

        field1.setFont(PenguinLive.YaHei);
        field2.setFont(PenguinLive.YaHei);

        JButtonX fileButton = new JButtonX("...");
        fileButton.setPreferredSize(new Dimension(48, 24));
        JButtonX yes = new JButtonX("确定");
        yes.setPreferredSize(new Dimension(200, 100));

        Box xBox1 = Box.createHorizontalBox();
        xBox1.add(Box.createHorizontalStrut(10));
        xBox1.add(label1);
        xBox1.add(field1);
        xBox1.add(fileButton);
        xBox1.add(Box.createHorizontalStrut(10));

        Box XBox2 = Box.createHorizontalBox();
        XBox2.add(Box.createHorizontalStrut(80));
        XBox2.add(label2);
        XBox2.add(field2);
        XBox2.add(Box.createHorizontalStrut(80));

        Box XBox3 = Box.createHorizontalBox();
        XBox3.add(yes);

        Box YBox = Box.createVerticalBox();
        YBox.add(xBox1);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox2);
        YBox.add(Box.createVerticalStrut(20));
        YBox.add(XBox3);

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsDialog.this.setVisible(false);
            }
        });

        mainPanel.add(YBox);

        field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                System.out.println(key);
                field2.setText(key);
            }
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                String key = KeyEvent.getKeyText(code);
                field2.setText(key);
            }
        });

        getContentPane().add(mainPanel);
    }
}
>>>>>>> origin/master
