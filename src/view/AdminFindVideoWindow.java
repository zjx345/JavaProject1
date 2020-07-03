package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import factory.ServiceFactory;
import tool.Table;
import vo.Courseware;
import vo.Video;
/**
 * 该类为管理员查找视频窗口
 * @author zjx
 *
 */
public class AdminFindVideoWindow extends JInternalFrame {
	private static AdminFindVideoWindow findVideoWindow=new AdminFindVideoWindow();
	private JTextField textField;
	ArrayList<Video> al; // 用来存放要显示的视频
	private int type=1; // 用来标识按什么字段名查找

	/**
	 * 该方法为无参构造方法，用来初始化窗口
	 */
	public AdminFindVideoWindow() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("查看视频信息");
		setBounds(10, 30, 900, 600);
		getContentPane().setLayout(null);
		// 创建表格，显示所有视频
		al=ServiceFactory.getIAdminServiceInstance().searchAllVideo();
		String[] colName= {"        视频号","        课程号","        视频名","      视频路径"};
		String[][] data=new String[100000][5];
		int i=0;
		for(Video video : al) {
			data[i][0]=video.getVnum();
			data[i][1]=video.getCid();
			data[i][2]=video.getVname();
			data[i][3]=video.getVpath();
			i++;
		}
		Table.createTable(getContentPane(),colName, data);
		
		// 创建下拉列表、搜索框、搜索按钮
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 30, 106, 23);
		getContentPane().add(comboBox);
		comboBox.addItem("按课程号查询：");
		comboBox.addItem("按视频号查询：");
		comboBox.addItem("按视频名查询：");
		textField = new JTextField();
		textField.setBounds(141, 30, 212, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		// 为下拉列表增加事件
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals("按课程号查询：")) {
					type=1;
				}else if(e.getItem().equals("按视频号查询：")){
					type=2;
				}else if(e.getItem().equals("按视频名查询：")) {
					type=3;
				}
			}
		});
		JButton btnNewButton = new JButton("\u67E5    \u8BE2 ");
		btnNewButton.setBounds(368, 30, 93, 23);
		getContentPane().add(btnNewButton);
		// 为搜索按钮添加事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				al=ServiceFactory.getIAdminServiceInstance().searchVideo(textField.getText(), type);
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示部分视频
				for(Video video : al) {
					Table.model.addRow(new String[] {video.getVnum(),video.getCid(),video.getVname(),video.getVpath()});
				}
			}
		});
		
		// 创建显示所有视频按钮
		JButton showAllCouButton = new JButton("显示所有视频");
		showAllCouButton.setBounds(500, 30, 120, 23);
		getContentPane().add(showAllCouButton);
		showAllCouButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				al=ServiceFactory.getIAdminServiceInstance().searchAllVideo();
				// 清空表格
				Table.model.getDataVector().clear();
				// 刷新表格
				Table.model.fireTableDataChanged();
				// 显示所有学生
				for(Video video : al) {
					Table.model.addRow(new String[] {video.getVnum(),video.getCid(),video.getVname(),video.getVpath()});
				}
			}
		});
	}

	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminFindVideoWindow getInstance() {
		return findVideoWindow;
	}
}
