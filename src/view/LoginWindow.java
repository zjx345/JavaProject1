package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import factory.ServiceFactory;
import vo.Admin;
import vo.Student;
/**
 * 该类为登陆窗口
 * @author zjx
 *
 */
public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userName_textField;
	private JPasswordField password_Field;
	private JButton register_Button;
	private JButton userLogin_Button;
	private JButton adminLogin_Button;
	private static LoginWindow loginWindow=new LoginWindow();
	
	/**
	 * 该私有方法为无参构造方法，用来初始化该窗口
	 */
	private LoginWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\\u5B66\u4E60\\java\\Java\u8BFE\u7A0B\u8BBE\u8BA1-\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF\\\u7D20\u6750\\\u56FE\u6807.jpg"));
		setTitle("在线课程学习系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		// 设置背景图片
		ImageIcon image1=new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\登陆界面壁纸.jpg");
		JLabel label=new JLabel(image1);
		label.setBounds(0, 0, image1.getIconWidth(), image1.getIconHeight());
		contentPane.add(label);
		
		// 设置窗体的大小
		this.setBounds(0, 0, image1.getIconWidth(), image1.getIconHeight());
		
		// 设置用户名和密码文本框
		userName_textField=new JTextField();
		userName_textField.setBounds(210, 218, 255, 26);
		label.add(userName_textField);
		userName_textField.setColumns(20);
		
		password_Field=new JPasswordField();
		password_Field.setBounds(210, 282, 255, 26);
		label.add(password_Field);
		password_Field.setColumns(20);
		
		// 设置注册按钮
		register_Button=new JButton("注  册");
		register_Button.setBounds(124, 357, 95, 23);
		label.add(register_Button);
		
		// 添加注册按钮事件
		register_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterWindow frame = RegisterWindow.getRegisterWindow();
				frame.setVisible(true);
			}
		});
		
		// 设置管理员登陆按钮
		adminLogin_Button=new JButton("管理员登陆");
		adminLogin_Button.setBounds(249, 357, 95, 23);
		label.add(adminLogin_Button);
		// 给管理员按钮添加事件
		adminLogin_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin admin=new Admin(userName_textField.getText(),password_Field.getText());
				// 如果用户存在，则登陆成功，否则提示用户名或密码错误
				if(ServiceFactory.getIAdminServiceInstance().login(admin)) {
					String lookAndFeel=UIManager.getSystemLookAndFeelClassName();
					try {
						UIManager.setLookAndFeel(lookAndFeel);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					AdminManagerWindow adw=new AdminManagerWindow(admin);
					adw.setVisible(true);
					loginWindow.dispose();
				}else {
					JOptionPane.showMessageDialog(loginWindow, "用户名或密码有误，请重新输入！");
				}
			}
		});
		
		// 设置用户登陆按钮
		userLogin_Button=new JButton("用户登陆");
		userLogin_Button.setBounds(374, 357, 95, 23);
		label.add(userLogin_Button);
		// 给用户登陆按钮添加事件
		userLogin_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Student stu=new Student(userName_textField.getText(),password_Field.getText(),"钟佳兴","吉首大学","大二","17347516760");
				// 如果用户存在，则登陆成功，否则提示用户名或密码错误
				if(ServiceFactory.getIStudentServiceInstance().login(stu)) {
					String lookAndFeel=UIManager.getSystemLookAndFeelClassName();
					try {
						UIManager.setLookAndFeel(lookAndFeel);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					MainWindow frame = MainWindow.getMainWindow();
					frame.setStudent(stu);
					frame.setVisible(true);
					loginWindow.dispose();
				}else {
					JOptionPane.showMessageDialog(loginWindow, "用户名或密码有误，请重新输入！");
				}
			}
		});
		
		// 光标闪烁
		userName_textField.requestFocus();
		
		setContentPane(contentPane);
	}
	
	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static LoginWindow getLoginWindow() {
		return loginWindow;
	}
}
