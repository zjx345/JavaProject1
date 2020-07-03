package factory;
import service.IAdminService;
import service.IStudentService;
import service.impl.AdminServiceImpl;
import service.impl.StudentServiceImpl;
/**
 * 该类为业务工厂类，用来实例化一些业务类
 * @author zjx
 *
 */
public class ServiceFactory {
	/**
	 * 负责实例化用户业务类
	 * @return 返回用户业务类对象
	 */
	public static IStudentService getIStudentServiceInstance() {
		return new StudentServiceImpl();
	}
	/**
	 * 负责实例化管理员业务类
	 * @return 返回管理员业务类对象
	 */
	public static IAdminService getIAdminServiceInstance() {
		return new AdminServiceImpl();
	}
}
