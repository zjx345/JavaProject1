package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import factory.ServiceFactory;
import vo.Courseware;
/**
 * 该类为管理员增加课件窗口
 * @author zjx
 *
 */
public class AdminAddCoursewareWindow extends JInternalFrame {

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private static AdminAddCoursewareWindow addCoursewareWindow=new AdminAddCoursewareWindow();

	/**
	 * 该私有方法为无参构造方法，用来初始化窗口
	 */
	private AdminAddCoursewareWindow() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("添加课件信息");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("课件号：");
		lblNewLabel.setBounds(92, 33, 60, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程号：");
		lblNewLabel_1.setBounds(92, 60, 60, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("课件名：");
		lblNewLabel_2.setBounds(92, 87, 60, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("课件路径：");
		lblNewLabel_3.setBounds(92, 114, 60, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("课件大小：");
		lblNewLabel_4.setBounds(92, 141, 60, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField1 = new JTextField();
		textField1.setBounds(160, 30, 205, 21);
		getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(160, 57, 205, 21);
		getContentPane().add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(160, 84, 205, 21);
		getContentPane().add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(160, 111, 205, 21);
		getContentPane().add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(160, 138, 205, 21);
		getContentPane().add(textField5);
		
		// 取消按钮
		JButton btnNewButton = new JButton("\u53D6    \u6D88");
		btnNewButton.setBounds(78, 217, 93, 23);
		getContentPane().add(btnNewButton);
		// 添加取消按钮事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCoursewareWindow.dispose();
			}
		});
		
		// 添加按钮
		JButton btnNewButton_1 = new JButton("\u6DFB    \u52A0");
		btnNewButton_1.setBounds(288, 217, 93, 23);
		getContentPane().add(btnNewButton_1);
				
		// 为按钮添加事件
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cnum=textField1.getText();
				String cid=textField2.getText();
				String kname=textField3.getText();
				String cpath=textField4.getText();
				String size=textField5.getText(); 
				Courseware courseware=new Courseware(cid,cnum,kname,cpath,size);
				if(cid.equals("")||cnum.equals("")||kname.equals("")||cpath.equals("")||size.equals("")) {
					JOptionPane.showMessageDialog(addCoursewareWindow, "有文本框内容为空，请补充！");
				}else if(ServiceFactory.getIAdminServiceInstance().isExist(courseware)) {
					JOptionPane.showMessageDialog(addCoursewareWindow, "该课程号已存在，请更换课程号！");
				}else {
					ServiceFactory.getIAdminServiceInstance().addCourseware(courseware);
					JOptionPane.showMessageDialog(addCoursewareWindow, "添加成功！");
				}
			}
		});
	}
	
	/**
	 * 该静态方法为得到该窗口
	 * @return 返回一个窗口对象
	 */
	public static AdminAddCoursewareWindow getInstance() {
		return addCoursewareWindow;
	}
}
