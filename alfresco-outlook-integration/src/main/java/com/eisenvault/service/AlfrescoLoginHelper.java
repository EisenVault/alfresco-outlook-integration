package com.eisenvault.service;

import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.data.RepositoryCapabilities;
import org.apache.chemistry.opencmis.commons.data.RepositoryInfo;

import com.eisenvault.model.User;
import com.eisenvault.util.AlfrescoConnector;

public class AlfrescoLoginHelper extends AlfrescoConnector {

	public void getStuff(User user) {
		Session cmisSession = getCmisSession(user);
		System.out.println("Getting session " + cmisSession);

		RepositoryInfo info = cmisSession.getRepositoryInfo();
		RepositoryCapabilities caps = cmisSession.getRepositoryInfo()
				.getCapabilities();
		Folder rootFolder = cmisSession.getRootFolder();
		if (!caps.isGetDescendantsSupported()) {
			System.out.println("n Warning: getDescendants "
					+ "not supported in this repository");
		} else {
			System.out.println("ngetDescendants "
					+ "is supported on this repository.");
			System.out.println("nDescendants of " + rootFolder.getName()
					+ " : ");
			for (Tree<FileableCmisObject> t : rootFolder.getDescendants(-1)) {
				printTree(t, "");
			}
		}

	}

	private static void printTree(Tree<FileableCmisObject> tree, String tab) {
		System.out.println(tab + "Descendant " + tree.getItem().getName());
		for (Tree<FileableCmisObject> t : tree.getChildren()) {
			printTree(t, tab + " ");
		}
	}
}
