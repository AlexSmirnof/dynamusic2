package dynamusic;

import java.util.Date;

import atg.repository.RepositoryItemImpl;
import atg.repository.RepositoryPropertyDescriptor;
 
public class FindAge extends RepositoryPropertyDescriptor {

//	Declare the String member variable mAgeFormat, which has two possible values: years or days
	String mAgeFormat = "years";
	
//	Declare a constant called AGE_FORMAT_ATTR_NAME that is set to "ageFormat". 
//	This will be the name of attribute that is set in the XML repository definition file to control the age format.  
//	Remember that a constant is defined as a public static final member variable
	public static final String AGE_FORMAT_ATTR_NAME = "ageFormat";
	
//	This code would register this property descriptor so that we can refer to it by name rather than the full path.
//	static {
//		RepositoryPropertyDescriptor.registerPropertyDescriptorClass("FindAge", FindAge.class);
//	}
	
	public FindAge(){
		super();
	}
	
	/** 
	   Return the age for this user, based on his or her birthday
    **/
	public Object getPropertyValue(RepositoryItemImpl pItem, Object pValue){
		System.out.println("Calling getPropertyValue " + pItem);
		
		Date dob = (Date) pItem.getPropertyValue("dateOfBirth");
		
		if (dob != null) {
			if (mAgeFormat.equalsIgnoreCase("days")) {
				return new Integer(AgeCalc.ageInDays(dob));
			}
			else {
				return new Integer(AgeCalc.ageInYears(dob));
			}
		}
		System.out.println("dob unset, returning -1");
		return new Integer(-1);
	}

//	setValue allow for the reading of the ageFormat attribute value from the repository definition file.  
	@Override
	public void setValue(String pAttributeName, Object pValue) {
		super.setValue(pAttributeName, pValue);
		if (pAttributeName.equalsIgnoreCase(AGE_FORMAT_ATTR_NAME)) {
			mAgeFormat = pValue.toString();
		}
	}
	
//	Returns property Queryable - this needs to be false because this property is not stored in the database.
	@Override
	public boolean isQueryable() {
		return false;
	}

//	 Returns property Writable. - this property is always false because it doesn't store a value;
//	 it only returns a formatted version of values stored in other properties.
	@Override
	public boolean isWritable() {
		return false;
	}
	
}
