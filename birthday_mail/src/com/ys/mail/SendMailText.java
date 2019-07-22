package com.ys.mail;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailText {
	//发件人地址
	public static String senderAddress = "13550530310@163.com";
	//收件人地址
	//public static String recipientAddress = "13550530310@163.com";
	//发件人账户名
	public static String senderAccount = "13550530310@163.com";
	//发件人账户密码
	public static String senderPassword = "";
	
	public static void main(String[] args) throws Exception {
		//1、连接邮件服务器的参数配置
		Properties props = new Properties();
		//设置用户的认证方式
		props.setProperty("mail.smtp.auth", "true");
		//设置传输协议
		props.setProperty("mail.transport.protocol", "smtp");
		//设置发件人的SMTP服务器地址
		props.setProperty("mail.smtp.host", "smtp.163.com");
		//2、创建定义整个应用程序所需的环境信息的 Session 对象
		Session session = Session.getInstance(props);
		//设置调试信息在控制台打印出来
		session.setDebug(true);

		ArrayList<String> array=ReadFile.readTxtFile("C:\\Users\\lyb\\IdeaProjects\\birthday_mail\\person.txt");
		//System.out.println(array);

		Iterator<String> it=array.iterator();
		while(it.hasNext()){
			String str=it.next();

			Calendar now =Calendar.getInstance();
			int mon=now.get(Calendar.MONTH)+1;
			int day=now.get(Calendar.DAY_OF_MONTH);

			int birthmonth=GetPersonInfo.getMonth(str);
			int birthmtday=GetPersonInfo.getDay(str);
			String name=GetPersonInfo.getName(str);
			String mailAdres=GetPersonInfo.getMailAdres(str);
			if(mon==birthmonth && day==birthmtday){
				//3、创建邮件的实例对象
				Message msg = GetMimeMessage.getMimeMessage(session,name,mailAdres,senderAddress);
				//4、根据session对象获取邮件传输对象Transport
				Transport transport = session.getTransport();
				//设置发件人的账户名和密码
				transport.connect(senderAccount, senderPassword);
				//发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
				transport.sendMessage(msg,msg.getAllRecipients());

				//如果只想发送给指定的人，可以如下写法
				//transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
				//5、关闭邮件连接
				transport.close();

				}
			//}
		}

	}

}
