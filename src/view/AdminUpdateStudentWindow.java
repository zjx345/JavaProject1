package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Student;
/**
 * 该类用来创建修改学生窗口
 * @author zjx
 *
 */
public class AdminUpdateStudentWindow extends AdminFindStudentWindow {

	private static AdminUpdateStudentWindow updateStudentWindow=new AdminUpdateStudentWindow();
	private JButton updateButton; // 修改按钮
	private int selectedRow;   // 选中的行号 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminUpdateStudentWindow() {
		super();
		setTitle("修改学生信息");
		setBounds(10, 30, 1200, 600);
		updateButton=new JButton("点击修改");
		updateButton.setBounds(650, 30, 93, 23);
		getContentPane().add(updateButton);
		JLabel lblNewLabel = new JLabel("用 户 名：");
		lblNewLabel.setBounds(870, 126, 227, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6    \u7801\uFF1A");
		lblNewLabel_1.setBounds(870, 154, 62, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u59D3    \u540D\uFF1A");
		lblNewLabel_2.setBounds(870, 187, 65, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5B66\u6821\u540D\u79F0\uFF1A");
		lblNewLabel_3.setBounds(870, 218, 68, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E74    \u7EA7\uFF1A");
		lblNewLabel_4.setBounds(870, 247, 68, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		lblNewLabel_5.setBounds(870, 276, 65, 15);
		getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(930, 150, 211, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(930, 184, 211, 21);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(930, 215, 211, 21);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(930, 244, 211, 21);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(930, 273, 211, 21);
		getContentPane().add(textField_4);
		
		// 为点击修改按钮添加事件
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow=Table.table.getSelectedRow();
				Student oldStu=new Student((String)Table.table.getValueAt(selectedRow, 0),(String)Table.table.getValueAt(selectedRow, 1),(String)Table.table.getValueAt(selectedRow, 2),(String)Table.table.getValueAt(selectedRow, 3),(String)Table.table.getValueAt(selectedRow, 4),(String)Table.table.getValueAt(selectedRow, 5));
				
				// 清空文本框
				lblNewLabel.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				
				// 给文本框赋值
				lblNewLabel.setText("用户名："+oldStu.getAccount());
				textField.setText(oldStu.getPassword());
				textField_1.setText(oldStu.getName());
				textField_2.setText(oldStu.getSchool());
				textField_3.setText(oldStu.getGrade());
				textField_4.setText(oldStu.getPhone());
			}
		});
		
		// 添加确定修改按钮
		JButton submitButton=new JButton("确定修改");
		submitButton.setBounds(990, 330, 93, 23);
		getContentPane().add(submitButton);
		// 添加按钮事件
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Student newStu=new Student(lblNewLabel.getText().substring(4),textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText());
				if(ServiceFactory.getIAdminServiceInstance().updateStudent(newStu)) {
					Table.table.setValueAt(newStu.getPassword(), selectedRow, 1);
					Table.table.setValueAt(newStu.getName(), selectedRow, 2);
					Table.table.setValueAt(newStu.getSchool(), selectedRow, 3);
					Table.table.setValueAt(newStu.getGrade(), selectedRow, 4);
					Table.table.setValueAt(newStu.getPhone(), selectedRow, 5);
					Table.model.fireTableDataChanged();
					JOptionPane.showMessageDialog(updateStudentWindow, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(updateStudentWindow, "修改失败！");
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminUpdateStudentWindow getInstance() {
		return updateStudentWindow;
	}
}
