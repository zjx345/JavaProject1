package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import factory.ServiceFactory;
import vo.Course;
import vo.Student;
/**
 * 该类为主界面窗口
 * @author zjx
 *
 */
public class MainWindow extends JFrame {
	private static MainWindow mainWindow=new MainWindow();
	private Student stu;  // 操作该窗口的学生
	private JDesktopPane desktopPane;
	private StudyWindow studyWindow;
	public JTextField textField;
	
	private MainWindow() {
		init();
	}
	public void init() {
		setTitle("\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\\u5B66\u4E60\\java\\Java\u8BFE\u7A0B\u8BBE\u8BA1-\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF\\\u7D20\u6750\\\u56FE\u6807.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(this.MAXIMIZED_BOTH);  // 窗口最大化
		
		desktopPane = new JDesktopPane() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon image=new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\主界面壁纸.jpg");
				g.drawImage(image.getImage(), 0, 0,image.getIconWidth(), image.getIconHeight(),this); 
			}
		};
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 23);
		desktopPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u4E2A\u4EBA\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		// 修改密码
		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentUpdatePasswordWindow updatePasswordWindow= StudentUpdatePasswordWindow.getInstance();
				showIFrame(updatePasswordWindow);
				updatePasswordWindow.setStudent(stu);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(174, 43, 366, 24);
		textField.requestFocus();
		desktopPane.add(textField);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u5B66\u4E60\u7684\u8BFE\u7A0B\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 48, 173, 23);
		desktopPane.add(lblNewLabel);
		
		// 搜索课程按钮
		JButton searchButton = new JButton("\u641C   \u7D22");
		searchButton.setBounds(550, 43, 93, 23);
		desktopPane.add(searchButton);
		
		//增加按钮事件
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course course=ServiceFactory.getIStudentServiceInstance().findCourse(textField.getText());
				if(course==null) {
					JOptionPane.showMessageDialog(mainWindow, "该课程暂时未开放");
				}else {
					studyWindow=null;
					studyWindow=StudyWindow.getStudyWindow();
					studyWindow.setCourse(course);
					studyWindow.setVisible(true);
				}
			}
		});
		JButton button1 = new JButton("语文",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\button1.png"));
		button1.setBounds(30, 95, 268, 168);
		desktopPane.add(button1);
		this.addEvent(button1);
		
		JButton button2 = new JButton("高等数学",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\高数.png"));
		button2.setBounds(340, 95, 268, 168);
		desktopPane.add(button2);
		this.addEvent(button2);
		
		JButton button3 = new JButton("大学物理",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\物理.png"));
		button3.setBounds(650, 95, 268, 168);
		desktopPane.add(button3);
		this.addEvent(button3);
		
		JButton button4 = new JButton("初中历史",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\历史.png"));
		button4.setBounds(960, 95, 268, 168);
		desktopPane.add(button4);
		this.addEvent(button4);
		
		JButton button5 = new JButton("初中政治",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\政治.png"));
		button5.setBounds(30, 280, 268, 168);
		desktopPane.add(button5);
		this.addEvent(button5);
		
		JButton button6  = new JButton("初中数学",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\数学.png"));
		button6.setBounds(340, 280, 268, 168);
		desktopPane.add(button6);
		this.addEvent(button6);
		
		JButton button7  = new JButton("初中化学",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\化学.png"));
		button7.setBounds(650, 280, 268, 168);
		desktopPane.add(button7);
		this.addEvent(button7);
		
		JButton button8  = new JButton("英语阅读理解",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\英语.png"));
		button8.setBounds(960, 280, 268, 168);
		desktopPane.add(button8);
		this.addEvent(button8);
		
		JButton button9  = new JButton("初中物理",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\物理2.png"));
		button9.setBounds(30, 465, 268, 168);
		desktopPane.add(button9);
		this.addEvent(button9);
		
		JButton button10  = new JButton("中考英语复习",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\英语2.png"));
		button10.setBounds(340, 465, 268, 168);
		desktopPane.add(button10);
		this.addEvent(button10);
		
		JButton button11  = new JButton("初中物理知识点",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\物理3.png"));
		button11.setBounds(650, 465, 268, 168);
		desktopPane.add(button11);
		this.addEvent(button11);
		
		JButton button12  = new JButton("初中地理",new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材\\地理.png"));
		button12.setBounds(960, 465, 268, 168);
		desktopPane.add(button12);
		this.addEvent(button12);
	}
	public static MainWindow getMainWindow() {
		return mainWindow;
	}
	
	/**
	 * 该方法用来给按钮增加事件
	 * @param button 要增加事件的按钮
	 */
	public void addEvent(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Course course=ServiceFactory.getIStudentServiceInstance().findCourse(button.getText());
				mainWindow.textField.setText(button.getText());
				if(course==null) {
					JOptionPane.showMessageDialog(mainWindow, "该课程暂时未开放");
				}else {
					studyWindow=null;
					studyWindow=StudyWindow.getStudyWindow();
					studyWindow.setCourse(course);
					studyWindow.setVisible(true);
				}
			}
		});
	}
	
	/**
	 * 该方法用来设置操作该窗口的学生
	 * @param stu 要操作该窗口的学生参数
	 */
	public void setStudent(Student stu) {
		this.stu=stu;
	}
	/**
	 * 该方法用来显示子窗口
	 * @param iFrame 要显示的子窗口参数
	 */
	public void showIFrame(JInternalFrame iFrame) {
		int count=desktopPane.getAllFrames().length;  // 子窗体个数
		if(count>=1) {
			return;
		}
		else {
			desktopPane.add(iFrame);
			iFrame.setVisible(true);
			try {
				iFrame.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
