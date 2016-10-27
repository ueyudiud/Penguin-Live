package com.tieba.onsn.swing;

import com.melloware.jintellitype.JIntellitype;
import com.tieba.onsn.PenguinLive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Onsn on 2016/10/24.
 *
 * @author OnSN
 */
public class MainFrame extends JFrame {
    static int hotKey = 113;
    private JTextArea textArea = new JTextArea();
    private TiebaDialog tiebaDialog = new TiebaDialog(this);
    private SettingsDialog settingsDialog = new SettingsDialog(this);

    public MainFrame () throws IOException {
        super("Penguin Live");
        setSize(550, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //--------------------

        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        downPanel.setPreferredSize(new Dimension(450, 300));
        downPanel.setOpaque(false);
        JPanel sendPanel = new JPanel(new BorderLayout());
        sendPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        textArea.setPreferredSize(new Dimension(450, 200));
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        textArea.setLineWrap(true);
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        JButtonX sendButton = new JButtonX("发送");
        sendButton.setPreferredSize(new Dimension(100, 50));
        sendButton.setFont(new Font("微软雅黑", Font.BOLD, 20));

        sendPanel.setOpaque(false);
        sendPanel.add(sendButton, BorderLayout.EAST);

        downPanel.add(textArea, "North");
        downPanel.add(sendPanel, BorderLayout.SOUTH);

        //---------------------

        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setBorder(new EmptyBorder(50, 0, 25, 0));
        upPanel.setPreferredSize(new Dimension(400, 300));
        upPanel.setOpaque(false);

        ImgPanel imgPanel = new ImgPanel();
        imgPanel.setPreferredSize(new Dimension(400, 225));
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(50, 0));
        emptyPanel.setOpaque(false);

        JButtonX tiebaButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Tieba.png")));
        tiebaButton.setPreferredSize(new Dimension(100, 100));
        JButtonX settingsButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Settings.png")));
        settingsButton.setPreferredSize(new Dimension(100, 100));
        tiebaButton.setBorder(null);
        settingsButton.setBorder(null);
        tiebaButton.setContentAreaFilled(false);
        settingsButton.setContentAreaFilled(false);

        tiebaButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_press.png")));
        settingsButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_press.png")));
        tiebaButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_put.png")));
        settingsButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_put.png")));

        Box buttons = Box.createVerticalBox();
        buttons.setPreferredSize(new Dimension(100, 100));
        buttons.setBorder(new EmptyBorder(5, 25, 5, 25));

        buttons.add(tiebaButton);
        buttons.add(Box.createVerticalStrut(15));
        buttons.add(settingsButton);

        upPanel.add(buttons, BorderLayout.EAST);
        upPanel.add(emptyPanel, BorderLayout.WEST);
        upPanel.add(imgPanel, BorderLayout.CENTER);

        //---------------------

        this.getContentPane().add(upPanel, BorderLayout.NORTH);
        this.getContentPane().add(downPanel, BorderLayout.SOUTH);

        //---------------------

        sendButton.addActionListener(e -> {
            String s = textArea.getText().replaceAll("\n", " [br] ");
            System.out.println(s);
            textArea.setText(null);
        });
        tiebaButton.addActionListener(e -> tiebaDialog.setVisible(true));
        settingsButton.addActionListener((e) -> settingsDialog.setVisible(true));

        JIntellitype.getInstance().registerHotKey(0, 0, MainFrame.hotKey);
        JIntellitype.getInstance().addHotKeyListener(identifier -> {
            if (identifier == 0) {
                MainFrame.this.setState(NORMAL);
                MainFrame.this.setLocationRelativeTo(null);
                MainFrame.this.setVisible(true);

            }
        });
    }
    private class ImgPanel extends JPanel {
        Image img;
        ImgPanel() {
            super();
        }
        public void paint (Graphics g) {
            super.paint(g);
            URL imgUrl = PenguinLive.class.getResource("/img.png");
            img = Toolkit.getDefaultToolkit().getImage(imgUrl);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, 400, 225, this);
        }
    }
}