package com.tieba.onsn.swing;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import static com.tieba.onsn.PenguinLive.log;

/**
 * Created by Onsn on 2016/10/27.
 *
 * @author OnSN
 */
class JFileChooserX extends JFileChooser {
    /**
     * Constructs a <code>JFileChooser</code> pointing to the user's
     * default directory. This default depends on the operating system.
     * It is typically the "My Documents" folder on Windows, and the
     * user's home directory on Unix.
     */
    JFileChooserX() {
        this((File) null, (FileSystemView) null);
    }

    /**
     * Constructs a <code>JFileChooser</code> using the given path.
     * Passing in a <code>null</code>
     * string causes the file chooser to point to the user's default directory.
     * This default depends on the operating system. It is
     * typically the "My Documents" folder on Windows, and the user's
     * home directory on Unix.
     *
     * @param currentDirectoryPath  a <code>String</code> giving the path
     *                          to a file or directory
     */
    public JFileChooserX (String currentDirectoryPath) {
        this(currentDirectoryPath, (FileSystemView) null);
    }

    /**
     * Constructs a <code>JFileChooser</code> using the given <code>File</code>
     * as the path. Passing in a <code>null</code> file
     * causes the file chooser to point to the user's default directory.
     * This default depends on the operating system. It is
     * typically the "My Documents" folder on Windows, and the user's
     * home directory on Unix.
     *
     * @param currentDirectory  a <code>File</code> object specifying
     *                          the path to a file or directory
     */
    public JFileChooserX (File currentDirectory) {
        this(currentDirectory, (FileSystemView) null);
    }

    /**
     * Constructs a <code>JFileChooser</code> using the given
     * <code>FileSystemView</code>.
     */
    public JFileChooserX (FileSystemView fsv) {
        this((File) null, fsv);
    }


    /**
     * Constructs a <code>JFileChooser</code> using the given current directory
     * and <code>FileSystemView</code>.
     */
    public JFileChooserX (File currentDirectory, FileSystemView fsv) {
        super(currentDirectory, fsv);
        setFileSelectionMode(DIRECTORIES_ONLY);
    }

    /**
     * Constructs a <code>JFileChooser</code> using the given current directory
     * path and <code>FileSystemView</code>.
     */
    public JFileChooserX (String currentDirectoryPath, FileSystemView fsv) {
        super(currentDirectoryPath, fsv);
        setFileSelectionMode(DIRECTORIES_ONLY);
    }
}
