package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Student;
/**
 * 该类为管理员查找学生窗口
 * @author zjx
 *
 */
public class AdminFindStudentWindow extends JInternalFrame {
	
	private static AdminFindStudentWindow findStudentWindow=new AdminFindStudentWindow();
	private JTextField textField;
	TreeSet<Student> ts; // 用来存放要显示的学生
	private int type=1; // 用来标识按什么字段名查找

	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminFindStudentWindow() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("\u67E5\u770B\u5B66\u751F");
		setBounds(10, 30, 900, 600);
		getContentPane().setLayout(null);
		// 创建表格，显示所有学生
		ts=ServiceFactory.getIAdminServiceInstance().searchAllStudent();
		String[] colName= {"        账号","        密码","        姓名","      学校名称","        年级","      手机号码"};
		String[][] data=new String[100000][6];
		int i=0;
		for(Student stu : ts) {
			data[i][0]=stu.getAccount();
			data[i][1]=stu.getPassword();
			data[i][2]=stu.getName();
			data[i][3]=stu.getSchool();
			data[i][4]=stu.getGrade();
			data[i][5]=stu.getPhone();
			i++;
		}
		Table.createTable(getContentPane(),colName, data);
		
		// 创建下拉列表、搜索框、搜索按钮
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 30, 106, 23);
		getContentPane().add(comboBox);
		comboBox.addItem("按账号查询：");
		comboBox.addItem("按姓名查询：");
		textField = new JTextField();
		textField.setBounds(141, 30, 212, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		// 为下拉列表增加事件
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals("按账号查询：")) {
					type=1;
				}else if(e.getItem().equals("按姓名查询：")){
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
				ts=ServiceFactory.getIAdminServiceInstance().searchStudent(textField.getText(), type);
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示部分学生
				for(Student stu : ts) {
					Table.model.addRow(new String[] {stu.getAccount(),stu.getPassword(),stu.getName(),stu.getSchool(),stu.getGrade(),stu.getPhone()});
				}
			}
		});
		
		// 创建显示所有学生按钮
		JButton showAllStuButton = new JButton("显示所有学生");
		showAllStuButton.setBounds(500, 30, 120, 23);
		getContentPane().add(showAllStuButton);
		showAllStuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts=ServiceFactory.getIAdminServiceInstance().searchAllStudent();
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示所有学生
				for(Student stu : ts) {
					Table.model.addRow(new String[] {stu.getAccount(),stu.getPassword(),stu.getName(),stu.getSchool(),stu.getGrade(),stu.getPhone()});
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminFindStudentWindow getInstance() {
		return findStudentWindow;
	}
}
