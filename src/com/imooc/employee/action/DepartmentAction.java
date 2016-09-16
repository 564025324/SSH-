package com.imooc.employee.action;

import com.imooc.employee.domain.Department;
import com.imooc.employee.domain.PageBean;
import com.imooc.employee.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *���Ź���Action��
 */
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

	
	//ģ������ʹ�õĶ���
	private Department department = new Department();
	@Override
	public Department getModel() {
		
		return department;
	}
	
	private Integer currPage = 1;
	
	
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	//ע��departmentService
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//�ṩһ����ѯ�ķ���
	public String findAll(){
		
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		//��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
		
	}
	
	//��ת��һ����Ӳ���ҳ��ķ���
	public String saveUI(){
		return "saveUI";
	}
	
	//��Ӳ��ŵ�ִ�з���
	public String save(){
		departmentService.save(department);
		return "saveSuccess";
	}
	
	//�༭���ŵ�ִ�еķ���
	public String edit(){
		
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	//�޸Ĳ��ŵ�ִ�з���
	public String update(){
		
		departmentService.update(department);
		return "updateSuccess";
	}
	
	//ɾ�����ŵ�ִ�з���
	public String delete(){
		
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}

}
