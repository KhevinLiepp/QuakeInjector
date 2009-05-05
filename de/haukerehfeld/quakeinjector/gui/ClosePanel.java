package de.haukerehfeld.quakeinjector.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The typical okay/cancel area
 */
public class ClosePanel extends JPanel {
	public ClosePanel(final JDialog dialog, final ActionListener action) {
		super();
		setLayout(new GridLayout(0,2));
	
		add(makeCloseButton(dialog, "Okay", action));
		add(makeCloseButton(dialog, "Cancel"));
	}
		
	public static JButton makeCloseButton(final JDialog dialog,
									   final String text) {
		return makeCloseButton(dialog, text, null);
	}

	/**
	 * Make a button that closes the dialog and executes another action.
	 * 
	 * You still have to add it to the frame.
	 *
	 * Action may be null.
	 */
	public static JButton makeCloseButton(final JDialog dialog,
									   final String text,
									   final ActionListener action) {
		JButton okay = new JButton(text);
		okay.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
					if (action != null) {
						action.actionPerformed(e);
					}
                    dialog.setVisible(false);
                    dialog.dispose();
                }
            });
		return okay;
	}

	
}