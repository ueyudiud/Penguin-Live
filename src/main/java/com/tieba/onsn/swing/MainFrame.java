package com.tieba.onsn.swing;

import com.melloware.jintellitype.JIntellitype;
import com.tieba.onsn.Log;
import com.tieba.onsn.PenguinLive;
import com.tieba.onsn.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Onsn on 2016/10/29.
 *
 * @author OnSN
 */
@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    public static final Font YaHei = new Font("微软雅黑", Font.BOLD, 15);
    private JIntellitype jIntellitype = JIntellitype.getInstance();
    private JTextArea textArea = new JTextArea();
    private TiebaDialog tiebaDialog = new TiebaDialog(this);
    private SettingsDialog settingsDialog = new SettingsDialog(this);
    private ImagePanel imagePanel = new ImagePanel();
    private JPanel downPanel = new JPanel(new BorderLayout());
    private JPanel sendPanel = new JPanel(new BorderLayout());
    private JButtonX sendButton = new JButtonX("发送");
    private JPanel upPanel = new JPanel(new BorderLayout());
    private JPanel emptyPanel = new JPanel();
    private JButtonX tiebaButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Tieba.png")));
    private JButtonX settingsButton = new JButtonX(new ImageIcon(PenguinLive.class.getResource("/Settings.png")));
    private Box buttons = Box.createVerticalBox();

    public MainFrame() {
        //Init Main Frame.
        super("Penguin Live");
        setSize(550, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Set Component Border.
        downPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        sendPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        upPanel.setBorder(new EmptyBorder(50, 0, 25, 0));
        tiebaButton.setBorder(null);
        settingsButton.setBorder(null);
        buttons.setBorder(new EmptyBorder(5, 25, 5, 25));

        //Set Component Size.
        downPanel.setPreferredSize(new Dimension(450, 300));
        textArea.setPreferredSize(new Dimension(450, 200));
        sendButton.setPreferredSize(new Dimension(100, 50));
        upPanel.setPreferredSize(new Dimension(400, 300));
        imagePanel.setPreferredSize(new Dimension(400, 225));
        emptyPanel.setPreferredSize(new Dimension(50, 0));
        tiebaButton.setPreferredSize(new Dimension(100, 100));
        settingsButton.setPreferredSize(new Dimension(100, 100));
        buttons.setPreferredSize(new Dimension(100, 100));

        //Set Component Opaque.
        downPanel.setOpaque(false);
        sendPanel.setOpaque(false);
        upPanel.setOpaque(false);
        emptyPanel.setOpaque(false);

        //Set Button Icon.
        tiebaButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_press.png")));
        settingsButton.setPressedIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_press.png")));
        tiebaButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Tieba_put.png")));
        settingsButton.setRolloverIcon(new ImageIcon(PenguinLive.class.getResource("/Settings_put.png")));

        //Set Something else.
        textArea.setLineWrap(true);
        textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        sendButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        tiebaButton.setContentAreaFilled(false);
        settingsButton.setContentAreaFilled(false);

        //Add Component on JPanel.
        sendPanel.add(sendButton, BorderLayout.EAST);
        downPanel.add(textArea, "North");
        downPanel.add(sendPanel, BorderLayout.SOUTH);
        buttons.add(tiebaButton);
        buttons.add(Box.createVerticalStrut(15));
        buttons.add(settingsButton);
        upPanel.add(buttons, BorderLayout.EAST);
        upPanel.add(emptyPanel, BorderLayout.WEST);
        upPanel.add(imagePanel, BorderLayout.CENTER);

        //Add JPanel on Main Frame.
        this.getContentPane().add(upPanel, BorderLayout.NORTH);
        this.getContentPane().add(downPanel, BorderLayout.SOUTH);


        //Add Action Listener.
        sendButton.addActionListener(e -> {
            send();
        });
        tiebaButton.addActionListener(e -> {
            tiebaDialog.setVisible(true);
        });
        settingsButton.addActionListener((e) -> {
            settingsDialog.setVisible(true);
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10 && e.isControlDown()) {
                    send();
                }
            }
        });

        //Add Global Hot Key.
        jIntellitype.registerHotKey(0, 0, Integer.parseInt(Settings.settings.getSettings("hotKey")));
        jIntellitype.addHotKeyListener(identifier -> {
            if (identifier == 0) {
                hotKey();
            }
        });
    }

    private void hotKey() {
        this.setLocationRelativeTo(null);
        this.setState(NORMAL);
        this.setVisible(true);
        imagePanel.setFile();
    }
    private void send() {
        String text = textArea.getText().replaceAll("\n", " [br] ");
        textArea.setText(null);
        Log.log.addLog("Send: \"" + text + "\"");
    }
}
