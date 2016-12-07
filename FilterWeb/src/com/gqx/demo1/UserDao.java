package com.gqx.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//�û���Ȩ�޹�����ȡ���޸Ĳ���
public class UserDao {
	private static Map<String, User> users;    //�û����е�Ȩ��
	private static List<Authority> authorities =null;	//Ȩ�޵�����
	
	static{
		//��authorities��һ���ж�����Ȩ��
		authorities=new ArrayList<Authority>();
		authorities.add(new Authority("Article-1", "/article-1.jsp"));
		authorities.add(new Authority("Article-2", "/article-2.jsp"));
		authorities.add(new Authority("Article-3", "/article-3.jsp"));
		authorities.add(new Authority("Article-4", "/article-4.jsp"));
		
		users=new HashMap<String, User>();
		User user1=new User("AAA",authorities.subList(0, 2)); //sublist:����ҹ�
		users.put("AAA", user1);
		User user2=new User("BBB",authorities.subList(2,4)); //sublist:����ҹ�
		users.put("BBB", user2);
	}
	
	//��ȡ�û���ȫ����Ϣ
	User get(String username){
		return users.get(username); 
	}
	//�޸��û�����Ϣ
	void update(String name,List<Authority> authorities){
		users.get(name).setAuthorities(authorities);
	}
	
	//��ȡ���е�Authorities(һ���ж�����authority)
	public static List<Authority> getAuthorities() {
		return authorities;
	}
	
	public List<Authority> getAuthorities(String[] urls) {
		List<Authority> authorities2 = new ArrayList<>();
		
		for(Authority authority: authorities){
			if(urls != null){
				for(String url: urls){
					if(url.equals(authority.getUrl())){
						authorities2.add(authority);
					}
				}
			}			
		}
		
		return authorities2;
	}
	
	
}
