package com.imooc.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imooc.employee.dao.EmployeeDao;
import com.imooc.employee.domain.Employee;
import com.imooc.employee.domain.PageBean;
import com.imooc.employee.service.EmployeeService;

/**
 *Ա�������ҵ����ʵ����
 */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	//ע��DAO
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	/**
	 * ҵ����¼�ķ���
	 */
	public Employee login(Employee employee) {
		
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		
		return existEmployee;
	}

	/**
	 * ҵ���ķ�ҳ��ѯԱ���ķ���
	 */
	@Override
	public PageBean<Employee> finByPage(int currPage) {
		
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//��װ��ǰ��ҳ��
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ�ļ�¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ��㱣��Ա���ķ���
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	//ҵ������Ա����ID��ѯԱ���ķ���
	@Override
	public Employee findById(int eid) {
		
		return employeeDao.findById(eid);
	}

	//ҵ����޸�Ա���ķ���
	@Override
	public void update(Employee employee) {
		
		employeeDao.update(employee);
	}

	//ҵ���ɾ��Ա���ķ���
	@Override
	public void delete(Employee employee) {
		
		employeeDao.delete(employee);
	}
	
}
