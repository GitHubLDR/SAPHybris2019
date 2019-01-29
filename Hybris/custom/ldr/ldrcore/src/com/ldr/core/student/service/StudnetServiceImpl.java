/**
 *
 */
package com.ldr.core.student.service;

import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;

import com.ldr.core.event.StudentRegistrationEvent;
import com.ldr.core.model.StudentModel;
import com.ldr.core.student.dao.StudentDAO;


/**
 * @author devreddy
 *
 */
public class StudnetServiceImpl implements StudentService
{

	private StudentDAO studentDAO;

	private EventService eventService;

	private BaseStoreService baseStoreService;

	private BaseSiteService baseSiteService;

	private CommonI18NService commonI18NService;

	/*
	 * @see com.ldr.core.student.service.StudentService#getAllStudentDetails()
	 */
	@Override
	public List<StudentModel> getAllStudentDetails()
	{
		return studentDAO.getAllStudentDetails();
	}

	/*
	 * @see com.ldr.core.student.service.StudentService#getStudentById(java.lang.String)
	 */
	@Override
	public StudentModel getStudentById(final String studentId)
	{
		return studentDAO.getStudentByIdGenric(studentId);
	}

	/**
	 * @return the studentDAO
	 */
	public StudentDAO getStudentDAO()
	{
		return studentDAO;
	}

	/**
	 * @param studentDAO
	 *           the studentDAO to set
	 */
	public void setStudentDAO(final StudentDAO studentDAO)
	{
		this.studentDAO = studentDAO;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.student.service.StudentService#doStudentRegistration(org.training.model.StudentModel)
	 */
	@Override
	public void doStudentRegistration(final StudentModel studentModel)
	{

		final StudentRegistrationEvent studentRegistrationEvent = new StudentRegistrationEvent();

		studentDAO.doStudentRegistration(studentModel);

		studentRegistrationEvent.setBaseStore(getBaseStoreService().getCurrentBaseStore());
		studentRegistrationEvent.setSite(getBaseSiteService().getCurrentBaseSite());
		studentRegistrationEvent.setCurrency(getCommonI18NService().getCurrentCurrency());
		studentRegistrationEvent.setLanguage(getCommonI18NService().getCurrentLanguage());
		studentRegistrationEvent.setStudent(studentModel);
		eventService.publishEvent(studentRegistrationEvent);

	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	
	/**
	 * @return the eventService
	 */
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

}
