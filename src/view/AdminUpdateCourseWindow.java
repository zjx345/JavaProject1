package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Course;
import vo.Student;
/**
 * 该类用来创建管理员修改课程窗口
 * @author zjx
 *
 */
public class AdminUpdateCourseWindow extends AdminFindCourseWindow {
	private static AdminUpdateCourseWindow updateCourseWindow=new AdminUpdateCourseWindow();
	private JButton updateButton; // 修改按钮
	private int selectedRow;   // 选中的行号 
	private JTextField textField;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextArea textArea3;
	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminUpdateCourseWindow() {
		super();
		setTitle("修改课程信息");
		setBounds(10, 30, 1200, 600);
		updateButton=new JButton("点击修改");
		updateButton.setBounds(650, 30, 93, 23);
		getContentPane().add(updateButton);
		JLabel lblNewLabel = new JLabel("课 程 号：");
		lblNewLabel.setBounds(870, 126, 227, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程名：");
		lblNewLabel_1.setBounds(870, 154, 62, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("课程简介：");
		lblNewLabel_2.setBounds(870, 230, 65, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("教学大纲：");
		lblNewLabel_3.setBounds(870, 315, 68, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("授课计划：");
		lblNewLabel_4.setBounds(870, 400, 68, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(930, 150, 211, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea1 = new JTextArea();
		textArea1.setLineWrap(true);
		textArea1.setBounds(930, 215, 211, 50);
		getContentPane().add(textArea1);
		
		textArea2 = new JTextArea();
		textArea2.setLineWrap(true);
		textArea2.setBounds(930, 300, 211, 50);
		getContentPane().add(textArea2);
		
		textArea3 = new JTextArea();
		textArea3.setLineWrap(true);
		textArea3.setBounds(930, 385, 211, 50);
		getContentPane().add(textArea3);
		
		// 为点击修改按钮添加事件
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow=Table.table.getSelectedRow();
				Course oldCou=new Course((String)Table.table.getValueAt(selectedRow, 0),(String)Table.table.getValueAt(selectedRow, 1),(String)Table.table.getValueAt(selectedRow, 2),(String)Table.table.getValueAt(selectedRow, 3),(String)Table.table.getValueAt(selectedRow, 4));
				
				// 清空文本框
				lblNewLabel.setText("");
				textField.setText("");
				textArea1.setText("");
				textArea2.setText("");
				textArea3.setText("");
				
				// 给文本框赋值
				lblNewLabel.setText("课程号："+oldCou.getCno());
				textField.setText(oldCou.getCname());
				textArea1.setText(oldCou.getPresentation());
				textArea2.setText(oldCou.getSyllabus());
				textArea3.setText(oldCou.getPlan());
			}
		});
		
		// 添加确定修改按钮
		JButton submitButton=new JButton("确定修改");
		submitButton.setBounds(990, 470, 93, 23);
		getContentPane().add(submitButton);
		// 添加按钮事件
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course newCou=new Course(lblNewLabel.getText().substring(4),textField.getText(),textArea1.getText(),textArea2.getText(),textArea3.getText());
				if(ServiceFactory.getIAdminServiceInstance().updateCourse(newCou)) {
					Table.table.setValueAt(newCou.getCname(), selectedRow, 1);
					Table.table.setValueAt(newCou.getPresentation(), selectedRow, 2);
					Table.table.setValueAt(newCou.getSyllabus(), selectedRow, 3);
					Table.table.setValueAt(newCou.getPlan(), selectedRow, 4);
					Table.model.fireTableDataChanged();
					JOptionPane.showMessageDialog(updateCourseWindow, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(updateCourseWindow, "修改失败！");
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminUpdateCourseWindow getInstance() {
		return updateCourseWindow;
	}
}
