package de.haukerehfeld.quakeinjector;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;

class ShowMapInfoSelectionHandler implements ListSelectionListener {
	private MapInfoPanel panel;

	private MapList list;

	public ShowMapInfoSelectionHandler(MapInfoPanel panel, MapList list) {
		this.panel = panel;
		this.list = list;
	}

	/**
	 * Listen on selection changes
	 */
	public void valueChanged(ListSelectionEvent e) { 
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

		if (!lsm.isSelectionEmpty()) {
			int selection = getSelection(lsm);
			setMapInfo(selection);
		}
	}

	/**
	 * Tell everyone about the selection change
	 */
	private void setMapInfo(int selection) {
		panel.setMapInfo(list.getMapInfo(selection));
	}

	/**
	 * Find out what entry was selected
	 */
	public int getSelection(ListSelectionModel lsm) {
		int selection = -1;
		// Find out which indexes are selected.
		int minIndex = lsm.getMinSelectionIndex();
		int maxIndex = lsm.getMaxSelectionIndex();
		for (int i = minIndex; i <= maxIndex; i++) {
			if (lsm.isSelectedIndex(i)) {
				selection = i;
				break;
			}
		}
		return selection;
	}
}

