package com.tenghu.user_manager.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义日期格式转换标签
 * @author Arvin_Li
 *
 */
public class DateFormatTag extends TagSupport{
	
	private long value;
	private String pattern;
	private PageContext context;
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void setPageContext(PageContext pageContext) {
		context=pageContext;
		super.setPageContext(pageContext);
	}

	@Override
	public int doStartTag() throws JspException {
		SimpleDateFormat sdf=null;
		if(null==pattern||pattern.toString().equals("")){
			sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sdf=new SimpleDateFormat(pattern);
		}
		
		try {
			context.getOut().print(sdf.format(new Date(value)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
