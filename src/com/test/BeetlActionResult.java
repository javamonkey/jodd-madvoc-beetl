package com.test;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.madvoc.ActionRequest;
import jodd.madvoc.result.ActionResult;

import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;
import org.bee.tl.ext.WebConfig;
import org.bee.tl.ext.WebPathKit;
import org.bee.tl.ext.spring.SessionWrapper;
import org.bee.tl.ext.spring.WebVariable;

public class BeetlActionResult  extends ActionResult{
	
	public static final String NAME = "beetl";	
	GroupTemplate group = null;
	public BeetlActionResult() {
		super(NAME);

		try{
			
			WebConfig config = new WebConfig();
			config.updateRootPath(WebPathKit.getWebRootPath()); //更新模版路径
			group = config.createGroupTemplate();
			group.setCharset("UTF-8");
		
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}	


	@Override
	public void render(ActionRequest actionRequest, Object resultObject, String resultValue, String resultPath) throws Exception {
			
		HttpServletRequest request = actionRequest.getHttpServletRequest();
		HttpServletResponse response = actionRequest.getHttpServletResponse();
		response.setContentType("text/html;charset=UTF-8");
		String target = resultPath;
		
		
		Template t = group.getFileTemplate(resultPath);
		Enumeration en = request.getAttributeNames();
		while (en.hasMoreElements())
		{
			String key = (String) en.nextElement();
			t.set(key, request.getAttribute(key));
		}

		WebVariable webVariable = new WebVariable();
		webVariable.setRequest(request);
		webVariable.setResponse(response);
		webVariable.setSession(request.getSession());

		t.set("servlet", webVariable);
		t.set("request", request);
		t.set("ctxPath", request.getContextPath());
		t.setRawValue("session", new SessionWrapper(request.getSession()));
		t.getText(response.getWriter());

		
	}

}
