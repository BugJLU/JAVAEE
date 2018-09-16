package javaee.ole.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javaee.ole.dao.StudentDAO;

@FacesValidator("UserValidator")
public class UserValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String uname = (String)arg2;
		if(StudentDAO.findUserByName(uname)!=null)
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"用户已存在","")); 

	}

}
