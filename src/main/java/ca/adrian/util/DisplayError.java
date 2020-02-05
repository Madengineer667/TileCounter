package ca.adrian.util;

import java.awt.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Calendar;

import javax.swing.JOptionPane;


public class DisplayError extends JOptionPane {
    private static final String logName = "Syncher.log";
    
    public static void error(String title, Exception ex) {
        error(null, title, ex);
    }
    
    public static void error(Component comp, String title, String error) {
        error(comp, title, error, null);
    }
    
    public static void error(Component comp, String title, Exception ex) {        
        error(comp, title, ex.toString(), ex);
    }
    
    public static void error(Component comp, String title, String error, Exception ex) {        
        JOptionPane.showMessageDialog(comp,error,title,JOptionPane.ERROR_MESSAGE);
        if (ex != null) {
            PrintStream logWriter = null;
            Calendar rightNow = Calendar.getInstance();
            try {
                logWriter = new PrintStream(new FileOutputStream(logName, true));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(comp,e.toString(),"Log Error - Opening Log File",JOptionPane.ERROR_MESSAGE);
            }
            logWriter.println("["+rightNow.getTime()+"]:"+error);
            ex.printStackTrace(logWriter);
            logWriter.println();
            logWriter.close();
        }
    }
}
