/**
 * 
 */
package com.erpsystem.crms.constants;

/**
 * @author AMRUTA
 *
 */
public class CrmsSqlQueryConstants {
	
	public static final String INSERT_MASTER_ENTITY = "INSERT INTO MASTER_ENTITY (id, eaid, entity_key,value) VALUES (0,?,?,?);";

	public static final String UPDATE_MASTER_ENTITY = "UPDATE MASTER_ENTITY SET value = ? where eaid = ?;";
	
	public static final String GET_ENTITY_ID = "SELECT ENTITY_ID FROM ENTITY_DATA WHERE NAME = ?;";
	
	public static final String GET_ATTRIBUTE_ID = "SELECT attribute_id FROM attribute_entity WHERE name = ?;";
	
	public static final String GET_EA_ID = "SELECT eaid FROM link_entity_attribute WHERE attribute_id = ? and ENTITY_ID = ?;";
	
	public static final String GET_CURRENT_ENTITY_KEY = "SELECT max(entity_key) FROM master_entity WHERE eaid = ?;";
	
	public static final String GET_IDENTIFIER = "SELECT max(entity_key) FROM master_entity WHERE eaid = ?;";
	
	public static final String GET_USER_DTLS_BY_MOB = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'person' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 4 and e.entity_name='person'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";

	public static final String GET_ENQUIRY_BY_PROCESS_STATUS = "SELECT entity_id,GROUP_CONCAT(ex.value SEPARATOR ',') As Values1 FROM\r\n" + 
			"(select entity_id,value from erp_view ev where ev.entity_name = 'enquiry' and ev.entity_key IN\r\n" + 
			"(select entity_key from erp_view e where e.attribute_id = 25 and e.entity_name='enquiry'\r\n" + 
			" and e.value = ?) order by eaid asc) ex;";
	
	public static final String GENERIC_DATA_SEARCH = "SELECT e.entity_key,e.eaid,e.value ,e.entity_name  FROM erp_view e where e.entity_name IN\r\n" + 
			"(SELECT e.entity_name FROM erp_view e where e.value like //%?//% and e.entity_name=?)\r\n" + 
			"and e.entity_key IN (SELECT e.entity_key FROM erp_view e where e.value like //%?//% and\r\n" + 
			"e.entity_name=?) order by entity_key asc;";
}
