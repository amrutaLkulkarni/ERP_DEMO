/**
 * 
 */
package com.erpsystem.crms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.erpsystem.crms.data.IMasterEntityDao;
import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DesignationModel;
import com.erpsystem.crms.model.EmployeeModel;
import com.erpsystem.crms.model.EnquiryForModel;
import com.erpsystem.crms.model.EnquirySourceModel;
import com.erpsystem.crms.model.EntityDetailsModel;
import com.erpsystem.crms.model.InquiryModel;
import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.model.PersonModel;
import com.erpsystem.crms.model.StatusValueModel;
import com.erpsystem.crms.model.TargetProductModel;
import com.erpsystem.crms.model.TaskModel;

/**
 * @author AMRUTA
 *
 */

@Service
public class CrmsSvcImpl implements ICrmsSvc {
	
	@Autowired
	IMasterEntityDao masterEntityDao;
	
	public void addUserInSystem(final String entityName,final String attrName,final String attrValue,boolean isIdentity) 
			throws Exception {
		
		if(null!=masterEntityDao) {
			
			EntityDetailsModel entityDetailsModel = getmasterEntityModelDtls(entityName,attrName,attrValue);
			MasterEntityModel masterEntityModel = new MasterEntityModel();
			
			if(isIdentity) {
				long eaid = Long.valueOf(entityDetailsModel.getEaid());
				long entityKey = masterEntityDao.getIdentifier(eaid);
				masterEntityModel.setEaid(eaid);
				masterEntityModel.setEntityKey(entityKey);
				masterEntityModel.setValue(Long.toString(entityKey));
				
			} else {
				
				masterEntityModel.setEaid(entityDetailsModel.getEaid());
				masterEntityModel.setEntityKey(entityDetailsModel.getEntityKey());
				masterEntityModel.setValue(attrValue);
			}
			
			masterEntityDao.addDataInMasterEntity(masterEntityModel);
		}
		
	}
	public  void updateUserInSystem(final String entityName,final String attrName,final String attrValue,final long id, boolean isIdentity)
			throws Exception {
if(null!=masterEntityDao) {
	
			EntityDetailsModel entityDetailsModel = getmasterEntityModelDtlsUpd(entityName,attrName,attrValue,id);
			MasterEntityModel masterEntityModel = new MasterEntityModel();
			
			if(isIdentity) {
				long eaid = Long.valueOf(entityDetailsModel.getEaid());
				long entityKey = masterEntityDao.getIdentifier(eaid);
				masterEntityModel.setEaid(eaid);
				masterEntityModel.setEntityKey(id);
				masterEntityModel.setValue(Long.toString(entityKey));
				
			} else {
				
				masterEntityModel.setEaid(entityDetailsModel.getEaid());
				masterEntityModel.setEntityKey(entityDetailsModel.getEntityKey());
				masterEntityModel.setValue(attrValue);
			}
			masterEntityDao.updateDataInMasterEntity(masterEntityModel);
		
	}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PersonModel> searchDataSvc(final String entityName,final String searchString) throws Exception {
		
		List<PersonModel> personModel = masterEntityDao.searchData(entityName, searchString);
		return personModel;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<InquiryModel> searchEnquirySvc(final String entityName,final String searchString) throws Exception {
		
		List<InquiryModel> inquiryModel = masterEntityDao.searchEnquiryData(entityName, searchString);
		return inquiryModel;
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CommunicationModel> searchCommunicationSvc(String entityName, String searchString) throws Exception {
		// TODO Auto-generated method stub
		List<CommunicationModel> communicationModel = masterEntityDao.searchCommData(entityName, searchString);
		return communicationModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TaskModel> searchTaskSvc(String entityName, String searchString) throws Exception {
		// TODO Auto-generated method stub
		List<TaskModel> taskModel = masterEntityDao.searchTaskData(entityName, searchString);
		return taskModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<EmployeeModel> searchEmployeeSvc(final String entityName, final String searchString) throws Exception{
		
		List<EmployeeModel> employeeModel = masterEntityDao.searchEmployeeData(entityName, searchString);
		return employeeModel;
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void orchestrateUserCreation(final PersonModel personModel) 
			throws Exception {
		
		addUserInSystem("person", "personid", "",true);
		addUserInSystem("person", "fname", personModel.getFirstName(),false);
		addUserInSystem("person", "lname", personModel.getLastName(),false);
		addUserInSystem("person", "mobno", personModel.getMobileNo(),false);
		addUserInSystem("person", "address", personModel.getAddress(),false);
		addUserInSystem("person", "dob","" + personModel.getDob(),false);
		addUserInSystem("person", "age", "" + personModel.getAge(),false);
		addUserInSystem("person", "aadharno", personModel.getAadharNo(),false);
		addUserInSystem("person", "panno", personModel.getPanNo(),false);
		addUserInSystem("person", "mailid", personModel.getMailId(),false);
		addUserInSystem("person", "organisation", personModel.getOrganisation(),false);
		addUserInSystem("person", "orglocation", personModel.getOrgLocation(),false);
		addUserInSystem("person", "orgrole", personModel.getOrgRole(),false);
		addUserInSystem("person", "city",""+ personModel.getCity(),false);
		addUserInSystem("person", "pincode", personModel.getPinCode(),false);
		addUserInSystem("person", "occupation", personModel.getOccupation(),false);
		addUserInSystem("person", "branch","" + personModel.getBranch(),false);
		addUserInSystem("person", "reffid","" + personModel.getReffId(),false);
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void personUpdate(final PersonModel personModel)
			throws Exception {
		updateUserInSystem("person", "personid",""+ personModel.getPersonId(),personModel.getPersonId(),false);
		updateUserInSystem("person", "fname", personModel.getFirstName(),personModel.getPersonId(),false);
		updateUserInSystem("person", "lname", personModel.getLastName(),personModel.getPersonId(),false);
		updateUserInSystem("person", "mobno", personModel.getMobileNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "address", personModel.getAddress(),personModel.getPersonId(),false);
		updateUserInSystem("person", "dob","" + personModel.getDob(),personModel.getPersonId(),false);
		updateUserInSystem("person", "age", "" + personModel.getAge(),personModel.getPersonId(),false);
		updateUserInSystem("person", "aadharno", personModel.getAadharNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "panno", personModel.getPanNo(),personModel.getPersonId(),false);
		updateUserInSystem("person", "mailid", personModel.getMailId(),personModel.getPersonId(),false);
		updateUserInSystem("person", "organisation", personModel.getOrganisation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "orglocation", personModel.getOrgLocation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "orgrole", personModel.getOrgRole(),personModel.getPersonId(),false);
		updateUserInSystem("person", "city",""+ personModel.getCity(),personModel.getPersonId(),false);
		updateUserInSystem("person", "pincode", personModel.getPinCode(),personModel.getPersonId(),false);
		updateUserInSystem("person", "occupation", personModel.getOccupation(),personModel.getPersonId(),false);
		updateUserInSystem("person", "branch","" + personModel.getBranch(),personModel.getPersonId(),false);
		updateUserInSystem("person", "reffid","" + personModel.getReffId(),personModel.getPersonId(),false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)    
	public void enquiryUpdate(InquiryModel inquiryModel) 
				throws Exception{
		updateUserInSystem("enquiry", "enquiryid",""+ inquiryModel.getInquiryId(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "datevalue", inquiryModel.getDatevalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "branch",""+ inquiryModel.getBranch(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "enquirysource",""+ inquiryModel.getEnquirysource(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "enquiryfor",""+ inquiryModel.getEnquiryfor(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "targetproduct", ""+inquiryModel.getTargetProduct(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "description", inquiryModel.getDescription(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "processstatus", inquiryModel.getProcessStatus(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "followupdate", inquiryModel.getFollowupdate(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "remarkvalue", inquiryModel.getRemarkvalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "statusvalue", ""+inquiryModel.getStatusvalue(), inquiryModel.getInquiryId(), false);
		updateUserInSystem("enquiry", "lastupdate", inquiryModel.getLastUpdate(), inquiryModel.getInquiryId(), false);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void enquiryCreation(final InquiryModel inquiryModel) 
			throws Exception {
		
		addUserInSystem("enquiry", "inquiryid", "",true);
		addUserInSystem("enquiry", "datevalue", inquiryModel.getDatevalue(),false);
		addUserInSystem("enquiry", "branch", ""+inquiryModel.getBranch(),false);
		addUserInSystem("enquiry", "enquirysource",""+ inquiryModel.getEnquirysource(),false);
		addUserInSystem("enquiry", "enquiryfor", ""+inquiryModel.getEnquiryfor(),false);
		addUserInSystem("enquiry", "targetproduct",""+inquiryModel.getTargetProduct(),false);
		addUserInSystem("enquiry", "description", inquiryModel.getDescription(),false);
		addUserInSystem("enquiry", "processstatus", inquiryModel.getProcessStatus(),false);
		addUserInSystem("enquiry", "followupdate",inquiryModel.getFollowupdate(),false);
		addUserInSystem("enquiry", "remarkvalue", inquiryModel.getRemarkvalue(),false);
		addUserInSystem("enquiry", "statusvalue",""+ inquiryModel.getStatusvalue(),false);
		addUserInSystem("enquiry", "lastupdate", ""+inquiryModel.getLastUpdate(),false);
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void communicationCreation(final CommunicationModel communicationModel)
	          throws Exception{
		addUserInSystem("communication", "commid", "", true);
		addUserInSystem("communication", "commedium", ""+communicationModel.getMedium(), false);
		addUserInSystem("communication", "datealue",""+ communicationModel.getDatevalue(), false);
		addUserInSystem("communication", "description", communicationModel.getDescription(), false);
		addUserInSystem("communication", "processstatus", communicationModel.getProcessStatus(), false);
		addUserInSystem("communication", "conclusion", communicationModel.getConclusion(), false);
		addUserInSystem("communication", "followupdate", ""+communicationModel.getFollowupdate(), false);
		addUserInSystem("communication", "remarkvalue", communicationModel.getRemarkvalue(), false);
		addUserInSystem("communication", "personid", ""+communicationModel.getPersonid(), false);
		addUserInSystem("communication", "inquiryid", ""+communicationModel.getEnquiryid(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void taskCreation(final TaskModel taskModel) 
			throws Exception{
		addUserInSystem("task", "taskid", "", true);
		addUserInSystem("task", "title", taskModel.getTitle(), false);
		addUserInSystem("task", "assignto",""+ taskModel.getAssignto(), false);
		addUserInSystem("task", "description", taskModel.getDescription(), false);
		addUserInSystem("task", "expdate",""+ taskModel.getExpdate(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void employeeCreation(final EmployeeModel employeeModel)
			throws Exception{
		addUserInSystem("emplyoee", "employeeid", "", true);
		addUserInSystem("employee", "dateofjoining", ""+employeeModel.getDateofjoining(), false);
		addUserInSystem("employee", "designation", employeeModel.getDesignation(), false);
		addUserInSystem("employee", "branch", ""+ employeeModel.getBranch(), false);
		addUserInSystem("employee", "reportingto", ""+ employeeModel.getReportingTo(), false);
		addUserInSystem("employee", "personid", ""+ employeeModel.getPersonId(), false);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void designationCreation(DesignationModel designationModel)
			throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("designation", "desgnid", "", true);
		addUserInSystem("designation", "desgName", designationModel.getDesgName(), false);
				
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void branchCreation(BranchModel branchModel) 
			throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("branch", "branchid", "", true);
		addUserInSystem("branch", "title", branchModel.getTitle(), false);
		addUserInSystem("branch", "location", branchModel.getLocation(), false);
		addUserInSystem("branch", "contno", branchModel.getContno(), false);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void enquirysourceCreation(EnquirySourceModel enquirySourceModel)
		throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("enquirysource", "id", "", true);
		addUserInSystem("enquirysource", "enquirysource", enquirySourceModel.getSource(), false);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void enquiryforCreation(EnquiryForModel enquiryForModel)
		throws ClassNotFoundException, SQLException, Exception {
		addUserInSystem("enquiryfor", "id", "", true);
		addUserInSystem("enquiryfor", "enquiryfor", enquiryForModel.getEnqfor(), false);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void targetProductCreation(TargetProductModel targetProductModel)
			throws ClassNotFoundException, SQLException, Exception {
			addUserInSystem("targetproduct", "id", "", true);
			addUserInSystem("targetproduct", "targetproduct", targetProductModel.getTargetproduct(), false);
		}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void statusvalueCreation(StatusValueModel statusValueModel)
			throws ClassNotFoundException, SQLException, Exception {
			addUserInSystem("statusvalue", "id", "", true);
			addUserInSystem("statusvalue", "statusvalue", statusValueModel.getStatusvalue(), false);
		}
	
	private EntityDetailsModel getmasterEntityModelDtls(final String entityName,final String attrName,final String attrValue) 
			throws Exception {
		
		/** Step 1 : Fetch the entity id on the basis of entity name from entity data table.*/
		final long entityId = masterEntityDao.getEntityId(entityName);
		/** Step 2 : Fetch the attribute id on the basis of name from the attribute entity table.*/
		final long attributeId = masterEntityDao.getAttributeId(attrName);
		/** Step 3 : Fetch eaid on the basis of entity id and attribute id */
		final long eaid = masterEntityDao.getEAId(entityId, attributeId);
		/** Step 4 : Fetch the latest entity key from the master_entity table on the basis of eaid fetched in step 3 */
		final long entityKey = masterEntityDao.getLatestEntityKeyAndIncrement(eaid);
		
		EntityDetailsModel masterDetailsModel = new EntityDetailsModel();
		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(entityKey);
		masterDetailsModel.setEntityId(entityId);
		masterDetailsModel.setAttributeId(attributeId);
	
		return masterDetailsModel;
		
	}
	private EntityDetailsModel getmasterEntityModelDtlsUpd(final String entityName,final String attrName,final String attrValue,long id) 
			throws Exception {
		
		/** Step 1 : Fetch the entity id on the basis of entity name from entity data table.*/
		final long entityId = masterEntityDao.getEntityId(entityName);
		/** Step 2 : Fetch the attribute id on the basis of name from the attribute entity table.*/
		final long attributeId = masterEntityDao.getAttributeId(attrName);
		/** Step 3 : Fetch eaid on the basis of entity id and attribute id */
		final long eaid = masterEntityDao.getEAId(entityId, attributeId);
		/** Step 4 : Fetch the latest entity key from the master_entity table on the basis of eaid fetched in step 3 */
		//final long entityKey = masterEntityDao.getLatestEntityKey(eaid);
		
		EntityDetailsModel masterDetailsModel = new EntityDetailsModel();
		
		masterDetailsModel.setEaid(eaid);
		masterDetailsModel.setEntityKey(id);
		masterDetailsModel.setEntityId(entityId);
		masterDetailsModel.setAttributeId(attributeId);
	
		return masterDetailsModel;
		
	}
	
	public PersonModel getPersonDetailsID(long personid) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		PersonModel personModel = new PersonModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getPersonDetailsID(personid);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : personModel.setPersonId(Long.parseLong(userField));
			break;
			case 1 : personModel.setFirstName(userField);
			break;
			case 2 : personModel.setLastName(userField);
			break;
			case 3 : personModel.setMobileNo(userField);
			break;
			case 4 : personModel.setAddress(userField);
			break;
			case 5 : personModel.setDob(userField);
			break;
			case 6 : personModel.setAge(userField);
			break;
			case 7 : personModel.setAadharNo(userField);
			break;
			case 8 : personModel.setPanNo(userField);
			break;
			case 9 : personModel.setMailId(userField);
			break;
			case 10 : personModel.setOrganisation(userField);
			break;
			case 11 : personModel.setOrgLocation(userField);
			break;
			case 12 : personModel.setOrgRole(userField);
			break;
			case 13 : personModel.setCity(userField);
			break;
			case 14 : personModel.setPinCode(userField);
			break;
			case 15 : personModel.setOccupation(userField);
			break;
			case 16 : personModel.setBranch(userField);
			break;
			case 17 : personModel.setReffId(Long.parseLong(userField));
		    break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		return personModel;
	}
	public PersonModel getPersonDataByMobId(final String mobNo) throws Exception {
		
		String[] userDataArr = null;
		PersonModel personModel = new PersonModel();
		
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getUserDetailsByMobileNumber(mobNo);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : personModel.setPersonId(Long.parseLong(userField));
			break;
			case 1 : personModel.setFirstName(userField);
			break;
			case 2 : personModel.setLastName(userField);
			break;
			case 3 : personModel.setLastName(userField);
			break;
			case 4 : personModel.setMobileNo(userField);
			break;
			case 5 : personModel.setAddress(userField);
			break;
			case 6 : personModel.setDob(userField);
			break;
			case 7 : personModel.setDob(userField);
			break;
			
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		return personModel;
		
		
	}

	@Override
	public InquiryModel getInquiryByProcessStatus(String processStatus) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		InquiryModel inquiryModel = new InquiryModel();
		
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getInquiryByProcessStatus(processStatus);
		}
		
		int cnt = 0;
		
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			
			case 0 : inquiryModel.setInquiryId(Long.parseLong(userField));
			break;
			case 1 : inquiryModel.setDescription(userField);
			break;
			case 2 : inquiryModel.setRemarkvalue(userField);
			break;
			case 3 : inquiryModel.setBranch(Long.parseLong(userField));
			break;
			case 4 : inquiryModel.setEnquiryfor(Long.parseLong(userField));
			break;
			case 5 : inquiryModel.setEnquirysource(Long.parseLong(userField));
			break;
			case 6 : inquiryModel.setTargetProduct(Long.parseLong(userField)); 
			break;
			case 7 : inquiryModel.setStatusvalue(Long.parseLong(userField));
			break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		return inquiryModel;
	}
	
	public DesignationModel getDesigntionId(long designationId) throws Exception {
		// TODO Auto-generated method stub
		String[] userDataArr = null;
		DesignationModel designationModel = new DesignationModel();
		if(null!=masterEntityDao) {
			userDataArr = masterEntityDao.getDesignation(designationId);
		}
		
		int cnt = 0;
		for(String userField : userDataArr) {
			
			switch(cnt) {
			
			case 0 : designationModel.setDesgnid(Long.parseLong(userField));
			break;
			case 1 : designationModel.setDesgName(userField);
			break;
			default : System.out.println("Do nothing");
			
			}
			
			++cnt;
		}
		
		
		return designationModel;
	}


	
	
	

	
	
	
	
	
}
