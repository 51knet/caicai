package com.knet51.ccweb.controllers.teacher;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import com.knet51.ccweb.util.validators.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "new_psw", second = "confirm_new_psw", message = "The password fields must match"),
})

public class TeacherPswForm {
	@NotEmpty
	private String ori_psw;
	@NotEmpty
	@Size(min=3, max=25)
	private String new_psw;
	@NotEmpty
	@Size(min=3, max=25)
	private String confirm_new_psw;
	public String getOri_psw() {
		return ori_psw;
	}
	public void setOri_psw(String ori_psw) {
		this.ori_psw = ori_psw;
	}
	public String getNew_psw() {
		return new_psw;
	}
	public void setNew_psw(String new_psw) {
		this.new_psw = new_psw;
	}
	public String getConfirm_new_psw() {
		return confirm_new_psw;
	}
	public void setConfirm_new_psw(String confirm_new_psw) {
		this.confirm_new_psw = confirm_new_psw;
	}

}
