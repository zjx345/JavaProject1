package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import factory.ServiceFactory;
import tool.Table;
import vo.Video;
/**
 * 该类为管理员删除视频窗口
 * @author zjx
 *
 */
public class AdminDeleteVideoWindow extends AdminFindVideoWindow {

	private static AdminDeleteVideoWindow deleteVideoWindow=new AdminDeleteVideoWindow();
	private JButton deleteButton;  // 删除按钮
	/**
	 * 该私有方法为无参构造方法，用来初始化该窗口
	 */
	private AdminDeleteVideoWindow() {
		super();
		setTitle("删除视频");
		deleteButton=new JButton("删    除");
		deleteButton.setBounds(650, 30, 93, 23);
		getContentPane().add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] seletedRows=Table.table.getSelectedRows();
				if(seletedRows.length>0) {
					for(int i=0;i<seletedRows.length;i++) {
						// 从数据库中删除
						Video video=new Video((String)Table.table.getValueAt(seletedRows[0], 1),(String)Table.table.getValueAt(seletedRows[i]-i, 0),(String)Table.table.getValueAt(seletedRows[i]-i, 2),(String)Table.table.getValueAt(seletedRows[i]-i, 3));
						ServiceFactory.getIAdminServiceInstance().removeVideo(video);
						// 从表格中删除
						Table.model.removeRow(seletedRows[0]);
						Table.model.fireTableDataChanged();
					}
					JOptionPane.showMessageDialog(deleteVideoWindow, "删除成功！");
				}else {
					JOptionPane.showMessageDialog(deleteVideoWindow, "没有选择所要删除的行！");
				}
			}
		});
	}
	
	/**
	 * 该静态方法用来取到该窗口对象
	 * @return 返回该窗口对象
	 */
	public static AdminDeleteVideoWindow getInstance() {
		return deleteVideoWindow;
	}
}
