package comp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by DV21 on 12-05-2015.
 */
public class DockWindow extends JPanel {

    private DockBar dockButtonBar;
    private Map<String,DockPanel> dockPanels;
    private Map<String,DockButton> dockButtons;

    public DockWindow() {
        super();
        this.initDockWindow();

        this.dockPanels = new HashMap<String, DockPanel>();
        this.dockButtons = new HashMap<String, DockButton>();
    }

    private void initDockWindow() {
        this.setLayout( new BorderLayout() );

        dockButtonBar = new DockBar();
        this.add( dockButtonBar , BorderLayout.SOUTH );
    }

    public DockBar getDockButtonBar() {
        return  this.dockButtonBar;
    }

    public DockButton getDockButton( String dockPanelName ) {
        return this.dockButtons.get( dockPanelName );
    }

    public DockPanel getDockPanel( String dockPanelName ) {
        return this.dockPanels.get( dockPanelName );
    }

    public String[] getDockPanelNames() {
        String[] names = null ;

        if( this.dockPanels.size() > 0 ) {
            names = new String[ this.dockPanels.size() ];
            this.dockPanels.keySet().toArray( names );
        }

        return names;
    }

    public void addDockPanel( final String name , final DockPanel panel ) {
        DockButton button = new DockButton();
        button.setText( name );
        button.addActionListener( new ActionListener() {
            boolean show = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                show = !show;
                SwingUtilities.invokeLater( new Runnable() {
                    @Override
                    public void run() {
                        showHidePanel(show, panel);
                    }
                });
            }
        });

        this.dockButtonBar.add( button );
        this.dockPanels.put( name , panel );
        this.dockButtons.put( name , button );
    }

    private void showHidePanel( boolean showHide , DockPanel panel ) {
        if( showHide ) {
            if( this.getComponentCount()>1 )
                this.remove(1);
            this.add( panel , BorderLayout.CENTER );
            this.getRootPane().validate();
        } else {
            if( this.getComponentCount()>1 ) {
                this.remove(1);
                this.getRootPane().validate();
            }
        }
    }
}
