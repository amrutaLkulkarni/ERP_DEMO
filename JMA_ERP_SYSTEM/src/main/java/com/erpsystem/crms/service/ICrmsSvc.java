/**
 * 
 */
package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.List;

import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DesignationModel;
import com.erpsystem.crms.model.EmployeeModel;
import com.erpsystem.crms.model.InquiryModel;
import com.erpsystem.crms.model.PersonModel;
import com.erpsystem.crms.model.TaskModel;



/**
 * @author AMRUTA
 *
 */
public interface ICrmsSvc {

	public void orchestrateUserCreation(final PersonModel personModel) throws ClassNotFoundException, SQLException, Exception;

	public void enquiryCreation(final InquiryModel inquiryModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void communicationCreation(final CommunicationModel communicationModel) throws ClassNotFoundException, SQLException, Exception;
	
	public  void taskCreation(final TaskModel taskModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void employeeCreation(final EmployeeModel employeeModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void designationCreation(final DesignationModel designationModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void branchCreation(final BranchModel branchModel) throws ClassNotFoundException, SQLException, Exception;
	
	public PersonModel getPersonDataByMobId(final String mobNo) throws Exception;
	
	public InquiryModel getInquiryByProcessStatus(final String processStatus) throws Exception;
	
	public List<PersonModel> searchDataSvc(final String entityName,final String searchString) throws Exception; 
	
}
