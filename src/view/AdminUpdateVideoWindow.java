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
import vo.Video;
/**
 * 该类用来创建管理员修改视频窗口
 * @author zjx
 *
 */
public class AdminUpdateVideoWindow extends AdminFindVideoWindow {

	private static AdminUpdateVideoWindow updateVideoWindow=new AdminUpdateVideoWindow();
	private JButton updateButton; // 修改按钮
	private int selectedRow;   // 选中的行号 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * 该方法为无参构造方法，用来初始化该窗口
	 */
	public AdminUpdateVideoWindow() {
		super();
		setTitle("修改视频信息");
		setBounds(10, 30, 1200, 600);
		updateButton=new JButton("点击修改");
		updateButton.setBounds(650, 30, 93, 23);
		getContentPane().add(updateButton);
		JLabel lblNewLabel = new JLabel("视 频 号：");
		lblNewLabel.setBounds(870, 126, 227, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程号：");
		lblNewLabel_1.setBounds(870, 154, 62, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("视频名：");
		lblNewLabel_2.setBounds(870, 187, 65, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("视频路径：");
		lblNewLabel_3.setBounds(870, 218, 68, 15);
		getContentPane().add(lblNewLabel_3);
		
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
		
		// 为点击修改按钮添加事件
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRow=Table.table.getSelectedRow();
				Video oldVideo=new Video((String)Table.table.getValueAt(selectedRow, 1),(String)Table.table.getValueAt(selectedRow, 0),(String)Table.table.getValueAt(selectedRow, 2),(String)Table.table.getValueAt(selectedRow, 3));
				
				// 清空文本框
				lblNewLabel.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				
				// 给文本框赋值
				lblNewLabel.setText("视频号："+oldVideo.getVnum());
				textField.setText(oldVideo.getCid());
				textField_1.setText(oldVideo.getVname());
				textField_2.setText(oldVideo.getVpath());
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
				Video newVideo=new Video(lblNewLabel.getText().substring(4),textField.getText(),textField_1.getText(),textField_2.getText());
				if(ServiceFactory.getIAdminServiceInstance().updateVideo(newVideo)) {
					Table.table.setValueAt(newVideo.getCid(), selectedRow, 1);
					Table.table.setValueAt(newVideo.getVname(), selectedRow, 2);
					Table.table.setValueAt(newVideo.getVpath(), selectedRow, 3);
					Table.model.fireTableDataChanged();
					JOptionPane.showMessageDialog(updateVideoWindow, "修改成功！");
				}else {
					JOptionPane.showMessageDialog(updateVideoWindow, "修改失败！");
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminUpdateVideoWindow getInstance() {
		return updateVideoWindow;
	}
}
