package com.onlymega.dgaisan.html5maker.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.onlymega.dgaisan.html5maker.actions.RegisterAction;
import com.onlymega.dgaisan.html5maker.common.CommonData;
import com.onlymega.dgaisan.html5maker.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * An {@link Interceptor} that is checking authentication before invoking
 * certain actions (that require user to be logged).
 * 
 * @author Dmitri Gaisan
 * 
 */
public class AuthenticationIntercepter extends AbstractInterceptor implements CommonData {
	private static final long serialVersionUID = 2121L;
	private static final Logger logger = Logger.getLogger(RegisterAction.class.getName());
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		User user = (User) actionContext.getSession().get(USER_OBJECT);
		
		if (user == null) {
			logger.log(Level.FINEST, "Interceptor: user is null!");
			
			return ACTION_RESULT_LOGIN;
		} 
			
		
		return invocation.invoke();
	}

}
