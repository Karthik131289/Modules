package net.swingx.component;

import java.util.Date;

import com.alee.extended.date.WebDateField;

public class XDateField extends WebDateField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8432990635217903148L;

	public XDateField() {
		// TODO Auto-generated constructor stub
	}

	public XDateField(boolean drawBorder) {
		super(drawBorder);
		// TODO Auto-generated constructor stub
	}

	public XDateField(Date date) {
		super(date);
		// TODO Auto-generated constructor stub
	}

	public XDateField(Date date, boolean drawBorder) {
		super(date, drawBorder);
		// TODO Auto-generated constructor stub
	}
	
	public void hidePopup() {
		super.hideCalendarPopup();
	}

}
