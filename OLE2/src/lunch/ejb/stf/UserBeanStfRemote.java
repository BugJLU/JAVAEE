package lunch.ejb.stf;

import javax.ejb.Remote;

@Remote
public interface UserBeanStfRemote {
	void sayhello();
	String getUname();
	void setUname(String uname);
	boolean isLog();
	void setLog(boolean islog);
}
