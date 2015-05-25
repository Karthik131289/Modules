package net.swingx.component;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JLabel;

import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;

public class XImageCheckBox extends WebPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3767558822786398201L;
	
	private static final ButtonGroup grp = new ButtonGroup();
	private WebCheckBox CheckBox;
    private WebLabel label;

    public XImageCheckBox() 
    {
        setLayout(new BorderLayout());
        setUndecorated(false);
        setRound(5);
        setMargin(5);
        add(getLabel(),BorderLayout.CENTER);
        add(getCheckBox(),BorderLayout.NORTH);
    }

    public XImageCheckBox(Icon icon, String text) {
        this();
        setIcon(icon);
        setText(text);
    }

    protected WebCheckBox getCheckBox() {
        if (CheckBox == null) {
            CheckBox = new WebCheckBox();
            CheckBox.setHorizontalTextPosition( JLabel.LEADING );
            grp.add(CheckBox);
        }
        return CheckBox;
    }

    protected JLabel getLabel() {
        if (label == null) {
            label = new WebLabel();
        }
        return label;
    }

    public void addActionListener(ActionListener listener) {
        getCheckBox().addActionListener(listener);
    }

    public void removeActionListener(ActionListener listener) {
        getCheckBox().removeActionListener(listener);
    }

    public void setText(String text) {
    	getCheckBox().setText(text);
    }

    public String getText() {
        return getCheckBox().getText();
    }

    public void setIcon(Icon icon) {
        getLabel().setIcon(icon);
    }

    public Icon getIcon() {
        return getLabel().getIcon();
    }

}
