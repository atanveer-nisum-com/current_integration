package com.nisum.shop.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.shop.model.Email;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.User;
import com.nisum.shop.repository.EmailRepository;
import com.nisum.shop.service.EmailService;
import com.nisum.shop.service.UserService;
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
	private EmailRepository emailRepository;
	
	@Autowired
	private UserService userService;
 
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
	 * @see com.nisum.service.EmailService#sendOrderConfirmationEmail(com.nisum.model.Order)
	 */
	@Override
	public void sendOrderConfirmationEmail(Order order) {
		
		User user = userService.findOne(order.getUserId());
		
		Email email = emailRepository.getOrderConfirmationEmail();
		String emailBody = email.getEmailBody();
		
		emailBody = emailBody.replaceAll("##FIRSTNAME##", user.getFirstName()).
				replaceAll("##LASTNAME##",user.getLastName());
		
		emailBody = emailBody.replaceAll("##TOTALITEMS##",order.getTotalItem().toString()).
				replaceAll("##TOTALPRICE##", order.getOrderTotalPrice().toString());
		
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
    			.replaceAll("##contextName##", AppConstant.CONTEXT_PATH_DEV + AppConstant.API_USER);
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
	
    /* (non-Javadoc)
     * @see com.nisum.service.EmailService#sendCancelOrderEmail(com.nisum.model.Order, com.nisum.model.User)
     */
    public void sendCancelOrderEmail(Order order, User user){
    	Email email = emailRepository.getCancelOrderEmail();
    	String emailBody = email.getEmailBody();
    	//TODO: SHOP: order items
    	//String itemNames = this.getItemNames(order.getOrderItems());
    	emailBody = emailBody.replaceAll("##firstName##", user.getFirstName())
    							.replaceAll("##lastName##", user.getLastName())
    							.replaceAll("##orderNumber##", order.getId().toString())
    							.replaceAll("##totalItems##", order.getTotalItem().toString())
    							//.replaceAll("##itemNames##", itemNames)
    							.replaceAll("##orderTotalPrice##", order.getOrderTotalPrice().toString());
    	this.sendMimeMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
    }

    /**
     * Gets the item names.
     *
     * @param Orderitems the orderitems
     * @return the item names
     */
//    private String getItemNames(Set<OrderItem> Orderitems){
//    	StringBuffer itemNames = new StringBuffer();
//    	for (OrderItem orderItem : Orderitems) {
//    		//TODO commenting this line as there is no item model exist in app shop
////    		itemNames.append(orderItem.getItem().getName());
//    		itemNames.append(", ");
//		}
//    	itemNames = itemNames.replace(itemNames.length()-2, itemNames.length()-1, ".");
//    	return itemNames.toString();
//    }



}
