package dynamusic;

import java.io.IOException;

import javax.servlet.ServletException;

import atg.repository.Repository;
import atg.repository.RepositoryException;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.DynamoServlet;

public class EnumForEach extends DynamoServlet {
	
	
    private String mDummyString;
		
	public String getDummyString () {
		return mDummyString;
	}
		
	public void setDummyString(String pDummyString) {
		mDummyString = pDummyString;
	}
	
	
	@Override
	public void service(DynamoHttpServletRequest req, DynamoHttpServletResponse res)
			throws ServletException, IOException {
			
		String repositoryName = req.getParameter("repositoryName");
		String itemDescriptorName = req.getParameter("itemDescriptorName");
		String propertyName = req.getParameter("propertyName");
		
		Repository theRepository = (Repository) resolveName(repositoryName);
		
		try {
			String[] enumValues = EnumeratedProperties.getEnumeratedProperties(theRepository, itemDescriptorName, propertyName);
			if (enumValues != null) {
				for (int i = 0; i < enumValues.length; i++){
					req.setParameter("element", enumValues[i]);
					req.serviceParameter("output", req, res);
				}
			}
		} catch (RepositoryException e) {
			if (isLoggingError()){
				logError(e);
			}
			req.serviceParameter("error", req, res);
		}
				
	}
	
    //Constructor
    public EnumForEach() {}
	
}
