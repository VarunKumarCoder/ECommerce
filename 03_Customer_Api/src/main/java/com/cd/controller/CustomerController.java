package com.cd.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.binding.Login;
import com.cd.binding.Register;
import com.cd.binding.ResetPassword;
import com.cd.binding.RestResponse;
import com.cd.service.ForgotPasswordService;
import com.cd.service.LoginService;
import com.cd.service.RegiterService;
import com.cd.service.ResetPwdService;


	@CrossOrigin("http://localhost:4200")
	@RestController
	@RequestMapping("/api/customer")
	public class CustomerController {
	    private LoginService loginService;
	    private ForgotPasswordService forgotPwdService;

	    private RegiterService registerService;

	    private ResetPwdService resetPwdService;
	    public CustomerController(LoginService loginService,ForgotPasswordService forgotPwdService,RegiterService registerService,ResetPwdService resetPwdService){
	        this.loginService=loginService;
	        this.forgotPwdService=forgotPwdService;
	        this.registerService=registerService;
	        this.resetPwdService=resetPwdService;
	    }
	    @PostMapping("/loginHandle")
	    @ResponseBody
	    public Login loginHandle(@RequestBody Login login){
	        System.out.println("Calling login controller");
	        System.out.println(login.getEmail()+" "+login.getPassword());
	        return loginService.loginHandle(login);
	    }

	    @PostMapping("/forgotPwdHandle/{email}")
	    public Boolean forgotPasswordHandle(@PathVariable String email) {
	        System.out.println("Calling forgot password controller with email: " + email);
	        return forgotPwdService.sendEmail(email);
	    }

	    @PostMapping("/register")
	    @ResponseBody
	    public Boolean register(@RequestBody Register register){
	        System.out.println(register);
	        System.out.println("Calling register customer controller");
	        return registerService.register(register);
	    }

	    @PostMapping("/reset-pwd")
	    public RestResponse resetPassword(@RequestBody ResetPassword resetPassword){
	        RestResponse response=new RestResponse();
	        System.out.println(resetPassword);
	        System.out.println("Calling reset password customer controller");
	        response.setResponse(resetPwdService.resetPassword(resetPassword));
	        return response;
	    }
	}


