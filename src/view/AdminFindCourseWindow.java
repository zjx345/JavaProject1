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
import vo.Student;
/**
 * 该类为管理员查找课程窗口
 * @author zjx
 *
 */
public class AdminFindCourseWindow extends JInternalFrame {

	private static AdminFindCourseWindow findCourseWindow=new AdminFindCourseWindow();
	private JTextField textField;
	ArrayList<Course> al; // 用来存放要显示的学生
	private int type=1; // 用来标识按什么字段名查找

	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminFindCourseWindow() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("\u67E5\u770B\u5B66\u751F");
		setBounds(10, 30, 900, 600);
		getContentPane().setLayout(null);
		// 创建表格，显示所有学生
		al=ServiceFactory.getIAdminServiceInstance().searchAllCourse();
		String[] colName= {"        课程号","        课程名","        课程简介","      教学大纲","        授课计划"};
		String[][] data=new String[100000][5];
		int i=0;
		for(Course course : al) {
			data[i][0]=course.getCno();
			data[i][1]=course.getCname();
			data[i][2]=course.getPresentation();
			data[i][3]=course.getSyllabus();
			data[i][4]=course.getPlan();
			i++;
		}
		Table.createTable(getContentPane(),colName, data);
		
		// 创建下拉列表、搜索框、搜索按钮
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 30, 106, 23);
		getContentPane().add(comboBox);
		comboBox.addItem("按课程号查询：");
		comboBox.addItem("按课程名查询：");
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
				}else if(e.getItem().equals("按课程名查询：")){
					type=2;
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
				al=ServiceFactory.getIAdminServiceInstance().searchCourse(textField.getText(), type);
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示部分课程
				for(Course course : al) {
					Table.model.addRow(new String[] {course.getCno(),course.getCname(),course.getPresentation(),course.getSyllabus(),course.getPlan()});
				}
			}
		});
		
		// 创建显示所有课程按钮
		JButton showAllCouButton = new JButton("显示所有课程");
		showAllCouButton.setBounds(500, 30, 120, 23);
		getContentPane().add(showAllCouButton);
		showAllCouButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				al=ServiceFactory.getIAdminServiceInstance().searchAllCourse();
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示所有学生
				for(Course course : al) {
					Table.model.addRow(new String[] {course.getCno(),course.getCname(),course.getPresentation(),course.getSyllabus(),course.getPlan()});
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminFindCourseWindow getInstance() {
		return findCourseWindow;
	}
}
