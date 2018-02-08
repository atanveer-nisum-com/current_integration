package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nisum.common.shop.dto.UserDTO;
import com.nisum.shop.model.Email;
import com.nisum.shop.model.Order;
import com.nisum.shop.repository.EmailRepository;
import com.nisum.shop.service.EmailService;
import com.nisum.shop.service.UserService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	UserService userService;
	
	/** The email sender. */
	@Autowired
    public JavaMailSender emailSender;

	/** The email repository. */
	@Autowired
	private EmailRepository emailRepository;
	
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
    
	@Override
	public void sendOrderConfirmationEmail(Order order) {

		UserDTO user = userService.findOne(order.getUserId());
		Email email = emailRepository.getOrderConfirmationEmail();
		String emailBody = email.getEmailBody();
		//emailBody = emailBody.replaceAll("##FIRSTNAME##", user.getFirstName()).
				//replaceAll("##LASTNAME##",user.getLastName());
		emailBody = emailBody.replaceAll("##TOTALITEMS##",order.getTotalItem().toString()).
				replaceAll("##TOTALPRICE##", order.getOrderTotalPrice().toString());
		this.sendSimpleMessage(user.getEmailAddress(), email.getEmailSubject(), emailBody);
		
	}

}
