package com.office.ioc.core.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.office.ioc.core.BeanFactory;

@SuppressWarnings("unchecked")
public class ClassPathXmlApplicationContext implements BeanFactory {

	private Map<String, Object> beans = new HashMap<String, Object>();
	
	private String configLocation="application.xml";
	
	public ClassPathXmlApplicationContext(String configLocation)throws Exception{
		this.configLocation=configLocation;
		setConfigLocation(getDocument());
	}

	public ClassPathXmlApplicationContext() throws Exception {
		setConfigLocation(getDocument());
	}
	
	public Document getDocument() throws Exception{
		SAXReader sr = new SAXReader();
		return sr.read(sr.getClass().getClassLoader().getResourceAsStream(configLocation));// 构造文档对象
	}
	
	public void setConfigLocation(Document document) throws Exception{
		
		Element root = document.getRootElement();// 获取根元素HD
		List<Element> list = root.elements("bean");// 获取名字为bean的所有元素

		for (Element element : list) {
			String id = element.attributeValue("id");
			String clazz = element.attributeValue("class");
			Object object = Class.forName(clazz).newInstance();
			System.out.println("bean id is " + id + ",  class is " + clazz);
			beans.put(id, object);

			// 遍历所有的property
			for (Element property : (List<Element>) element.elements("property")) {
				String name = property.attributeValue("name");
				String bean = property.attributeValue("bean");
				Object beanObject = beans.get(bean);

				// 构造setter方法
				String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				System.out.println("setter method name = " + methodName);

				Method m = object.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
				m.invoke(object, beanObject);
			}
		}
	}

	public Object getBean(String id) {
		return beans.get(id);
	}

}
