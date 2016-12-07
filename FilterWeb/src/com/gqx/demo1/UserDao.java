package com.gqx.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//用户的权限管理，获取和修改操作
public class UserDao {
	private static Map<String, User> users;    //用户所有的权限
	private static List<Authority> authorities =null;	//权限的种类
	
	static{
		//在authorities中一共有多少种权限
		authorities=new ArrayList<Authority>();
		authorities.add(new Authority("Article-1", "/article-1.jsp"));
		authorities.add(new Authority("Article-2", "/article-2.jsp"));
		authorities.add(new Authority("Article-3", "/article-3.jsp"));
		authorities.add(new Authority("Article-4", "/article-4.jsp"));
		
		users=new HashMap<String, User>();
		User user1=new User("AAA",authorities.subList(0, 2)); //sublist:左闭右关
		users.put("AAA", user1);
		User user2=new User("BBB",authorities.subList(2,4)); //sublist:左闭右关
		users.put("BBB", user2);
	}
	
	//获取用户的全部信息
	User get(String username){
		return users.get(username); 
	}
	//修改用户的信息
	void update(String name,List<Authority> authorities){
		users.get(name).setAuthorities(authorities);
	}
	
	//获取所有的Authorities(一共有多少种authority)
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
