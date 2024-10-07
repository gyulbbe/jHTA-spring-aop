package aop.demo1;

public interface UserService {

	void addUser(String id, String name, String pwd);
	
	void deleteUser(String id);
}
