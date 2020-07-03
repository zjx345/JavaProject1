package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import factory.ServiceFactory;
import vo.Admin;
/**
 * 该类用来创建管理员修改密码窗口
 * @author zjx
 *
 */
public class AdminUpdatePasswordWindow extends JInternalFrame {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private static AdminUpdatePasswordWindow updatePasswordWindow=new AdminUpdatePasswordWindow();
	private Admin admin;
	/**
	 * 该私有方法为无参构造方法，用来初始化该窗口
	 */
	private AdminUpdatePasswordWindow() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u65E7 \u5BC6 \u7801\uFF1A");
		lblNewLabel.setBounds(77, 56, 65, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0 \u5BC6 \u7801\uFF1A");
		lblNewLabel_1.setBounds(79, 92, 81, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(80, 123, 62, 15);
		getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 53, 203, 21);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(139, 90, 203, 21);
		getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(140, 123, 203, 21);
		getContentPane().add(passwordField_2);
		
		JButton cancelButton = new JButton("\u53D6     \u6D88");
		cancelButton.setBounds(80, 196, 93, 23);
		getContentPane().add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePasswordWindow.dispose();
			}
		});
		
		JButton submitButton = new JButton("\u786E    \u5B9A");
		submitButton.setBounds(248, 195, 93, 23);
		getContentPane().add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(admin.getPassword().equals(passwordField.getText())) {
					if(passwordField_1.getText().equals(passwordField_2.getText())) {
						if(ServiceFactory.getIAdminServiceInstance().updatePassword(admin, passwordField_1.getText())) {
							JOptionPane.showMessageDialog(updatePasswordWindow, "修改成功！");
						}else {
							JOptionPane.showMessageDialog(updatePasswordWindow, "修改失败！");
						}
					}else {
						JOptionPane.showMessageDialog(updatePasswordWindow, "输入的两次密码不一致！");
					}
				}else {
					JOptionPane.showMessageDialog(updatePasswordWindow, "旧密码不正确，请重新输入！");
				}
			}
		});

	}
	
	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminUpdatePasswordWindow getInstance() {
		return updatePasswordWindow;
	}
	
	/**
	 * 该方法用来设置操作该窗口的管理员
	 * @param admin 操作该窗口的管理员参数
	 */
	public void setAdmin(Admin admin) {
		this.admin=admin;
	}
}
