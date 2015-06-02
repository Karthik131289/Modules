package com.company.app.ui.components.Buttons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by DV21 on 14-01-2015.
 */
public class XButton extends JButton {

    private final Insets borderInsets = new Insets(6,6,6,6);
    private Insets marginInsets = new Insets(0,0,0,0);

    private Boolean isFocusPaint = true;
    private Boolean isBorderPaint = false;
    private Boolean isOpaque = false;
    private Boolean isRound = true;
    private int arc = 5;

    public XButton(String text) {
        this.setText( text );
        createRoundButton();
    }

    private void createRoundButton(){
        this.setBorder(new EmptyBorder( borderInsets ) );
        this.setMargin( marginInsets );
        this.setFocusPainted( isFocusPaint );
        this.setBorderPainted( isBorderPaint );
        this.setOpaque( isOpaque );
        this.setContentAreaFilled( false );
    }

    @Override
    protected void paintComponent( Graphics g ){
        g.setColor( getBackground() );
        g.drawRoundRect( 0 , 0 , getWidth()-1 , getHeight()-1 , arc , arc );
        g.fillRoundRect( 0 , 0 , getWidth()-1 , getHeight()-1 , arc , arc );
        super.paintComponent( g );
    }
}
