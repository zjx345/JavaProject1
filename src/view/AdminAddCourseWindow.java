package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import factory.ServiceFactory;
import vo.Course;
/**
 * 该类为管理员增加课程窗口
 * @author zjx
 *
 */
public class AdminAddCourseWindow extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextArea textArea3;
	private static AdminAddCourseWindow addCourseWindow=new AdminAddCourseWindow();
	/**
	 * 该私有方法为无参构造方法，用来初始化窗口
	 */
	private AdminAddCourseWindow() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("添加课程信息");
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("课程号：");
		lblNewLabel.setBounds(92, 33, 60, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程名：");
		lblNewLabel_1.setBounds(92, 60, 60, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("课程简介：");
		lblNewLabel_2.setBounds(92, 112, 60, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("教学大纲：");
		lblNewLabel_3.setBounds(92, 203, 60, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("授课计划：");
		lblNewLabel_4.setBounds(92, 280, 60, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(160, 30, 300, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 57, 300, 21);
		getContentPane().add(textField_1);
		
		textArea1 = new JTextArea();
		textArea1.setBounds(160, 85, 300, 72);
		textArea1.setLineWrap(true);
		getContentPane().add(textArea1);
		
		textArea2 = new JTextArea();
		textArea2.setBounds(160, 168, 300, 72);
		textArea2.setLineWrap(true);
		getContentPane().add(textArea2);
		
		textArea3 = new JTextArea();
		textArea3.setBounds(160, 251, 300, 72);
		textArea2.setLineWrap(true);
		getContentPane().add(textArea3);
		
		// 取消按钮
		JButton btnNewButton = new JButton("\u53D6    \u6D88");
		btnNewButton.setBounds(135, 345, 93, 23);
		getContentPane().add(btnNewButton);
		// 添加取消按钮事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCourseWindow.dispose();
			}
		});
		
		// 添加按钮
		JButton btnNewButton_1 = new JButton("\u6DFB    \u52A0");
		btnNewButton_1.setBounds(407, 345, 93, 23);
		getContentPane().add(btnNewButton_1);
				
		// 为按钮添加事件
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cid=textField.getText();
				String cname=textField_1.getText();
				String presentation=textArea1.getText();
				String syllabus=textArea2.getText();
				String plan=textArea3.getText();
				Course course=new Course(cid,cname,presentation,syllabus,plan);
				if(cid.equals("")||cname.equals("")||presentation.equals("")||syllabus.equals("")||plan.equals("")) {
					JOptionPane.showMessageDialog(addCourseWindow, "有文本框内容为空，请补充！");
				}else if(ServiceFactory.getIAdminServiceInstance().isExist(course)) {
					JOptionPane.showMessageDialog(addCourseWindow, "该课程号已存在，请更换课程号！");
				}else {
					ServiceFactory.getIAdminServiceInstance().addCourse(course);
					JOptionPane.showMessageDialog(addCourseWindow, "添加成功！");
				}
			}
		});
	}
	
	/**
	 * 该静态方法取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminAddCourseWindow getInstance() {
		return addCourseWindow;
	}
}
