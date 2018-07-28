/**
 * 
 */
package com.erpsystem.crms.data;

import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GENERIC_DATA_SEARCH;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ATTRIBUTE_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_CURRENT_ENTITY_KEY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_EA_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENQUIRY_BY_PROCESS_STATUS;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_ID;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_IDENTIFIER;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_USER_DTLS_BY_MOB;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.INSERT_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.UPDATE_MASTER_ENTITY;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_ENTITY_KEY_UPD;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_Designation;
import static com.erpsystem.crms.constants.CrmsSqlQueryConstants.GET_USER_DTLS_BY_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.EmployeeModel;
import com.erpsystem.crms.model.InquiryModel;
import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.model.PersonModel;
import com.erpsystem.crms.model.TaskModel;

/**
 * @author AMRUTA
 *
 */

@Service
public class MasterEntityDaoImpl extends AbstractDatabaseConfig implements IMasterEntityDao {
	
	private static PreparedStatement psmt = null;
	
	private static Connection conn = null;
	
	private int result =  0;
	
	public void addDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
		
		try {
			
			if(null!=masterEntityModel) {
			
			conn = getDbConn();
			psmt = conn.prepareStatement(INSERT_MASTER_ENTITY);
			psmt.setLong(1, masterEntityModel.getEaid());
			psmt.setLong(2, masterEntityModel.getEntityKey());
			psmt.setString(3, masterEntityModel.getValue());
			result = psmt.executeUpdate();
			//conn.commit();
			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	public List<PersonModel> searchData(final String entityName,final String searchString) throws Exception {
		
		ResultSet rs = null;
		int counter = 0;
		int eaid = 0;
		
		
		PersonModel personModel = new PersonModel();
		List<PersonModel> personDetailList = new ArrayList<>();
		String srchStr = "'%".concat(searchString).concat("%'");
		
		try {
			
			if(null!=entityName && null!=searchString) {
			
				String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
						"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
						"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
						"e.entity_name='"+entityName+"') order by entity_key asc;";
				
				conn = getDbConn();
				psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
				//psmt.setString(1, entityName);
				//psmt.setString(2, entityName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					
					counter++;
					eaid = rs.getInt(2);
					
					switch(eaid) {
					
					case 1 : personModel.setPersonId(rs.getLong(3));
						break;
						
					case 2 : personModel.setFirstName(rs.getString(3));
						break;
						
					case 3 : personModel.setLastName(rs.getString(3));
						break;
						
					case 4 : personModel.setMobileNo(rs.getString(3));
						break;
						
					case 5 : personModel.setAddress(rs.getString(3));
						break;
						
					case 6 : personModel.setDob(rs.getString(3));
						break;
						
					case 7 : personModel.setAge(rs.getString(3));
						break;
						
					case 8 : personModel.setAadharNo(rs.getString(3));
						break;
						
					case 9 : personModel.setPanNo(rs.getString(3));
						break;
						
					case 10 : personModel.setMailId(rs.getString(3));
						break;
						
					case 11 : personModel.setOrganisation(rs.getString(3));
						break;
						
					//case 12 : personModel.setOrganisation(organisation);(rs.getString(3));
					case 12 : personModel.setOrgLocation(rs.getString(3));	
						break;
						
					case 13 : personModel.setOrgRole(rs.getString(3));
						break;
						
					case 14 : personModel.setCity(rs.getString(3));
						break;
						
					case 15 : personModel.setPinCode(rs.getString(3));
						break;
						
					case 16 : personModel.setOccupation(rs.getString(3));
						break;	
						
					case 17 : personModel.setBranch(rs.getString(3));
						break;	
						
					case 18 : personModel.setReffId(rs.getLong(3));
						break;	
						
					//default : System.out.println("In default");	
					
					}
					
					if(counter==18) {
						personDetailList.add(personModel);
						personModel = new PersonModel();
						counter = 0;
					}
					
				}
				
				
				
				}
			
			return personDetailList;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	
public List<InquiryModel> searchEnquiryData(final String entityName,final String searchString) throws Exception {
		
		ResultSet rs = null;
		int counter = 0;
		int eaid = 0;
		
		
		InquiryModel inquiryModel = new InquiryModel();
 		List<InquiryModel> enquiryDetailList = new ArrayList<>();
		String srchStr = "'%".concat(searchString).concat("%'");
		
		try {
			
			if(null!=entityName && null!=searchString) {
			
				String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
						"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
						"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
						"e.entity_name='"+entityName+"') order by entity_key asc;";
				
				conn = getDbConn();
				psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
				//psmt.setString(1, entityName);
				//psmt.setString(2, entityName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					
					counter++;
					eaid = rs.getInt(2);
					
					switch(eaid) {
					
					case 19 : inquiryModel.setInquiryId(rs.getLong(3));
						break;
					
					case 20 : inquiryModel.setDatevalue(rs.getString(3));
						break;
					
					case 21 : inquiryModel.setBranch(rs.getLong(3));
						break;
					
					case 22 : inquiryModel.setEnquirysource(rs.getLong(3));
						break;
					
					case 23 : inquiryModel.setEnquiryfor(rs.getLong(3));
						break;
					
					case 24 : inquiryModel.setTargetProduct(rs.getLong(3));
						break;
					
					case 25 : inquiryModel.setDescription(rs.getString(3));
						break;
					
					case 26 : inquiryModel.setProcessStatus(rs.getString(3));
						break;
					
					case 27 : inquiryModel.setFollowupdate(rs.getString(3));
						break;
					
					case 28 : inquiryModel.setRemarkvalue(rs.getString(3));
						break;
					
					case 29 : inquiryModel.setStatusvalue(rs.getLong(3));
						break;
					
					case 30 : inquiryModel.setLastUpdate(rs.getString(3));
						break;
					
					//default : System.out.println("In default");	
					
					}
					
					if(counter==12) {
						enquiryDetailList.add(inquiryModel);
						inquiryModel = new InquiryModel();
						counter = 0;
					}
					
				}
				
				
				
				}
			
			return enquiryDetailList;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			} finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
public List<CommunicationModel> searchCommData(String entityName, String searchString) throws Exception {
	// TODO Auto-generated method stub
	
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	CommunicationModel communicationModel = new CommunicationModel();
		List<CommunicationModel> commDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				//case 38 : employeeModel.setDateofjoining(rs.getString(3));
				case 32 : communicationModel.setMedium(rs.getLong(3));
				   break;
				   
				case 33 : communicationModel.setConclusion(rs.getString(3));
				   break;
				
				//default : System.out.println("In default");	
				
				}
				
				if(counter==12) {
					commDetailList.add(communicationModel);
					communicationModel = new CommunicationModel();
					counter = 0;
				}
				
			}
			
			
			
			}
		
		return commDetailList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}

public List<TaskModel> searchTaskData(String entityName, String searchString) throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	TaskModel taskModel = new TaskModel();
		List<TaskModel> taskDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				//case 32 : communicationModel.setMedium(rs.getLong(3));
				case 35 : taskModel.setTitle(rs.getString(3));
				   break;
				case 36 : taskModel.setAssignto(rs.getLong(3));
				   break;
				case 37 : taskModel.setExpdate(rs.getLong(3));
				   break;
				
				//default : System.out.println("In default");	
				
				}
				
				if(counter==12) {
					taskDetailList.add(taskModel);
					taskModel = new TaskModel();
					counter = 0;
				}
				
			}
		}
		return taskDetailList;
	
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
}

public List<EmployeeModel> searchEmployeeData(final String entityName,final String searchString) throws Exception {
	
	ResultSet rs = null;
	int counter = 0;
	int eaid = 0;
	
	
	EmployeeModel employeeModel = new EmployeeModel();
		List<EmployeeModel> employeeDetailList = new ArrayList<>();
	String srchStr = "'%".concat(searchString).concat("%'");
	
	try {
		
		if(null!=entityName && null!=searchString) {
		
			String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
					"(SELECT e.entity_name FROM erp_view e where e.value like "+srchStr+" and e.entity_name='"+entityName+"')\r\n" + 
					"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like "+srchStr+" and\r\n" + 
					"e.entity_name='"+entityName+"') order by entity_key asc;";
			
			conn = getDbConn();
			psmt = conn.prepareStatement(GENERIC_DATA_SEARCH);
			//psmt.setString(1, entityName);
			//psmt.setString(2, entityName);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				counter++;
				eaid = rs.getInt(2);
				
				switch(eaid) {
				case 38 : employeeModel.setDateofjoining(rs.getString(3));
				   break;
				  
				case 39 : employeeModel.setDateofjoining(rs.getString(3));
				   break;
				   
				case 40 : employeeModel.setDesignation(rs.getString(3));
				   break;
				   
				case 41 : employeeModel.setReportingTo(rs.getLong(3));  
				   break;
				
				//default : System.out.println("In default");	
				
				}
				
				if(counter==12) {
					employeeDetailList.add(employeeModel);
					employeeModel = new EmployeeModel();
					counter = 0;
				}
				
			}
			
			
			
			}
		
		return employeeDetailList;
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		} finally {
			closeResources(conn,null,psmt,null);
		}
	}
	public void updateDataInMasterEntity(final MasterEntityModel masterEntityModel) throws Exception {
	
		try {
			
			if (null != masterEntityModel) {

				conn = getDbConn();
				psmt = conn.prepareStatement(UPDATE_MASTER_ENTITY);
							
				psmt.setString(1, masterEntityModel.getValue());
				psmt.setLong(2, masterEntityModel.getEaid());
				psmt.setLong(3, masterEntityModel.getEntityKey());
				result = psmt.executeUpdate();

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,null,psmt,null);
			}
		}
	
	public long getEntityId(final String entityName) throws Exception {
		
		long entityId = 0;
		String entityidRs = null;
		ResultSet rs = null;
		
		try {
			
			if (null != entityName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENTITY_ID);
				psmt.setString(1, entityName);
				rs = psmt.executeQuery();

			}
			
			while(rs.next()) {
				entityidRs = rs.getString(1);
			}
			
			entityId = Long.parseLong(entityidRs);
			
		} catch(final Exception exception) {
				throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityId;
		
		}
	
	public long getAttributeId(final String attributeName) throws Exception {
		
		long attributeId = 0;
		
		String attributeidRs = null;
		ResultSet rs = null;
		
		try {
			
			if (null != attributeName) {

				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ATTRIBUTE_ID);
				psmt.setString(1, attributeName);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					attributeidRs = rs.getString(1);
				}
				
				attributeId = Long.parseLong(attributeidRs);

			}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return attributeId;
		
		}
	
	public long getEAId(final long entityId,final long attributeId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_EA_ID);
				psmt.setLong(2, entityId);
				psmt.setLong(1, attributeId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaidRs = rs.getString(1);
				}
				
				if(null!=eaidRs) {
					eaid = Long.parseLong(eaidRs);
				}else {
					eaid = 0;
				}
				
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
	public long getLatestEntityKeyAndIncrement(final long eaId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_CURRENT_ENTITY_KEY);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaid = rs.getLong(1);
				}
				
				eaid++;
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
public long getLatestEntityKey(final long eaId) throws Exception {
		
		String eaidRs = null;
		long eaid = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENTITY_KEY_UPD);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					eaid = rs.getLong(1);
				}
				
				
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return eaid;
		
		}
	
	public long getIdentifier(final long eaId) throws Exception {
		
		String entityKeyRs = null;
		long entityKey = 0;
		ResultSet rs = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_IDENTIFIER);
				psmt.setLong(1, eaId);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityKey = rs.getLong(1);
				}
		
				//entityKey = Long.parseLong(entityKeyRs);
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return entityKey+1;
		
		}
	

public String[] getPersonDetailsID(final long personid) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_USER_DTLS_BY_ID);
				psmt.setLong(1, personid);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}

	public String[] getUserDetailsByMobileNumber(final String mobNo) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {
			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_USER_DTLS_BY_MOB);
				psmt.setString(1, mobNo);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;
		
		}

public String[] getInquiryByProcessStatus(final String processStatus) throws Exception {
		
		String userResultSet = null;
		String entityId = null;
		String userDetailsStr = null;
		ResultSet rs = null;
		String[] userData = null;
		
		try {			
				conn = getDbConn();
				psmt = conn.prepareStatement(GET_ENQUIRY_BY_PROCESS_STATUS);
				psmt.setString(1, processStatus);
				rs = psmt.executeQuery();
						
				while(rs.next()) {
					entityId = rs.getString(1);
					userDetailsStr = rs.getString(2);
				}
		
				if(null!=userDetailsStr) {
					userData = userDetailsStr.split(",");
				}
			
		} catch(final Exception exception) {
			throw new Exception(exception);
			}finally {
				closeResources(conn,rs,psmt,null);
			}
		
		return userData;		
		}


public String[] getDesignation(long designationId) throws Exception {
	// TODO Auto-generated method stub
	String userResultSet = null;
	String entityId = null;
	String userDetailsStr = null;
	ResultSet rs = null;
	String[] userData = null;
	
	try {			
		conn = getDbConn();
		psmt = conn.prepareStatement(GET_Designation);
		psmt.setLong(1,designationId);
		
		rs = psmt.executeQuery();
				
		while(rs.next()) {
			entityId = rs.getString(1);
			userDetailsStr = rs.getString(2);
		}

		if(null!=userDetailsStr) {
			userData = userDetailsStr.split(",");
		}
		
	} catch(final Exception exception) {
		throw new Exception(exception);
		}finally {
			closeResources(conn,rs,psmt,null);
		}
	
	return userData;
}





	
}

