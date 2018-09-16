package javaee.ole.dao;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import lunch.ejb.stf.UserBeanStfRemote;

public class EJBRemoteClient {

	public static Object lookupRemoteEJB(final String appName,final String moduleName,final String distinctName,
			final String beanName,final String viewClassName,String stateful){
		final Hashtable prop = new Hashtable();
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try{
        	Context context = new InitialContext(prop);
        	return context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName + stateful);
        }catch(Exception e){
        	System.out.println("error while lookup, "+ e.getMessage());
        	e.printStackTrace();
        	return null;
        }
  
	}
	
//	public static void invokeStatelessBean(){
//		IUser iu = (IUser)lookupRemoteEJB("","OleEJB","","UserBean","javaee.ejb.stateless.IUser","");
//		if(iu==null)
//			return;
//		Student s = iu.findUserByName("nolunch");
//		if(s!=null)
//			System.out.println(s.getPasswd());
//		else
//			System.out.println("null");
//	}
	public static Object lookUpRemoteStatefulEJB(String beanName,String viewClassName){
		final Hashtable prop = new Hashtable();
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try{
        	Context context = new InitialContext(prop);
        	return context.lookup("ejb:/LunchEJB/" + beanName + "!" + viewClassName + "?stateful");
        }catch(Exception e){
        	System.out.println("error while lookup, "+ e.getMessage());
        	e.printStackTrace();
        	return null;
        }
	}
	public static Object lookUpRemoteEJB(String beanName,String viewClassName){
		final Hashtable prop = new Hashtable();
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try{
        	Context context = new InitialContext(prop);
        	return context.lookup("ejb:/LunchEJB/" + beanName + "!" + viewClassName);
        }catch(Exception e){
        	System.out.println("error while lookup, "+ e.getMessage());
        	e.printStackTrace();
        	return null;
        }
	}
	public static void invokeStatefulBean(){
		UserBeanStfRemote us = (UserBeanStfRemote)lookUpRemoteStatefulEJB("UserBeanStf","lunch.ejb.stf.UserBeanStfRemote");
		us.sayhello();
//		us.setUname("test");
//		System.out.println(us.getUname());
	}
	public static void main(String[] args) {
		invokeStatefulBean();
	}

}
