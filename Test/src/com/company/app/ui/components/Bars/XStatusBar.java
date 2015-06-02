package com.company.app.ui.components.Bars;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by DV21 on 21-01-2015.
 */
public class XStatusBar extends JPanel {

    public static final int PROGRESS_MIN_VAL = 0;
    public static final int PROGRESS_MAX_VAL = 100;
    public static final String STATUS_IDLE = "Idle...";

    private JSeparator separator;
    private JLabel lblStatus;
    private JProgressBar progressBar;
    private JPanel statusPanel;
    private JPanel progressPanel;
    private JPanel componentPanel;

    public XStatusBar(){
        this.separator = new JSeparator( JSeparator.VERTICAL );
        this.lblStatus = new JLabel();
        this.progressBar = new JProgressBar();
        this.statusPanel = new JPanel();
        this.progressPanel = new JPanel();
        this.componentPanel = new JPanel();

        super.setLayout(new BorderLayout(5, 0));
        super.setBorder(new LineBorder(Color.BLACK, 1) );

        this.separator.setBackground(Color.DARK_GRAY);
        this.separator.setVisible(true);

        this.statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        this.statusPanel.setBackground( getBackground() );
        this.statusPanel.setOpaque( false );
        this.statusPanel.add( this.lblStatus );
        this.statusPanel.add( new JSeparator( SwingConstants.VERTICAL ) );

        this.progressPanel.setLayout( new FlowLayout( FlowLayout.RIGHT , 5 , 5 ) );
        this.progressPanel.setBackground( getBackground() );
        this.progressPanel.setOpaque( false );
        this.progressPanel.add( this.progressBar );
        this.progressPanel.add( new JSeparator( SwingConstants.VERTICAL ) );

        this.componentPanel.setLayout( new BoxLayout( this.componentPanel , BoxLayout.X_AXIS ) );
        this.componentPanel.setBackground( getBackground() );
        this.componentPanel.setOpaque( false );

        super.add(this.statusPanel, BorderLayout.WEST);
        super.add(this.progressPanel, BorderLayout.EAST);
        super.add(this.componentPanel, BorderLayout.CENTER);
    }
    public void setStatusText( String message ){
        if( message != null )
            this.lblStatus.setText( message );
    }
    public String getStatusText(){
        return this.lblStatus.getText();
    }
    public void setStatusIcon( Icon icon ){
        if( icon != null )
            this.lblStatus.setIcon( icon );
    }
    public Icon getStatusIcon(){
        return this.lblStatus.getIcon();
    }
    public void setStatusText( String message , Icon icon , Font font , Color foreground ){
        this.setStatusText( message , icon );
        if( font != null )
            this.lblStatus.setFont( font );
        if( foreground != null )
            this.lblStatus.setForeground( foreground );
    }
    public void setStatusText( String message , Icon icon ){
        this.setStatusText( message );
        this.setStatusIcon( icon );
    }
    public void setProgressMaxValue( int maxValue ){
        this.progressBar.setMaximum( maxValue );
    }
    public int getProgressMaxValue(){
        return this.progressBar.getMaximum();
    }
    public void setProgressMinValue( int minValue ){
        this.progressBar.setMinimum( minValue );
    }
    public int getProgressMinValue(){
        return this.progressBar.getMinimum();
    }
    public void setProgressValue( int val ){
        this.progressBar.setValue( val );
    }
    public int getProgressValue(){
        return this.progressBar.getValue();
    }
    public void setProgressBounds( int minVal , int maxVal ){
        this.setProgressMinValue( minVal );
        this.setProgressMaxValue(maxVal);
    }
    public void showProgress( boolean visibility ){
        this.progressBar.setVisible( visibility );
        this.progressPanel.updateUI();
        this.repaint();
    }
    public void addCustomComponent( JComponent component ){
        if( component != null ){
            this.componentPanel.add( component );
            this.componentPanel.revalidate();
        }
    }
    public void removeCustomComponent( JComponent component ){
        if( component != null ){
            this.componentPanel.remove( component );
            this.componentPanel.revalidate();
        }
    }
}
