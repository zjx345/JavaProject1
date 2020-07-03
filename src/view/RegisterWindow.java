package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import factory.ServiceFactory;
import vo.Student;
/**
 * 该类为注册界面
 * @author zjx
 *
 */
public class RegisterWindow extends JFrame {

	private LoginWindow loginWindow;
	private JPanel contentPane;
	private JTextField userName_textField;
	private JPasswordField password_Field;
	private JTextField email_textField;
	private JButton cancel_Button;
	private JButton submit_Button;
	private static RegisterWindow registerWindow=new RegisterWindow();

	/**
	 * 该私有方法为无参构造方法，用来初始化该窗口
	 */
	private RegisterWindow() {
		this.loginWindow=LoginWindow.getLoginWindow();
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\\u5B66\u4E60\\java\\Java\u8BFE\u7A0B\u8BBE\u8BA1-\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF\\\u7D20\u6750\\\u56FE\u6807.jpg"));
		setTitle("用户注册");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		this.setBounds(200, 100, 800, 500);
		
		// 设置背景图片
		ImageIcon image1=new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\注册界面壁纸.jpg");
		JLabel label=new JLabel(image1);
		label.setBounds(0, 0, image1.getIconWidth(), image1.getIconHeight());
		contentPane.add(label);
		
		// 设置用户名和密码文本框
		JLabel userNameLabel=new JLabel("用户名：");
		userNameLabel.setBounds(100, 115, 130, 105);
		label.add(userNameLabel);
		userName_textField=new JTextField();
		userName_textField.setBounds(150, 158, 200, 20);
		label.add(userName_textField);
		userName_textField.setColumns(20);
		
		JLabel passwordLabel=new JLabel("密   码：");
		passwordLabel.setBounds(100, 145, 130, 105);
		label.add(passwordLabel);
		password_Field=new JPasswordField();
		password_Field.setBounds(150, 188, 200, 20);
		label.add(password_Field);
		password_Field.setColumns(20);
		
		JLabel emailLabel=new JLabel("邮   箱：");
		emailLabel.setBounds(100, 175, 130, 105);
		label.add(emailLabel);
		email_textField=new JTextField();
		email_textField.setBounds(150, 218, 200, 20);
		label.add(email_textField);
		email_textField.setColumns(20);
		
		cancel_Button=new JButton("返    回");
		cancel_Button.setBounds(150, 250, 80, 20);
		label.add(cancel_Button);
		// 为返回按钮增加事件
		cancel_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerWindow.dispose();
				loginWindow.setVisible(true);
			}
		});
		
		submit_Button=new JButton("确    定");
		submit_Button.setBounds(270, 250, 80, 20);
		label.add(submit_Button);
		// 为确定按钮增加事件
		submit_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Student stu=new Student(userName_textField.getText(),password_Field.getText(),"钟佳兴","吉首大学","大二","17347516760");
				// 判断用户名是否在数据库中存在，如果存在，则提示用户，否则把用户插入到数据库
				if(ServiceFactory.getIStudentServiceInstance().login(stu)) {
					JOptionPane.showMessageDialog(registerWindow, "该用户名已存在！");
				}else {
					// 提示框
					ServiceFactory.getIStudentServiceInstance().register(stu);
					JOptionPane.showMessageDialog(registerWindow, "注册成功！");
					// 清空文本框
					userName_textField.setText("");
					password_Field.setText("");
					email_textField.setText("");
				}
			}
		});
		
		setContentPane(contentPane);
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static RegisterWindow getRegisterWindow() {
		return registerWindow;
	}
}
