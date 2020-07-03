package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Course;
import vo.Courseware;
/**
 * 该类为管理员查找课件窗口
 * @author zjx
 *
 */
public class AdminFindCoursewareWindow extends JInternalFrame {

	private static AdminFindCoursewareWindow findCoursewareWindow=new AdminFindCoursewareWindow();
	private JTextField textField;
	ArrayList<Courseware> al; // 用来存放要显示的课件
	private int type=1; // 用来标识按什么字段名查找

	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminFindCoursewareWindow() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("\u67E5\u770B\u5B66\u751F");
		setBounds(10, 30, 900, 600);
		getContentPane().setLayout(null);
		// 创建表格，显示所有学生
		al=ServiceFactory.getIAdminServiceInstance().searchAllCourseware();
		String[] colName= {"        课件号","        课程号","        课件名","      课件路径","        课件大小"};
		String[][] data=new String[100000][5];
		int i=0;
		for(Courseware courseware : al) {
			data[i][0]=courseware.getCnum();
			data[i][1]=courseware.getCid();
			data[i][2]=courseware.getKname();
			data[i][3]=courseware.getCpath();
			data[i][4]=courseware.getSize();
			i++;
		}
		Table.createTable(getContentPane(),colName, data);
		
		// 创建下拉列表、搜索框、搜索按钮
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 30, 106, 23);
		getContentPane().add(comboBox);
		comboBox.addItem("按课程号查询：");
		comboBox.addItem("按课件号查询：");
		comboBox.addItem("按课件名查询：");
		textField = new JTextField();
		textField.setBounds(141, 30, 212, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		// 为下拉列表增加事件
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals("按课程号查询：")) {
					type=1;
				}else if(e.getItem().equals("按课件号查询：")){
					type=2;
				}else if(e.getItem().equals("按课件名查询：")) {
					type=3;
				}
			}
		});
		JButton btnNewButton = new JButton("\u67E5    \u8BE2 ");
		btnNewButton.setBounds(368, 30, 93, 23);
		getContentPane().add(btnNewButton);
		// 为搜索按钮添加事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				al=ServiceFactory.getIAdminServiceInstance().searchCourseware(textField.getText(), type);
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示部分课件
				for(Courseware courseware : al) {
					Table.model.addRow(new String[] {courseware.getCnum(),courseware.getCid(),courseware.getKname(),courseware.getCpath(),courseware.getSize()});
				}
			}
		});
		
		// 创建显示所有课件按钮
		JButton showAllCouButton = new JButton("显示所有课件");
		showAllCouButton.setBounds(500, 30, 120, 23);
		getContentPane().add(showAllCouButton);
		showAllCouButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				al=ServiceFactory.getIAdminServiceInstance().searchAllCourseware();
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示所有学生
				for(Courseware courseware : al) {
					Table.model.addRow(new String[] {courseware.getCnum(),courseware.getCid(),courseware.getKname(),courseware.getCpath(),courseware.getSize()});
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminFindCoursewareWindow getInstance() {
		return findCoursewareWindow;
	}
}
