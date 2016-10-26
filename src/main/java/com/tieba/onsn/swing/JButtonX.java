package com.tieba.onsn.swing;

import javax.swing.*;
import java.beans.ConstructorProperties;

/**
 * Created by Onsn on 2016/10/26.
 *
 * @author OnSN
 */
public class JButtonX extends JButton {
    /**
     * Creates a button with no set text or icon.
     */
    public JButtonX() {
        this(null, null);
    }

    /**
     * Creates a button with an icon.
     *
     * @param icon  the Icon image to display on the button
     */
    public JButtonX(Icon icon) {
        this(null, icon);
    }

    /**
     * Creates a button with text.
     *
     * @param text  the text of the button
     */
    @ConstructorProperties({"text"})
    public JButtonX(String text) {
        this(text, null);
    }

    /**
     * Creates a button where properties are taken from the
     * <code>Action</code> supplied.
     *
     * @param a the <code>Action</code> used to specify the new button
     *
     * @since 1.3
     */
    public JButtonX(Action a) {
        this();
        setAction(a);
    }

    /**
     * Creates a button with initial text and an icon.
     *
     * @param text  the text of the button
     * @param icon  the Icon image to display on the button
     */
    public JButtonX(String text, Icon icon) {
        super(text, icon);
        setFocusable(false);
    }
}
