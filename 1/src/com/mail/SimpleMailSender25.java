package com.mail;
/**   
 * 简单邮件（不带附件的邮件）发送器   端口25--好使-已测试
 */ 
import java.util.Date;    
import java.util.Properties;   

import javax.mail.Address;    
import javax.mail.BodyPart;    
import javax.mail.Message;    
import javax.mail.Multipart;    
import javax.mail.Session;    
import javax.mail.Transport;    
import javax.mail.internet.InternetAddress;    
import javax.mail.internet.MimeBodyPart;    
import javax.mail.internet.MimeMessage;    
import javax.mail.internet.MimeMultipart;    



    
public class SimpleMailSender25 {    
/**   
  * 以文本格式发送邮件   
  * @param mailInfo 待发送的邮件的信息   
  */    
    public boolean sendTextMail(MailSenderInfo25 mailInfo) throws Exception{    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;    
      Properties pro = mailInfo.getProperties();   
      if (mailInfo.isValidate()) {    
      // 如果需要身份认证，则创建一个密码验证器    
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
      }   
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator); 
    
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      // 创建邮件发送者地址    
      Address from = new InternetAddress(mailInfo.getFromAddress());    
      // 设置邮件消息的发送者    
      mailMessage.setFrom(from);    
      // 创建邮件的接收者地址，并设置到邮件消息中    
      Address to = new InternetAddress(mailInfo.getToAddress());    
      mailMessage.setRecipient(Message.RecipientType.TO,to);    
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());    
      // 设置邮件消息的主要内容    
      String mailContent = mailInfo.getContent();    
      mailMessage.setText(mailContent);    
      // 发送邮件    
      Transport.send(mailMessage); 
    
      return true;    
    }    
       
    /**   
      * 以HTML格式发送邮件   
      * @param mailInfo 待发送的邮件信息   
      */    
    public  boolean sendHtmlMail(MailSenderInfo25 mailInfo) throws Exception{    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;   
      Properties pro = mailInfo.getProperties();   
      //如果需要身份认证，则创建一个密码验证器     
      if (mailInfo.isValidate()) {    
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
      }    
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
        
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      // 创建邮件发送者地址    
      Address from = new InternetAddress(mailInfo.getFromAddress());    
      // 设置邮件消息的发送者    
      mailMessage.setFrom(from);    
      // 创建邮件的接收者地址，并设置到邮件消息中    
      Address to = new InternetAddress(mailInfo.getToAddress());    
      // Message.RecipientType.TO属性表示接收者的类型为TO    
      mailMessage.setRecipient(Message.RecipientType.TO,to);    
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());    
      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
      Multipart mainPart = new MimeMultipart();    
      // 创建一个包含HTML内容的MimeBodyPart    
      BodyPart html = new MimeBodyPart();    
      // 设置HTML内容    
      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
      mainPart.addBodyPart(html);    
      // 将MiniMultipart对象设置为邮件内容    
      mailMessage.setContent(mainPart);    
      // 发送邮件    
      Transport.send(mailMessage);    
      return true;    
    }    
 
    /*
     * @title:标题
     * @content:内容
     * @type:类型,1:文本格式;2:html格式
     * @tomail:接收的邮箱
     */
    public boolean sendMail(String title,String content,String type,String tomail) throws Exception{   
        
    	//这个类主要是设置邮件   -发送邮件的设置-smtp.163.com
	     MailSenderInfo25 mailInfo = new MailSenderInfo25();    
	   //  mailInfo.setMailServerHost("smtp.qq.com");  
	     mailInfo.setMailServerHost("smtp.163.com");  
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	    // mailInfo.setUserName("itfather@1b23.com");  
	     mailInfo.setUserName("ahviplc@163.com");  
	    // mailInfo.setPassword("tttt");//您的邮箱密码    
	     mailInfo.setPassword("lc201088");//您的邮箱密码    
	     //mailInfo.setFromAddress("itfather@1b23.com"); 
	     mailInfo.setFromAddress("ahviplc@163.com"); 
	     mailInfo.setToAddress(tomail);    
	     mailInfo.setSubject(title);    
	     mailInfo.setContent(content);    
	     //这个类主要来发送邮件   
	  
	     SimpleMailSender25 sms = new SimpleMailSender25();   
	     
	     if("1".equals(type)){
	    	 return sms.sendTextMail(mailInfo);//发送文体格式    
	     }else if("2".equals(type)){
	    	 return sms.sendHtmlMail(mailInfo);//发送html格式   
	     }
	     return false;
	   }
    /**
     * @param SMTP  	邮件服务器
     * @param PORT		端口
     * @param EMAIL		本邮箱账号
     * @param PAW		本邮箱密码
     * @param toEMAIL	对方箱账号
     * @param TITLE		标题
     * @param CONTENT	内容
     * @param TYPE		1：文本格式;2：HTML格式
     */
    public static void sendEmail(String SMTP, String PORT, String EMAIL, String PAW, String toEMAIL, String TITLE, String CONTENT, String TYPE) throws Exception{ 
    	
        //这个类主要是设置邮件   
	     MailSenderInfo25 mailInfo = new MailSenderInfo25();
	     
	     mailInfo.setMailServerHost(SMTP);    
	     mailInfo.setMailServerPort(PORT);    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName(EMAIL);    
	     mailInfo.setPassword(PAW);   
	     mailInfo.setFromAddress(EMAIL);    
	     mailInfo.setToAddress(toEMAIL);    
	     mailInfo.setSubject(TITLE);    
	     mailInfo.setContent(CONTENT);    
	     //这个类主要来发送邮件   
	  
	     SimpleMailSender25 sms = new SimpleMailSender25();
	     
	    if("1".equals(TYPE)){
	    	sms.sendTextMail(mailInfo);
	    }else{
	    	sms.sendHtmlMail(mailInfo);
	    }
	     
	   }
    
    
    public static void main(String[] args) throws Exception{   
        //这个类主要是设置邮件   
	     MailSenderInfo25 mailInfo25 = new MailSenderInfo25();    
	     mailInfo25.setMailServerHost("smtp.163.com");    
	     mailInfo25.setMailServerPort("25");    
	     mailInfo25.setValidate(true);    
	     mailInfo25.setUserName("ahviplc@163.com");    
	     mailInfo25.setPassword("lc201088");//您的邮箱密码    
	     mailInfo25.setFromAddress("ahviplc@163.com");    
	     mailInfo25.setToAddress("ahlc@sina.cn");    
	     mailInfo25.setSubject("设置邮箱标题-2017年9月11日");    
	     mailInfo25.setContent("设置邮箱内容-2017年9月11日！~LC");    
	     //这个类主要来发送邮件   
	  
	     SimpleMailSender25 sms25 = new SimpleMailSender25();   
	     sms25.sendTextMail(mailInfo25);//发送文体格式    
	     //sms25.sendHtmlMail(mailInfo25);//发送html格式   
	     System.out.println("成功！25");
	     
	   }
    
}   
