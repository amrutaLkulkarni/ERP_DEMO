/**
 * 
 */
package com.erpsystem.crms.data;

import java.sql.SQLException;
import java.util.List;

import com.erpsystem.crms.model.MasterEntityModel;
import com.erpsystem.crms.model.PersonModel;

/**
 * @author AMRUTA
 *
 */
public interface IMasterEntityDao {
	
	public void addDataInMasterEntity(final MasterEntityModel masterEntityModel) throws ClassNotFoundException, SQLException, Exception;
	
	public void updateDataInMasterEntity(final MasterEntityModel masterEntityModel) throws ClassNotFoundException, SQLException, Exception;
	
	public long getEntityId(final String entityName) throws ClassNotFoundException, SQLException, Exception;
	
	public long getAttributeId(final String attributeName) throws ClassNotFoundException, SQLException, Exception;

	public long getEAId(final long entityId,final long attributeId) throws ClassNotFoundException, SQLException, Exception;
	
	public long getLatestEntityKeyAndIncrement(final long eaId) throws ClassNotFoundException, SQLException, Exception;
	
	public long getIdentifier(final long eaId) throws ClassNotFoundException, SQLException, Exception;
	
	public String[] getUserDetailsByMobileNumber(final String mobNo) throws Exception;
	
	public String[] getInquiryByProcessStatus (final String processStatus) throws Exception;
	
	public List<PersonModel> searchData(final String entityName,final String searchString) throws Exception;

	
	
}
