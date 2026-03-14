package com.udistrital;

import javax.swing.SwingUtilities;

import com.udistrital.View.GraphGUI;


public class App 
{
    public static void main( String[] args )
    {
       {
        SwingUtilities.invokeLater(() -> new GraphGUI());
        }

    }
}
