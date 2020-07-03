package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Courseware;
import vo.Student;
/**
 * 该类为管理员修改课件窗口
 * @author zjx
 *
 */
public class AdminUpdateCoursewareWindow extends AdminFindCoursewareWindow {

	private static AdminUpdateCoursewareWindow updateCoursewareWindow=new AdminUpdateCoursewareWindow();
	private JButton updateButton; // 修改按钮
	private int selectedRow;   // 选中的行号 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminUpdateCoursewareWindow() {
		super();
		setTitle("修改课件信息");
		setBounds(10, 30, 1200, 600);
		updateButton=new JButton("点击修改");
		updateButton.setBounds(650, 30, 93, 23);
		getContentPane().add(updateButton);
		JLabel lblNewLabel = new JLabel("课 件 号：");
		lblNewLabel.setBounds(870, 126, 227, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程号：");
		lblNewLabel_1.setBounds(870, 154, 62, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("课件名：");
		lblNewLabel_2.setBounds(870, 187, 65, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("课件路径：");
		lblNewLabel_3.setBounds(870, 218, 68, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("课件大小：");
		lblNewLabel_4.setBounds(870, 247, 68, 15);
		getContentPane().add(lblNewLabel_4);
		
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
		
		// 为点击修改按钮添加事件
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow=Table.table.getSelectedRow();
				Courseware oldCou=new Courseware((String)Table.table.getValueAt(selectedRow, 1),(String)Table.table.getValueAt(selectedRow, 0),(String)Table.table.getValueAt(selectedRow, 2),(String)Table.table.getValueAt(selectedRow, 3),(String)Table.table.getValueAt(selectedRow, 4));
				
				// 清空文本框
				lblNewLabel.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
				// 给文本框赋值
				lblNewLabel.setText("课件号："+oldCou.getCnum());
				textField.setText(oldCou.getCid());
				textField_1.setText(oldCou.getKname());
				textField_2.setText(oldCou.getCpath());
				textField_3.setText(oldCou.getSize());
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
				Courseware newCou=new Courseware(lblNewLabel.getText().substring(4),textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText());
				if(ServiceFactory.getIAdminServiceInstance().updateCourseware(newCou)) {
					Table.table.setValueAt(newCou.getCid(), selectedRow, 1);
					Table.table.setValueAt(newCou.getKname(), selectedRow, 2);
					Table.table.setValueAt(newCou.getCpath(), selectedRow, 3);
					Table.table.setValueAt(newCou.getSize(), selectedRow, 4);
					Table.model.fireTableDataChanged();
					JOptionPane.showMessageDialog(updateCoursewareWindow, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(updateCoursewareWindow, "修改失败！");
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminUpdateCoursewareWindow getInstance() {
		return updateCoursewareWindow;
	}
}
