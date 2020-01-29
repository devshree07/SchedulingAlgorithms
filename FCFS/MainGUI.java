import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

public class MainGUI {
	JTable table;
	public MainGUI() {
	
		  JFrame frame = new JFrame("Setting Cell Values in JTable");
		  JPanel panel = new JPanel();
		  panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		  panel.setBounds(0, 0, 500, 332);
		  String data[][] = {{"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""}/*,
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""},
		   {"","","",""}*/};
		  String col[] = {"Process id","Process type","Status","Counts"};  
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
		  frame.setVisible(true);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void SetData(Object obj, int row_index, int col_index){
		  table.getModel().setValueAt(obj,row_index,col_index);
		  //System.out.println("Value is added");
		  }
}



