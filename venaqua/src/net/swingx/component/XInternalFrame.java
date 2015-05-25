package net.swingx.component;

import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.alee.laf.desktoppane.WebInternalFrame;

public class XInternalFrame extends WebInternalFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150151286979707963L;

	public XInternalFrame() {
		// TODO Auto-generated constructor stub
	}

	public XInternalFrame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public XInternalFrame(String title, boolean resizable) {
		super(title, resizable);
		// TODO Auto-generated constructor stub
	}

	public XInternalFrame(String title, boolean resizable, boolean closable) {
		super(title, resizable, closable);
		// TODO Auto-generated constructor stub
	}

	public XInternalFrame(String title, boolean resizable, boolean closable,
			boolean maximizable) {
		super(title, resizable, closable, maximizable);
		// TODO Auto-generated constructor stub
	}

	public XInternalFrame(String title, boolean resizable, boolean closable,
			boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		// TODO Auto-generated constructor stub
	}
	
	public void setFittoFullSize()
	{
		super.putClientProperty("InternalFrame.isPalette", Boolean.TRUE);
		super.getRootPane().setWindowDecorationStyle( JRootPane.NONE  );
		 ((BasicInternalFrameUI) super.getUI()).setNorthPane(null);
		 super.setBorder( new EmptyBorder(-1, -5, -5, -5));
	}

}
