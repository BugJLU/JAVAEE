package javaee.ole.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PwdValidator")
public class PwdValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String reg ="^(?![a-zA-Z]+$)(?![0-9]+$)[a-zA-Z0-9]{8,16}$";
		if(!((String)arg2).matches(reg)){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"密码格式不正确","")); 
		}
	}

}
