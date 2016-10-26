package com.tieba.onsn.swing;

import com.tieba.onsn.PenguinLive;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Onsn on 2016/10/24.
 *
 * @author OnSN
 */
public class MainFrame extends JFrame {
    public JPanel downPanel = new JPanel(new BorderLayout());
    public JPanel sendPanel = new JPanel(new BorderLayout());
    public JTextArea textArea = new JTextArea();
    public JButtonX sendButton = new JButtonX("发送");
    public JPanel upPanel = new JPanel(new BorderLayout());
    public ImgPanel imgPanel = new ImgPanel();
    private Box buttons = Box.createVerticalBox();
    private JPanel emptyPanel = new JPanel();
    private JButtonX tiebaButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Tieba.png")));
    private JButtonX settingsButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Settings.png")));
    public TiebaDialog tiebaDialog = new TiebaDialog(this);
    public SettingsDialog settingsDialog = new SettingsDialog(this);

    public MainFrame () {
        super("Penguin Live");
        setSize(550, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //--------------------

        downPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        downPanel.setPreferredSize(new Dimension(450, 300));
        downPanel.setOpaque(false);
        sendPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        textArea.setPreferredSize(new Dimension(450, 200));
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        textArea.setLineWrap(true);
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        sendButton.setPreferredSize(new Dimension(100, 50));
        sendButton.setFont(new Font("微软雅黑", Font.BOLD, 20));

        sendPanel.setOpaque(false);
        sendPanel.add(sendButton, BorderLayout.EAST);

        downPanel.add(textArea, "North");
        downPanel.add(sendPanel, BorderLayout.SOUTH);

        //---------------------

        upPanel.setBorder(new EmptyBorder(50, 0, 25, 0));
        upPanel.setPreferredSize(new Dimension(400, 300));
        upPanel.setOpaque(false);

        imgPanel.setPreferredSize(new Dimension(400, 225));
        emptyPanel.setPreferredSize(new Dimension(50, 0));
        emptyPanel.setOpaque(false);

        tiebaButton.setPreferredSize(new Dimension(100, 100));
        settingsButton.setPreferredSize(new Dimension(100, 100));
        tiebaButton.setBorder(null);
        settingsButton.setBorder(null);
        tiebaButton.setContentAreaFilled(false);
        settingsButton.setContentAreaFilled(false);

        tiebaButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_press.png")));
        settingsButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_press.png")));
        tiebaButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_put.png")));
        settingsButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_put.png")));

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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textArea.getText().replaceAll("\n", " [br] ");
                System.out.println(s);
                textArea.setText(null);
            }
        });

        tiebaButton.addActionListener(e -> tiebaDialog.setVisible(true));
        settingsButton.addActionListener((e) -> settingsDialog.setVisible(true));

    }

    public class ImgPanel extends JPanel {
        Image img;
        public ImgPanel () {
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
