import comp.DockPanel;
import comp.DockWindow;
import comp.XButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DV21 on 12-05-2015.
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        initUI();
    }

    private void initUI() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setPreferredSize(new Dimension(300, 200));
        this.setLayout( new BorderLayout() );
        {
            DockWindow window = new DockWindow();
            this.add( window , BorderLayout.SOUTH );
            {
                DockPanel panel1 = new DockPanel();
                panel1.setBorder( new LineBorder( Color.DARK_GRAY , 1 ) );
                {
                    JLabel label = new JLabel("Panel - 1...");
                    panel1.add(label, BorderLayout.CENTER);
                }

                DockPanel panel2 = new DockPanel();
                panel2.setBorder( new LineBorder( Color.DARK_GRAY , 1 ) );
                {
                    JLabel label = new JLabel("Panel - 2...");
                    panel2.add(label, BorderLayout.CENTER);
                }

                window.addDockPanel( "Panel-1" , panel1 );
                window.addDockPanel( "Panel-2" , panel2 );
            }
        }
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation( (int)screenDim.getWidth() - (int)this.getPreferredSize().getWidth() , (int)screenDim.getHeight()-(int)this.getPreferredSize().getHeight()-50 );
        this.setVisible( true );
        this.pack();
    }

    private JFrame getFrame() {
        return this;
    }

    private void showDockWindow()
    {
        final JPanel dockWindow = new JPanel( new BorderLayout() , true );
        this.add( dockWindow , BorderLayout.SOUTH );
        {
            JToolBar buttonBar = new JToolBar( JToolBar.HORIZONTAL );
            buttonBar.setBorderPainted( true );
            buttonBar.setFloatable(false);
            buttonBar.setBorder( new LineBorder( Color.DARK_GRAY , 1 ) );
            dockWindow.add( buttonBar , BorderLayout.SOUTH );
            {
                XButton but1 = new XButton( "Button-1" );
                but1.addActionListener( new ActionListener() {
                    boolean state = false;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        state = !state;
                        if( state ) {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("Button-1 action...");

                                    JPanel dockPane = new JPanel(new BorderLayout());
                                    dockPane.setBorder( new LineBorder( Color.DARK_GRAY , 1 ) );
                                    dockWindow.add(dockPane, BorderLayout.CENTER);
                                    {
                                        JLabel label = new JLabel("Test...");
                                        dockPane.add(label, BorderLayout.CENTER);
                                    }

                                    for (int i = 0; i < dockWindow.getComponentCount(); i++) {
                                        Component comp = dockWindow.getComponent(i);
                                        System.out.println(comp);
                                        if (comp instanceof JPanel) {
                                            for (int j = 0; j < ((JPanel) comp).getComponentCount(); j++)
                                                System.out.println(((JPanel) comp).getComponent(j));
                                        }
                                    }

                                    dockWindow.getRootPane().validate();
                                    //dockWindow.getParent().validate();
                                }
                            });
                        } else {
                            SwingUtilities.invokeLater( new Runnable() {
                                @Override
                                public void run() {
                                    dockWindow.remove( 1 );

                                    for (int i = 0; i < dockWindow.getComponentCount(); i++) {
                                        Component comp = dockWindow.getComponent(i);
                                        System.out.println(comp);
                                        if (comp instanceof JPanel) {
                                            for (int j = 0; j < ((JPanel) comp).getComponentCount(); j++)
                                                System.out.println(((JPanel) comp).getComponent(j));
                                        }
                                    }

                                    dockWindow.getRootPane().validate();
                                    //dockWindow.getParent().validate();
                                }
                            });
                        }
                    }
                });
                buttonBar.add(but1);

                XButton but2 = new XButton( "Button-2" );
                but2.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println( "Button-2 action..." );
                    }
                });
                buttonBar.add(but2);
            }
        }
    }
}
