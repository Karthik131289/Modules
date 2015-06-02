package com.company.app.ui.components.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by DV21 on 13-01-2015.
 */
public class XSplashPanel extends JLabel {

    private ImageIcon splashImage;
    private String statusText = "";
    private Color statusColor = Color.WHITE;
    private Font  statusFont  = new Font( Font.SANS_SERIF , Font.ITALIC|Font.BOLD , 10 );
    private Point statusLocation = new Point(10,10);
    private Vector<splashScreenData> splashData = new Vector<splashScreenData>();

    public XSplashPanel(ImageIcon icon){
        super( icon );
        this.splashImage = icon;
        setDoubleBuffered( true );
    }

    public void setStatusText( String text , Color color , Font font ){
        if( text!=null )
            this.statusText = text ;
        if( color!=null )
            this.statusColor = color;
        if( font!=null )
            this.statusFont = font;
        repaint();
    }

    public void setStatusText( String text ){
        if( text!=null )
            this.statusText = text;
        repaint();
    }

    public void setStatusLocation( Point statusLocation ) {
        setStatusLocation(statusLocation.x, statusLocation.y);
    }
    public void setStatusLocation( int x , int y ){
        this.statusLocation.setLocation( x , y );
    }
    public void setSplashData( splashScreenData data ){
        synchronized (splashData){
            if( data!=null )
                splashData.add( data );
        }
        repaint();
    }
    public void setSplashData( String text , Point loc ){
        setSplashData( new splashScreenData( text , loc ) );
    }
    public void setSplashData( String text , Color foreground , Color background , Font font , Point loc ){
        setSplashData( new splashScreenData( text , foreground , background , font , loc ) );
    }

    public void update( Graphics g ){
        paint(g);
    }
    public void paint( Graphics g ){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        /**** Display status Information ****/
        g.setColor( this.statusColor );
        g.setFont( this.statusFont );
        int statusY = getBounds().height - this.statusLocation.y;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if( this.statusText!=null )
            g.drawString( this.statusText , this.statusLocation.x , statusY );

        /**** Display Splash Screen Information ****/
        synchronized( splashData ){
            Iterator<splashScreenData> it = splashData.iterator();
            while ( it.hasNext() ){
                splashScreenData data = it.next();
                if( data.text!=null ){
                    g.setColor( data.foreColor );
                    g.setFont( data.font );
                    int yPos = getBounds().height - data.location.y;
                    g.drawString( data.text , data.location.x , yPos );
                }
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    class splashScreenData {
        String text = "";
        Color foreColor = Color.WHITE;
        Color backColor = Color.BLACK;
        Font  font = new Font( Font.SANS_SERIF , Font.BOLD , 10 );
        Point location;

        public splashScreenData( String text , Color foreground , Color background , Font font , Point loc ){
            if( text!=null )
                this.text = text ;
            if( foreground!=null )
                this.foreColor = foreground;
            if( background!=null )
                this.backColor = background;
            if( font!=null )
                this.font = font;

            this.location = loc;
        }
        public splashScreenData( String text , Point loc ){
            if( text!=null )
                this.text = text ;
            this.location = loc;
        }
        public void setBackColor( Color background ){
            if( background!=null )
                this.backColor = background;
        }
        public Color getBackColor(){
            return this.backColor;
        }
        public void setForeColor( Color foreground ){
            if( foreground!=null )
                this.foreColor = foreground;
        }
        public Color getForeColor(){
            return this.foreColor;
        }
        public void setFont( Font font ){
            if( font!=null )
                this.font = font;
        }
        public Font getFont(){
            return this.font;
        }
    }
}
