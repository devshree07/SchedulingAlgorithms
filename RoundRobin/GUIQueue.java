import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ListIterator;

import javax.swing.border.BevelBorder;

public class GUIQueue {
	JTable table;
	public GUIQueue() {
	
		  JFrame frame = new JFrame("Setting Cell Values in JTable");
		  JPanel panel = new JPanel();
		  panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  panel.setBounds(0, 0, 500, 332);
		  String data[][] = {{"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""}/*,
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""},
		   {"","",""}*/};
		  String col[] = {"New Queue","Ready Queue","Block Queue"};  
		  DefaultTableModel model = new DefaultTableModel(data, col);
		  table = new JTable(model);
		  JTableHeader header = table.getTableHeader();
		  header.setBackground(Color.yellow);
		  frame.getContentPane().setLayout(null);
		  JScrollPane pane = new JScrollPane(table);
		  panel.add(pane);
		  frame.getContentPane().add(panel);
		  frame.setSize(800,300);
		  frame.setUndecorated(true);
		  frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		  frame.setVisible(false);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void SetData(Object obj, int row_index, int col_index){
		  table.getModel().setValueAt(obj,row_index,col_index);
		  //System.out.println("Value is added");
	}
	
	public void queue(ListIterator<Process> iterator,GUIQueue gui_queue) {
		int i=0;
		while (iterator.hasNext()) { 
			gui_queue.SetData("Process "+iterator.next().pid, i,1);
			i++;
        } 
		i=0;
	}
}



