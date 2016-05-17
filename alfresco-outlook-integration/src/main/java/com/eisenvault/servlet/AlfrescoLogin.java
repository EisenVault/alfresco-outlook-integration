package com.eisenvault.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eisenvault.model.User;
import com.eisenvault.service.AlfrescoLoginHelper;

@WebServlet(description = "Alfresco Outlook Integration Servlet", urlPatterns = { "/AlfrescoLogin",
		"/AlfrescoLogin.do" })
// @WebServlet("/AlfrescoLogin")
public class AlfrescoLogin extends HttpServlet {

	private User user;
	private AlfrescoLoginHelper alfrescoLoginHelper;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// reading the user input
		String userName = request.getParameter("userName");
		char [] password = request.getParameter("userPass").toCharArray();

		user = new User(userName, password);

		alfrescoLoginHelper = new AlfrescoLoginHelper();
		alfrescoLoginHelper.getStuff(user);

	}
}
