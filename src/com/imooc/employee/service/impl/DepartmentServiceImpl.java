package com.imooc.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.imooc.employee.dao.DepartmentDao;
import com.imooc.employee.domain.Department;
import com.imooc.employee.domain.PageBean;
import com.imooc.employee.service.DepartmentService;

/**
 *���Ź����ҵ����ʵ����
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	//ע�벿�Ź����DAO
	
	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}



	//��ҳ��ѯ���ֵķ���
	@Override
	public PageBean<Department> findByPage(Integer currPage) {
		
		PageBean<Department> pageBean = new PageBean<Department>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ��¼��
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ������
		int begin = (currPage -1)* pageSize;
		List<Department> list = departmentDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		
		
		return pageBean;
	}


	//ҵ��㱣�沿�ŵķ���
	@Override
	public void save(Department department) {
		
		departmentDao.save(department);
		
	}


	//ҵ�����ݲ��ŵ�ID��ѯ���ŵķ���
	@Override
	public Department findById(int did) {
		
		
		return departmentDao.finById(did);
	}


	//ҵ�����Ӳ���
	@Override
	public void update(Department department) {
		
		departmentDao.update(department);
		
	}


	//ҵ������Daoɾ������
	@Override
	public void delete(Department department) {

		departmentDao.delete(department);
	}


	//��ѯ���в��ŵķ���
	@Override
	public List<Department> findAll() {
		
		return departmentDao.findAll();
	}
	

}
