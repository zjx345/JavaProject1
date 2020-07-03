package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import factory.ServiceFactory;
import vo.Course;
import vo.Courseware;
import vo.Video;
/**
 * 该类为课程学习界面
 * @author zjx
 *
 */
public class StudyWindow extends JFrame {
	private JTable table;
	private Course course;  // 存放当前要学习的课程对象
	private static JDesktopPane desktopPane;  // 桌面
	private JLabel label;  // 欢迎标签
	private static JLabel msgLabel; // 课程简介、教学大纲、授课计划标题标签
	private static JLabel vcLabel;  // 视频、课件标题标签
	private static JLabel imgLabel;  // 图片标签
	private DefaultTableModel model;
	private JScrollPane coursewareJScrollPane;  // 课件滚动条
	private JScrollPane videoJScrollPane;  // 视频滚动条
	private JButton downloadButton;  //下载按钮
	private JButton playButton;  //视频播放按钮
	private String cpath;  // 课件路径
	private String vpath;  // 视频路径
	private static StudyWindow studyWindow=new StudyWindow();

	static {
		msgLabel=new JLabel();
		msgLabel.setBounds(500, 60, 300, 300);
		msgLabel.setFont(new Font("宋体", 20, 40));
		desktopPane.add(msgLabel);
		
		vcLabel=new JLabel();
		vcLabel.setBounds(400, -50, 300, 300);
		vcLabel.setFont(new Font("宋体", 20, 40));
		desktopPane.add(vcLabel);
		
		imgLabel=new JLabel();
		imgLabel.setBounds(0, 50, 300, 300);
		desktopPane.add(imgLabel);
	}
	/**
	 * 该私有方法为无参构造方法
	 */
	private StudyWindow() {
		init();
	}
	/**
	 * 该方法用来初始化该窗口
	 */
	public void init() {
		setTitle("\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\\u5B66\u4E60\\java\\Java\u8BFE\u7A0B\u8BBE\u8BA1-\u5728\u7EBF\u8BFE\u7A0B\u5B66\u4E60\u7CFB\u7EDF\\\u7D20\u6750\\\u56FE\u6807.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 50);
		desktopPane.add(toolBar);
		
		Object[][] data= {};
		String[] colName= {null};
		model=new DefaultTableModel(data,colName);
		table = new JTable();
		table.setModel(model);
		table.setBounds(310, 290, 605, 190);
		table.setFont(new Font("楷体", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.setBackground(Color.lightGray);
		desktopPane.add(table);
		table.setOpaque(false);
		table.setShowGrid(false);
		
		label = new JLabel("欢迎学习"+MainWindow.getMainWindow().textField.getText()+"科目");
		label.setBounds(150, 200, 1000, 200);
		label.setFont(new Font("宋体", 20, 70));
		desktopPane.add(label);
		
		// 课程简介按钮
		JButton btnNewButton = new JButton("\u8BFE \u7A0B \u7B80 \u4ECB ");
		toolBar.add(btnNewButton);
		toolBar.addSeparator(new Dimension(20,45)); // 分隔线
		// 增加课程简介事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String presentation=course.getPresentation();
				imgLabel.setIcon(new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材2\\6.gif"));
				imgLabel.setVisible(true);
				
				msgLabel.setText("课程简介");
				msgLabel.setVisible(true);
				vcLabel.setVisible(false);
				printCourseInfo(presentation);
			}
		});
		
		// 教学大纲按钮
		JButton btnNewButton_1 = new JButton("\u6559 \u5B66 \u5927 \u7EB2 ");
		toolBar.add(btnNewButton_1);
		toolBar.addSeparator(new Dimension(20,45)); // 分隔线
		// 增加按钮事件
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String syllabus=course.getSyllabus();
				
				imgLabel.setIcon(new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材2\\3.gif"));
				imgLabel.setVisible(true);
				
				msgLabel.setText("教学大纲");
				msgLabel.setVisible(true);
				vcLabel.setVisible(false);
				printCourseInfo(syllabus);
			}
		});
		
		// 授课计划按钮
		JButton btnNewButton_2 = new JButton("\u6388 \u8BFE \u8BA1 \u5212 ");
		toolBar.add(btnNewButton_2);
		toolBar.addSeparator(new Dimension(20,45)); // 分隔线
		// 增加按钮事件
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String plan=course.getPlan();
				
				imgLabel.setIcon(new ImageIcon("F:\\学习\\java\\Java课程设计-在线课程学习系统\\素材2\\4.gif"));
				imgLabel.setVisible(true);
				
				msgLabel.setText("授课计划");
				msgLabel.setVisible(true);
				vcLabel.setVisible(false);
				printCourseInfo(plan);
			}
		});
		
		// 视频按钮
		JButton btnNewButton_3 = new JButton("\u6559 \u5B66 \u89C6 \u9891 ");
		toolBar.add(btnNewButton_3);
		toolBar.addSeparator(new Dimension(20,45)); // 分隔线
		// 增加按钮事件
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setVisible(false);
				
				imgLabel.setVisible(false);
				msgLabel.setVisible(false);
				vcLabel.setText("教学视频");
				vcLabel.setVisible(true);
				// 隐藏课件表格
				if(coursewareJScrollPane!=null) {
					coursewareJScrollPane.setVisible(false);
					downloadButton.setVisible(false);
				}
				// 隐藏课程信息表格
				model.setRowCount(0);
				String[] colName= {"视频编号","视频名"};
				Object[][] data=new Object[100000][100];
				ArrayList<Video> al=ServiceFactory.getIStudentServiceInstance().findAllVideo(course.getCno());
				int i=0;
				for(Video video:al) {
					data[i][0]=video.getVnum();
					data[i][1]=video.getVname();
					i++;
				}
				DefaultTableModel model=new DefaultTableModel(data,colName);
				JTable table=new JTable(model);
				//table.setEnabled(false);
				// 表格数据居中显示
				DefaultTableCellRenderer r=new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				table.setDefaultRenderer(Object.class, r);
				
				// 向表格增加鼠标单击监听器
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// 选择表格行获取路径
						int row=table.getSelectedRow();
						String vnum=(String)model.getValueAt(row, 0);
						if(vnum!=null) {
							for(Video video:al) {
								if(vnum.equals(video.getVnum())) {
									vpath=video.getVpath();
									break;
								}
							}
						}
					}
				});
				// 给表格加上滚动条
				videoJScrollPane=new JScrollPane(table);
				videoJScrollPane.setBounds(100, 130, 800, 460);
				desktopPane.add(videoJScrollPane);
				videoJScrollPane.setVisible(true);
				
				// 下载按钮
				playButton = new JButton("播放视频");
				playButton.setBounds(790, 60, 111, 23);
				desktopPane.add(playButton);
				playButton.setVisible(true);
				// 增加下载按钮事件
				playButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ServiceFactory.getIStudentServiceInstance().watchVideo(vpath);
					}
				});
			}
		});
		
		// 课件按钮
		JButton btnNewButton_4 = new JButton(" \u8BFE         \u4EF6 ");
		toolBar.add(btnNewButton_4);
		
		// 增加事件
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setVisible(false);
				
				imgLabel.setVisible(false);
				msgLabel.setVisible(false);
				vcLabel.setText("课    件");
				vcLabel.setVisible(true);
				// 隐藏视频表格
				if(videoJScrollPane!=null) {
					videoJScrollPane.setVisible(false);
					playButton.setVisible(false);
				}
				// 隐藏课程信息表格
				model.setRowCount(0);
				String[] colName= {"课件编号","课件名","课件大小"};
				Object[][] data=new Object[100000][100];
				ArrayList<Courseware> al=ServiceFactory.getIStudentServiceInstance().findAllCourseware(course.getCno());
				int i=0;
				for(Courseware courseware:al) {
					data[i][0]=courseware.getCnum();
					data[i][1]=courseware.getKname();
					data[i][2]=courseware.getSize();
					i++;
				}
				DefaultTableModel model=new DefaultTableModel(data,colName);
				JTable table=new JTable(model);
				//table.setEnabled(false);
				// 表格数据居中显示
				DefaultTableCellRenderer r=new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				table.setDefaultRenderer(Object.class, r);
				
				// 向表格增加鼠标单击监听器
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// 选择表格行获取路径
						int row=table.getSelectedRow();
						String cnum=(String)model.getValueAt(row, 0);
						for(Courseware courseware:al) {
							if(cnum.equals(courseware.getCnum())) {
								cpath=courseware.getCpath();
								break;
							}
						}
					}
				});

				coursewareJScrollPane=new JScrollPane(table);
				coursewareJScrollPane.setBounds(100, 130, 800, 460);
				desktopPane.add(coursewareJScrollPane);
				coursewareJScrollPane.setVisible(true);
				
				// 下载按钮
				downloadButton = new JButton("\u5F00 \u59CB \u4E0B \u8F7D ");
				downloadButton.setBounds(790, 60, 111, 23);
				desktopPane.add(downloadButton);
				downloadButton.setVisible(true);
				// 增加下载按钮事件
				downloadButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(ServiceFactory.getIStudentServiceInstance().downloadCourseware(cpath)) {
							JOptionPane.showMessageDialog(studyWindow, "下载成功！");
						}else {
							JOptionPane.showMessageDialog(studyWindow, "下载失败！");
						}
					}
				});
			}
		});
	}
	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static StudyWindow getStudyWindow() {
		return studyWindow;
	}
	/**
	 * 该方法用来设置课程
	 * @param course 要设置的课程
	 */
	public void setCourse(Course course) {
		this.course=course;
	}
	/**
	 * 该方法用来显示课程信息
	 * @param msg 要显示的课程内容
	 */
	public void printCourseInfo(String msg) {
		model.setRowCount(0);
		label.setVisible(false);
		if(coursewareJScrollPane!=null) {
			coursewareJScrollPane.setVisible(false);
			downloadButton.setVisible(false);
		}
		if(videoJScrollPane!=null) {
			videoJScrollPane.setVisible(false);
			playButton.setVisible(false);
		}
		int row=(int)Math.ceil(msg.length()/30.0);
		for(int i=0;i<row;i++) {
			String temp;
			int start_index,end_index;
			if(i==0)
				start_index=0;
			else
				start_index=i*30+1;
			if(i==row-1)
				end_index=msg.length();
			else
				end_index=(i+1)*30;
			temp=msg.substring(start_index,end_index);
			model.addRow(new Object[] {temp});
		}
	}
}
