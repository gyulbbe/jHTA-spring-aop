package aop.demo1;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public void addUser(String id, String name, String pwd) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {}
		System.out.println("핵심기능: 신규 사용자를 등록한다.");
	}
	
	public void deleteUser(String id) {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {}
		System.out.println("핵심기능: 사용자를 삭제한다.");
	}
}
