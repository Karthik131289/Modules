package com.company.app.ui.app;

import com.company.app.ui.components.Panels.DockPanel;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * Created by DV21 on 12-05-2015.
 */
public class ConsoleWindow extends DockPanel {
    public static final String CONSOLE_WINDOW = "Console";
    private JScrollPane scrollPane;
    private JTextPane   txtPane;

    public ConsoleWindow() {
        super();
        initUI();
        initStyles();
    }

    private void initUI() {

        super.setLayout( new BorderLayout() );
        scrollPane = new JScrollPane();
        scrollPane.setBorder( null );
        this.add(scrollPane, BorderLayout.CENTER);
        {
            txtPane = new JTextPane();
            scrollPane.setViewportView(txtPane);
        }
    }

    private void initStyles() {
        StyleContext styleContext = new StyleContext();
        DefaultStyledDocument doc = new DefaultStyledDocument( styleContext );

        Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
        defaultStyle.addAttribute( StyleConstants.FontFamily , "monospace" );
        defaultStyle.addAttribute( StyleConstants.Background , Color.WHITE );

        Style normalStyle = styleContext.addStyle( "normal" , defaultStyle );
        normalStyle.addAttribute( StyleConstants.Foreground , Color.BLACK );

        Style errorStyle = styleContext.addStyle( "error" , defaultStyle );
        errorStyle.addAttribute( StyleConstants.Foreground , Color.RED );

        doc.setLogicalStyle( 0 , defaultStyle );
        txtPane.setDocument( doc );
    }

    public void writeLine( final String message , final String messageType ) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                try {
                    StyledDocument doc = (StyledDocument) txtPane.getDocument();
                    Style style = doc.getStyle( messageType );
                    doc.insertString( doc.getLength() , message + "\n" , style );
                    txtPane.validate();
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clear() {
        StyledDocument doc = (StyledDocument)txtPane.getDocument();
        txtPane.setText( "" );

    }
}
