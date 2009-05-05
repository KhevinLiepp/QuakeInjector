package de.haukerehfeld.quakeinjector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.util.GregorianCalendar;

import java.util.*;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;

public class MapList extends AbstractTableModel implements ChangeListener {
	private ChangeListenerList listeners = new ChangeListenerList();
	
	private ArrayList<MapInfo> data;

	private String[] columnNames = {"Name",
									"Title",
									"Author",
									"Releasedate",
									"Installed"};

	public MapList() {
		data = new ArrayList<MapInfo>();
	}

	public MapList(ArrayList<MapInfo> data) {
		setMapList(data);
	}


	public void setMapList(ArrayList<MapInfo> data) {
		this.data = data;

		for (MapInfo m: data) {
			m.addChangeListener(this);
		}

		fireTableChanged(new TableModelEvent(this));
	}

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

	/*
	 * data
	 */
	
    public int getRowCount() {
        return data.size();
    }

	public Object getColumnData(int col, MapInfo info) {
		switch (col) {
		case 0: return info.getId();
		case 1: return info.getTitle();
		case 2: return info.getAuthor();
		case 3: return info.getDate();
		case 4: return new Boolean(info.isInstalled());
			/*
			 * Should never be used
			 */
		default: throw new RuntimeException("This should never happen: check the switch statement above");
		}
	}

    public Object getValueAt(int row, int col) {
        return getColumnData(col, data.get(row));
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
		return false;
    }

	public MapInfo getMapInfo(int row) {
		return data.get(row);
	}

	public void addChangeListener(ChangeListener l) {
		listeners.addChangeListener(l);
	}

	public void stateChanged(ChangeEvent e) {
		listeners.notifyChangeListeners(e.getSource());

		int i = data.indexOf((MapInfo) e.getSource());
		super.fireTableRowsUpdated(i, i);
		
	}


//     /*
//      * Don't need to implement this method unless your table's
//      * data can change.
//      */
//     public void setValueAt(Object value, int row, int col) {
//         data[row][col] = value;
//         fireTableCellUpdated(row, col);
//     }{

}