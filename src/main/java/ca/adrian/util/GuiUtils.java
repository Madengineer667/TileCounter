package ca.adrian.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

import java.io.File;

import java.io.IOException;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public final class GuiUtils {
    //public static ImageIcon dukeLogo = createImageIcon("icons/duke.gif");
    
    public static Window getParentWindow(Component owner) {
            Window window = null;
            if (owner instanceof Window) {
                    window = (Window)owner;
            } else if (owner != null) {
                    window = SwingUtilities.getWindowAncestor(owner);
            }
            if (window == null) {
                    window = new JFrame();
            }
            return window;
    }
    
    public static void centerComponent(Component parent, Component child) {
        Point parentLocation = parent.getLocationOnScreen();
        Dimension parentSize = parent.getSize();
        Dimension frameSize = child.getSize();
        if (frameSize.height > parentSize.height) {
            frameSize.height = parentSize.height;
        }
        if (frameSize.width > parentSize.width) {
            frameSize.width = parentSize.width;
        }
        child.setLocation((( parentSize.width - child.getWidth()) / 2)+parentLocation.x,
                         (( parentSize.height - child.getHeight()) / 2) + parentLocation.y);            
    }    
    
    public static void centerComponent(Component comp) {
        comp.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        
        Dimension frameSize = comp.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        comp.setLocation( ( screenSize.width - frameSize.width ) / 2, 
                          ( screenSize.height - frameSize.height ) / 2 );        
    }
    
    public static File saveFileChoose(Component comp, String extension, String description) {
        int returnVal = 0;
        File saveFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extension);
        chooser.setFileFilter(filter);
        do {
            returnVal = chooser.showSaveDialog(comp);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                saveFile = chooser.getSelectedFile();
                if(saveFile.exists()) {
                    int ret = JOptionPane.showConfirmDialog (chooser,
                    "The file "+ saveFile.getName() + " already exists. \nWould you like to replace it?",
                    "Replace?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (ret!=JOptionPane.OK_OPTION) {
                        saveFile = null; 
                    } 
                } else {
                    if (saveFile.getName().indexOf(".") < 0) {
                        try {
                            saveFile = new File(saveFile.getCanonicalPath()+"."+extension);
                        } catch (IOException e) {
                            DisplayError.error("Error Creating Save File",e);
                        }
                    }
                }
            }                
        } while((saveFile == null)&&(returnVal != JFileChooser.CANCEL_OPTION));
        return saveFile;
    }
    
    public static File loadFileChoose(Component comp, String extension, String description) {
        int returnVal = 0;
        File loadFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extension);
        chooser.setFileFilter(filter);
        do {
            returnVal = chooser.showOpenDialog(comp);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                loadFile = chooser.getSelectedFile();
            }                
        } while((loadFile == null)&&(returnVal != JFileChooser.CANCEL_OPTION));
        return loadFile;
    }    
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        GuiUtils gui = new GuiUtils();
        ClassLoader cl = gui.getClass().getClassLoader();
        URL imgURL = cl.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            DisplayError.error(null,"About Error","Can't find the file:"+imgURL);
            return null;
        }
    }    
}
