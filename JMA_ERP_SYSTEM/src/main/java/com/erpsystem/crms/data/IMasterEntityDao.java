/**
 * 
 */
package com.erpsystem.crms.data;

import java.sql.SQLException;
import com.erpsystem.crms.model.MasterEntityModel;

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

	
	
}
