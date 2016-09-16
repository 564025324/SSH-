package com.imooc.employee.action;

import java.util.List;

import com.imooc.employee.domain.Department;
import com.imooc.employee.domain.Employee;
import com.imooc.employee.domain.PageBean;
import com.imooc.employee.service.DepartmentService;
import com.imooc.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *Ա�������Action��
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	private static final long serialVersionUID = 1L;
	
	//ģ������ʹ�õĶ���ͨ����¼ִ�еķ������û����������װ��employee������.
	private Employee employee = new Employee();
	
	@Override
	public Employee getModel() {
		return employee;
	}
	
	
	//ע��ҵ�����
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}


	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//���յ�ǰҳ��
	private int currPage = 1;

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}


	/**
	 * ��¼ִ�еķ���
	 * @return
	 */
	public String login(){
		
		System.out.println("login������ִ����......");
		
		//����ҵ������
		Employee existEmployee =  employeeService.login(employee);
		if(existEmployee == null){
			//�û���������
			this.addActionError("��������û��������������!");
			return INPUT;
		}else{
			//�û�������
			
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
		
		
		
	}
	
	
	/**
	 * ��ҳ��ѯԱ����ִ�еķ���
	 */
	public String findAll(){
		PageBean<Employee> pageBean= employeeService.finByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ��ת�����Ա��ҳ���ִ�з���
	 */
	public String saveUI(){
		//��ѯ���в���
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * ����Ա����ִ�з���
	 */
	public String save(){
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	/**
	 * �༭Ա����ִ�з���
	 */
	public String edit(){
		
		//����Ա��ID��ѯԱ��
		employee = employeeService.findById(employee.getEid());
		//��ѯ���еĲ���
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	
	/**
	 * �޸�Ա����ִ�з���
	 */
	public String update(){
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	/**
	 * ɾ��Ա��ִ�з���
	 */
	public String delete(){
		//��ѯԱ��id
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
