package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import factory.ServiceFactory;
import vo.Video;
/**
 * 该类为管理员增加视频窗口
 * @author zjx
 *
 */
public class AdminAddVideoWindow extends JInternalFrame {

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private static AdminAddVideoWindow addVideoWindow=new AdminAddVideoWindow();
	/**
	 * 该私有方法为无参构造方法，用来初始化该窗口
	 */
	private AdminAddVideoWindow() {
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("添加视频信息");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("视频号：");
		lblNewLabel.setBounds(92, 33, 60, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程号：");
		lblNewLabel_1.setBounds(92, 60, 60, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("视频名：");
		lblNewLabel_2.setBounds(92, 87, 60, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("视频路径：");
		lblNewLabel_3.setBounds(92, 114, 60, 15);
		getContentPane().add(lblNewLabel_3);
		
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
		
		// 取消按钮
		JButton btnNewButton = new JButton("\u53D6    \u6D88");
		btnNewButton.setBounds(78, 217, 93, 23);
		getContentPane().add(btnNewButton);
		// 添加取消按钮事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addVideoWindow.dispose();
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
				String vnum=textField1.getText();
				String cid=textField2.getText();
				String vname=textField3.getText();
				String vpath=textField4.getText();
				Video video=new Video(cid,vnum,vname,vpath);
				if(cid.equals("")||vnum.equals("")||vname.equals("")||vpath.equals("")) {
					JOptionPane.showMessageDialog(addVideoWindow, "有文本框内容为空，请补充！");
				}else if(ServiceFactory.getIAdminServiceInstance().isExist(video)) {
					JOptionPane.showMessageDialog(addVideoWindow, "该课程号已存在，请更换课程号！");
				}else {
					ServiceFactory.getIAdminServiceInstance().addVideo(video);
					JOptionPane.showMessageDialog(addVideoWindow, "添加成功！");
				}
			}
		});
	}
	
	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminAddVideoWindow getInstance() {
		return addVideoWindow;
	}
}
