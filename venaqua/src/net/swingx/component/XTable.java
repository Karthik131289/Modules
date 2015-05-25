package net.swingx.component;

import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.alee.laf.table.WebTable;

public class XTable extends WebTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 801902060543158545L;

	public XTable() {
		// TODO Auto-generated constructor stub
	}

	public XTable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	public XTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public XTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	public XTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public XTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public XTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}

}
