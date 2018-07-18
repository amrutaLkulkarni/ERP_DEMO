/**
 * @author Amruta Lalit Kulkarni
 * Created date  : 07-Jul-2018
 * Modified date : 14-Jul-2018
 * @version 1.0
 * 
 */
package com.erpsystem.crms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erpsystem.crms.model.BranchModel;
import com.erpsystem.crms.model.CommunicationModel;
import com.erpsystem.crms.model.DataSearchModel;
import com.erpsystem.crms.model.DesignationModel;
import com.erpsystem.crms.model.InquiryModel;
import com.erpsystem.crms.model.PersonModel;
import com.erpsystem.crms.model.TaskModel;
import com.erpsystem.crms.model.UserDetailRequest;
import com.erpsystem.crms.service.ICrmsSvc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
	This class is the controller and acts as the entry point to the web service.
*/
@RestController
@RequestMapping(value = "/crms")
@Api(tags = {""})
public class CrmsController extends AbstractRestHandler {
	
	@Autowired
	ICrmsSvc crmsSvc;
	
	@RequestMapping(value = "/addPerson",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.CREATED)
			@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public void createUser(@RequestBody PersonModel personModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
				try {
					crmsSvc.orchestrateUserCreation(personModel);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
		
	}
	
	@RequestMapping(value = "/addEnquiry",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.CREATED)
			@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public void createUser(@RequestBody InquiryModel inquiryModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
				try {
					crmsSvc.enquiryCreation(inquiryModel);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
		
	}
	
	@RequestMapping(value = "/addCommunication",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.CREATED)
			@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public void createCommunication(@RequestBody CommunicationModel communicationModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
				try {
					crmsSvc.communicationCreation(communicationModel);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
		
	}
	
	@RequestMapping(value = "/addTask",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.CREATED)
			@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public void createTask(@RequestBody TaskModel taskModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
				try {
					crmsSvc.taskCreation(taskModel);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
		
	}
	
	
	@RequestMapping(value = "/AddDesignation",
	method = RequestMethod.POST,
	consumes = {"application/json", "application/xml"},
	produces = {"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
			+ "details and persists the details in the system database."
			+ "If the persist operation is successfull then http status code 201 created is returned.")
	public void createDesignation(@RequestBody DesignationModel designationModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

System.out.println("Hello");
		try {
			crmsSvc.designationCreation(designationModel);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Done");

}
	@RequestMapping(value = "/AddBranch",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.CREATED)
			@ApiOperation(value = "Creates a user in the system database", notes = "The web service accepts the user "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public void createBranch(@RequestBody BranchModel branchModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {

		System.out.println("Hello");
				try {
					crmsSvc.branchCreation(branchModel);
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				System.out.println("Done");

		}
	
	@RequestMapping(value = "/getUserDetails",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.OK)
			@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public PersonModel getUserDetails(@RequestBody UserDetailRequest userDetailRequest,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
		PersonModel personModel = new PersonModel();
				try {
					if(null!=userDetailRequest && null!=userDetailRequest.getMobileNo()) {
						personModel = crmsSvc.getPersonDataByMobId(userDetailRequest.getMobileNo());
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
				return personModel;
		
	}
	
	@RequestMapping(value = "/getEnquiryDetails",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.OK)
			@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public InquiryModel getEnquiryDetails(@RequestBody UserDetailRequest userDetailRequest,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
		InquiryModel inquiryModel = new InquiryModel();
				try {
					if(null!=userDetailRequest && null!=userDetailRequest.getProcessStatus()) {
						
						inquiryModel = crmsSvc.getInquiryByProcessStatus(userDetailRequest.getProcessStatus());
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
				return inquiryModel;
		
	}
	
	
	@RequestMapping(value = "/searchPersonDetails",
			method = RequestMethod.POST,
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"})
			@ResponseStatus(HttpStatus.FOUND)
			@ApiOperation(value = "Creates a inquiry in the system database", notes = "The web service accepts the inquiry "
					+ "details and persists the details in the system database."
					+ "If the persist operation is successfull then http status code 201 created is returned.")
			public List<PersonModel> searchPersonDetails(@RequestBody DataSearchModel dataSearchModel,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		System.out.println("Hello");
		List<PersonModel> personModel = new ArrayList<>();
		
				try {
					
					if(null!=dataSearchModel && null!=dataSearchModel.getSearchKey()) {
						
						personModel = crmsSvc.searchDataSvc("person", dataSearchModel.getSearchKey());
						
					}
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		
				System.out.println("Done");
				
				return personModel;
		
	}
	
	
	}
