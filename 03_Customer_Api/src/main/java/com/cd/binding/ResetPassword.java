package com.cd.binding;

import lombok.Data;

@Data
public class ResetPassword {

	private String email;
	private String newPwd;
	private String confirmNewPwd;
}
