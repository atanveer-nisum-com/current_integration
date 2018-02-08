package com.nisum.user.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.user.model.Email;
import com.nisum.user.model.User;
import com.nisum.user.repository.EmailRepositoryUser;
import com.nisum.user.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * The Class EmailServiceImpl.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	/** The Constant logger. */
	static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
			
	/** The email sender. */
	@Autowired
    public JavaMailSender emailSender;
	
	/** The email repository. */
	@Autowired
	private EmailRepositoryUser emailRepository;
 
    /* (non-Javadoc)
     * @see com.nisum.service.EmailService#sendSimpleMessage(java.lang.String, java.lang.String, java.lang.String)
     */
    public void sendSimpleMessage(String to, String subject, String text) {
		new Thread(() -> {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
		}).start();

    }

	/* (non-Javadoc)
	 * @see com.nisum.service.EmailService#sendSignupEmail(com.nisum.model.User)
	 */
	@Override
	public void sendSignupEmail(User user) {
		
		Email email = emailRepository.getSignupEmail();
		String emailBody = email.getEmailBody();
		
		emailBody = emailBody.replaceAll("##FIRSTNAME##", user.getFirstName()).
				replaceAll("##LASTNAME##", user.getLastName());
		
		this.sendSimpleMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
		
	}

	
    
    /* (non-Javadoc)
     * @see com.nisum.service.EmailService#sendMimeMessage(java.lang.String, java.lang.String, java.lang.String)
     */
    public void sendMimeMessage(String to, String subject, String text){
		new Thread(() -> {
	    	try{
	    		MimeMessage message = emailSender.createMimeMessage();
	        	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        	helper.setTo(to);
	        	helper.setText(text, true);
	        	helper.setSubject(subject);
	        	emailSender.send(message);
	    	}catch(MessagingException me){
	    		logger.error("Error :" + me);
	    	}
		}).start();
    }

    /* (non-Javadoc)
     * @see com.nisum.service.EmailService#sendForgetPasswordMail(com.nisum.model.User, java.lang.String)
     */
    public void sendForgetPasswordMail(User user, String resetToken){
    	Email email = emailRepository.getForgetPasswordEmail();
    	String emailBody = email.getEmailBody();
    	emailBody = emailBody.replaceAll("##firstName##", user.getFirstName())
    			.replaceAll("##lastName##", user.getLastName())
    			.replaceAll("##token##", resetToken)
    			.replaceAll("##contextName##", AppConstant.CONTEXT_PATH_DEV);
    	this.sendMimeMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
    }

    /* (non-Javadoc)
     * @see com.nisum.service.EmailService#sendResetPasswordMail(com.nisum.model.User)
     */
    public void sendResetPasswordMail(User user){
    	Email email = emailRepository.getResetPasswordEmail();
    	String emailBody = email.getEmailBody();
    	emailBody = emailBody.replaceAll("##firstName##", user.getFirstName())
    			.replaceAll("##lastName##", user.getLastName());
    	this.sendSimpleMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
    }

	/* (non-Javadoc)
	 * @see com.nisum.service.EmailService#sendProfileUpdateMail(com.nisum.model.User)
	 */
	@Override
	public void sendProfileUpdateMail(User user) {
		Email email = emailRepository.getProfileUpdateEmail();
    	String emailBody = email.getEmailBody();
    	emailBody = emailBody.replaceAll("##firstName##", user.getFirstName())
    			.replaceAll("##lastName##", user.getLastName());
    	this.sendSimpleMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
		
	}
	
   
    

}
