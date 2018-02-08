package com.nisum.user.test.service;

import com.nisum.common.constant.AppConstant;
import com.nisum.user.repository.*;
import com.nisum.user.service.*;
import com.nisum.user.service.impl.*;
import com.nisum.user.util.AddressConverter;
import com.nisum.user.util.UserConverter;
import com.nisum.user.util.UserPaymentCardConverter;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
class ServiceContextConfiguration {

    @Bean
    AddressBookService addressBookService() {
        return new AddressBookServiceImpl();
    }

    @Bean
    UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    StateRepository stateRepository() {
        return Mockito.mock(StateRepository.class);
    }

    @Bean
    AddressRepository addressRepository() {
        return Mockito.mock(AddressRepository.class);
    }

    @Bean
    AddressService addressService() {
        return new AddressServiceImpl();
    }

    @Bean
    CountryRepository countryRepository() {
        return Mockito.mock(CountryRepository.class);
    }

    @Bean
    CountryService countryService() {
        return new CountryServiceImpl();
    }

    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    EmailRepositoryUser emailRepository() {
        return Mockito.mock(EmailRepositoryUser.class);
    }

    @Bean
    EmailService emailService() {
        return new EmailServiceImpl();
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    StateService stateService() {
        return new StateServiceImpl();
    }

    @Bean
    RestTemplate restTemplate() {
        return  Mockito.mock(RestTemplate.class);
    }

    @Bean
    WishlistRepository wishlistRepository() {
        return Mockito.mock(WishlistRepository.class);
    }

    @Bean
    WishlistItemRepository wishlistItemRepository() {
        return Mockito.mock(WishlistItemRepository.class);
    }

    @Bean
    WishlistService wishlistService() {
        return new WishlistServiceImpl();
    }

    @Bean
    LoginService loginService() {
        return new LoginServiceImpl();
    }

    @Bean
    ForgotPasswordService forgotPasswordService() {
        return new ForgotPasswordServiceImpl();
    }
    @Bean
    StoreRepository storeRepository() {
        return Mockito.mock(StoreRepository.class);
    }
    @Bean
	public ExecutorService executorService() {
		ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.THREADS_COUNT);
		return executorService;
	}
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	@Bean
	public StoreService storeService() {
		return new StoreServiceImpl();
	}
	
	@Bean
	public PaymentCardService paymentCardService() {
		return new PaymentCardServiceImpl();
	}
	
	@Bean
	UserPaymentCardRepository userPaymentCardRepository() {
	    return Mockito.mock(UserPaymentCardRepository.class);
	}
	
	@Bean
	AddressConverter addressConverter() {
		return new AddressConverter();
	}
	
	@Bean
	UserPaymentCardConverter userPaymentCardConverter() {
		return new UserPaymentCardConverter();
	}
	
	@Bean
	UserConverter userConverter() {
		return new UserConverter();
	}
	
	
}
