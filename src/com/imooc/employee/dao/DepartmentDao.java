package com.imooc.employee.dao;

import java.util.List;

import com.imooc.employee.domain.Department;

/**
 *���Ź����DAO�ӿ�
 */
public interface DepartmentDao {

	int findCount();


	List<Department> findByPage(int begin, int pageSize);


	void save(Department department);


	Department finById(int did);


	void update(Department department);


	void delete(Department department);


	List<Department> findAll();

}
