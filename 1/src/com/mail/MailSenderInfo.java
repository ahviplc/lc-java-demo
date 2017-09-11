package com.mail;   
/**   
 *发送邮件需要使用的基本信息 端口465-ssl-好使-已测试
 *  
 */    
import java.util.Properties;    
public class MailSenderInfo {    
    // 发送邮件的服务器的IP和端口    
    private String mailServerHost;    
    private String mailServerPort = "465";    
    // 邮件发送者的地址    
    private String fromAddress;    
    // 邮件接收者的地址    
    private String toAddress;    
    // 登陆邮件发送服务器的用户名和密码    
    private String userName;    
    private String password;    
    // 是否需要身份验证    
    private boolean validate = false;    
    // 邮件主题    
    private String subject;    
    // 邮件的文本内容    
    private String content;    
    // 邮件附件的文件名    
    private String[] attachFileNames;      
    /**   
      * 获得邮件会话属性   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", this.mailServerHost);    
      p.put("mail.smtp.port", this.mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false"); 
    
      // 如果使用ssl，则去掉使用25端口的配置，进行如下配置!---如果是端口25--上面-- private String mailServerPort = "25";-下面注释 就好了！！！    
     p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     p.put("mail.smtp.socketFactory.port", "465");
      // p.put("mail.smtp.port", "465");
      
      return p;    
    }    
    
    
    //摘自网上-原版-源码
    /*
     *   public Properties getProperties(){      
      Properties p = new Properties();      
      p.put("mail.smtp.ssl.enable",ssl ? "true" : "false");  
      p.put("mail.smtp.host", this.mailServerHost);      
      p.put("mail.smtp.port", this.mailServerPort);      
      p.put("mail.smtp.auth", validate ? "true" : "false");     
      if(ssl){  
            MailSSLSocketFactory sf;    
              try {  
                  sf = new MailSSLSocketFactory();  
                  sf.setTrustAllHosts(true);    
                  p.put("mail.smtp.ssl.socketFactory", sf);   
              } catch (GeneralSecurityException ex) {  
                  ex.printStackTrace();  
              }  
      }  
      return p;      
    }      
     
     * 
     */
    
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public String getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    }    
}   
