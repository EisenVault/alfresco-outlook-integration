package com.eisenvault.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import com.eisenvault.model.User;

public class AlfrescoConnector {
	
	private Session cmisSession;
	
	private String serviceUrl = "http://localhost:8081/alfresco/api/-default-/public/cmis/versions/1.1/atom";
	
	public Session getCmisSession(User user) {
		if (cmisSession == null) {
			// default factory implementation
			SessionFactory factory = SessionFactoryImpl.newInstance();
			Map<String, String> parameter = new HashMap<String, String>();
	
			// connection settings
			parameter.put(SessionParameter.ATOMPUB_URL, getServiceUrl());
			parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
			parameter.put(SessionParameter.AUTH_HTTP_BASIC, "true");
			parameter.put(SessionParameter.USER, user.getUserName());
			parameter.put(SessionParameter.PASSWORD, String.valueOf(user.getPassword()));
			//parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");
	
			List<Repository> repositories = factory.getRepositories(parameter);
	
			cmisSession = repositories.get(0).createSession();
		}
		return this.cmisSession;
	}
	
	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
}
