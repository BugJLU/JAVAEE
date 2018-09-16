package javaee.ole.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private static final String HOST = "172.96.211.103";
    private static final String PORT = "25";
    private static final String ACCOUNT = "sec@clouda.com";
    private static final String PASSWORD = "asdfgh456";
    private static final String EMAILFROM = "OLE";
    //private static JavaMailSenderImpl mailSender = createMailSender();

    private static MimeMessage createEmail(Session session,String towho,String subject,String data){
    	MimeMessage msg = new MimeMessage(session);
    	try{
    		InternetAddress from = new InternetAddress(ACCOUNT,EMAILFROM,"utf-8");
        	InternetAddress to = new InternetAddress(towho,"","utf-8");
    		msg.setFrom(from);
        	msg.setRecipient(RecipientType.TO, to);
        	msg.setSubject(subject,"utf-8");
        	msg.setText(data);
        	msg.setSentDate(new Date());
        	msg.saveChanges();
        	return msg;
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    	
    	
    }
    
    public static void sendMail(String towho,String subject,String data){
    	 Properties props = new Properties();
    	 props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
         props.setProperty("mail.smtp.host", HOST); // 发件人的邮箱的 SMTP 服务器地址
         props.setProperty("mail.smtp.port", PORT); 
         //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
         //props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
         
         Session session = Session.getDefaultInstance(props);
         session.setDebug(true);
         MimeMessage message = createEmail(session,towho,subject,data);
         try{
        	 Transport transport = session.getTransport();
             transport.connect(HOST,ACCOUNT,PASSWORD);
             transport.sendMessage(message, message.getAllRecipients());
             transport.close();
             System.out.println("sent. OK!");
         }catch(Exception e){
        	 e.printStackTrace();
         }
         
    }
	public static void main(String[] args) {
		sendMail("2071263070@qq.com","找回密码","你的新密码是:"+RandPassword.genPass(10));
	}

}
