package com.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.bean.Student;
import com.student.dao.StudentDao;

@Controller
public class StudentController {
	@RequestMapping(value = "/lin")
	public static String getStudentInfo(Model model) {
		// 通过ApplicationContext方法，链接applicationContext.xml文件，达到从控制层访问Dao层的功能
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 通过获取applicationContext.xml配置文件中bean标签里的ID名称，获得StudentDao文件
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		// 创建集合
		List<Student> studentList = new ArrayList<>();
		// 通过对象dao调取StudentDao里面的方法，把从数据库中查询到的数据赋值给集合studentList
		studentList = dao.queryAll();
		// 通过model.addAttribute的方法把得到的结果返回给前端JSP页面
		model.addAttribute("student", studentList);

		// 返回值后写的是返回到那个前端jsp页面来接收数据，并反馈給用户

		return "student";
	}

	@RequestMapping(value = "/queryById")
	public static String getStudentQueryById(String id, Model model) {
		// 通过ApplicationContext方法，链接applicationContext.xml文件，达到从控制层访问Dao层的功能
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 通过获取applicationContext.xml配置文件中bean标签里的ID名称，获得StudentDao文件
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		// 创建集合
		List<Student> studentList = new ArrayList<>();
		// 通过对象dao调取StudentDao里面的方法，把从数据库中查询到的数据赋值给集合studentList
		studentList = dao.queryById(id);
		// 通过model.addAttribute的方法把得到的结果返回给前端JSP页面
		model.addAttribute("student", studentList);

		// 返回值后写的是返回到那个前端jsp页面来接收数据，并反馈給用户

		return "student";
	}

	@RequestMapping(value = "/queryByName")
	public static String getStudentQueryByName(String name, Model model) {
		// 通过ApplicationContext方法，链接applicationContext.xml文件，达到从控制层访问Dao层的功能
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 通过获取applicationContext.xml配置文件中bean标签里的ID名称，获得StudentDao文件
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		// 创建集合
		List<Student> studentList = new ArrayList<>();
		// 通过对象dao调取StudentDao里面的方法，把从数据库中查询到的数据赋值给集合studentList
		studentList = dao.queryByName(name);
		// 通过model.addAttribute的方法把得到的结果返回给前端JSP页面
		model.addAttribute("student", studentList);

		// 返回值后写的是返回到那个前端jsp页面来接收数据，并反馈給用户

		return "student";
	}

	/**
	 * 学生追加
	 *
	 * @param name
	 * @param birthday
	 * @param age
	 * @param score
	 * @param classid
	 * @param model
	 * @return 返回值型： String
	 */

	@RequestMapping(value = "/add")
	public String AddStudent(String name, String birthday, String age, String score, String classid, Model model) {
		// フィルダー情報の配置
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 从ioc容器中获取dao
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		// 新規student object
		Student student = new Student();
		student.setName(name);
		student.setBirthday(birthday);
		student.setAge(Integer.valueOf(age));
		student.setScore(Integer.valueOf(score));
		student.setClassid(Integer.valueOf(classid));
		boolean result = dao.addStu(student);

		if (result) {
			// model.addAttribute("msg", "<script>alert('添加成功！')</script>");
			System.out.println("添加成功！");
		} else {
			model.addAttribute("msg", "<script>alert('添加失敗！')</script>");
		}
		model.addAttribute("student", dao.queryAll());
		return "student";
	}

	/**
	 * 通過id除学生
	 *
	 * @param id
	 * @param model
	 * @return 返回值型： String
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(String id, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		boolean result = dao.deleteStu(Integer.parseInt(id));
		if (result) {
			System.out.println("消除成功");
		} else {
			System.out.println("消除失敗");
		}
		model.addAttribute("student", dao.queryAll());
		return "student";
	}

	/**
	 *
	 * @param id
	 * @param name
	 * @param birthday
	 * @param age
	 * @param score
	 * @param classid
	 * @param model
	 * @return 返回值型： String
	 */
	@RequestMapping(value = "/update")
	public String updateStudent(String id, String name, String birthday, String age, String score, String classid,
			Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		Student student = new Student();
		student.setId(Integer.parseInt(id));
		student.setName(name);
		student.setBirthday(birthday);
		student.setAge(Integer.valueOf(age));
		student.setScore(Integer.valueOf(score));
		student.setClassid(Integer.valueOf(classid));
		boolean result = dao.updateStudent(student);
		if (result) {
			System.out.println("update成功");
		} else {
			System.out.println("update失敗");
		}
		model.addAttribute("student", dao.queryAll());
		return "student";
	}

	@RequestMapping(value = "/address")
	// 此处的addAddress中的变量，String id,String add01,与前台jsp页面获取信息框对应的name属性名要一致
	public String addAddress(String addr01, String id, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 从ioc容器中获取dao
		StudentDao dao = (StudentDao) context.getBean("studentdao");
		// Student student = new Student();
		// setId()中的变量，即将jsp中获得的数据导入set方法中，所以属性名称也要对应jsp中的名称
		// student.setId(Integer.valueOf(id));
		// student.setAddress(addr01);
		// 此处通过调用Dao里的addAddress方法后，把获取的值给返回请求result
		boolean result = dao.addAddress(addr01, id);
		if (result) {
			System.out.println("住所を追加しました");

		}else {
			System.out.println("住所を追加しませんでした");
		}
		model.addAttribute("student",dao.queryAll());
		return "student";

	}



}
