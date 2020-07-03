package test;

import view.LoginWindow;
/**
 * 该类为项目测试类
 * @author Lenovo
 *
 */
public class Demo {
	/**
	 * main方法，程序的入口
	 * @param args
	 */
	public static void main(String[] args) {
		LoginWindow frame = LoginWindow.getLoginWindow();
		frame.setVisible(true);
	}
}
