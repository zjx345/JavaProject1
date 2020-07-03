package tool;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 * 该类为表格工具类
 * @author Lenovo
 *
 */
public class Table {
	
	public static DefaultTableModel model;
	public static JTable table;
	
	/**
	 * 该静态方法用来创建表格
	 * @param contentpane 存放表格的容器
	 * @param colName 列名
	 * @param data 表格数据
	 */
	public static void createTable(Container contentpane,String[] colName,Object[][] data) {
		model=new DefaultTableModel(data,colName);
		table=new JTable(model);
		table.setBounds(50, 80, 605, 190);
		// 表格数据居中显示
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		// 给表格加上滚动条
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(50, 80, 800, 460);
		contentpane.add(jsp);
		jsp.setVisible(true);
	}
}
