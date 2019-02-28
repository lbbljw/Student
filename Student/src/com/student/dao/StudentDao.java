package com.student.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.student.bean.Student;
import com.student.mapper.StudentMapper;

public class StudentDao {

	/**
	 * JdbcTemplate Java數據庫連接
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            セットする jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Student> queryAll() {

		// 编写数据库查询语句
		String sql = "select id,name,birthday,age,score,classid,address from student";
		// 创建集合
		List<Student> studentList = new ArrayList<>();
		// 通过jdbcTemplate方法把查询到的数据库储存到集合studentList中
		studentList = jdbcTemplate.query(sql, new StudentMapper());
		return studentList;

	}

	/**
	 * 通过id查询学生
	 *
	 * @param id
	 * @return
	 */
	public List<Student> queryById(String id) {
		String sql = "select id,name,birthday,age,score,classid,address from student where id like  " + id + " ";
		List<Student> studentList = new ArrayList<Student>();
		studentList = (ArrayList<Student>) jdbcTemplate.query(sql, new StudentMapper());
		return studentList;
	}

	/**
	 * 通過姓名查找
	 *
	 * @param name
	 * @return List<Student>
	 */

	public List<Student> queryByName(String name) {

		String sql = "select id,name,birthday,age,score,classid,address from student where name like '%" + name + "%'";

		List<Student> studentList = new ArrayList<Student>();

		studentList = jdbcTemplate.query(sql, new StudentMapper());

		return studentList;

	}

	/**
	 * 添加学生
	 *
	 * @param student
	 * @return 返回??型： boolean
	 */
	public boolean addStu(Student student) {
		String sql = "insert into student(id,name,birthday,age,score,classid,address) values(0,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				new Object[] { student.getName(), student.getBirthday(), student.getAge(), student.getScore(),
						student.getClassid(), student.getAddress() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DOUBLE, Types.INTEGER, Types.VARCHAR }) == 1;
	}

	/**
	 * ?除学生
	 *
	 * @param id
	 * @return 返回??型： boolean
	 */
	public boolean deleteStu(Integer id) {
		String sql = "delete from student where id = ?";
		return jdbcTemplate.update(sql, id) == 1;
	}

	/**
	 * 更新学生信息
	 *
	 * @param student
	 * @return 返回??型： boolean
	 */
	public boolean updateStudent(Student student) {
		String sql = "update student set name=?,birthday=?,age=?,score =?,classid =?,address =?  where id =?";
		Object stuObj[] = new Object[] { student.getName(), student.getBirthday(), student.getAge(), student.getScore(),
				student.getClassid(), student.getAddress(), student.getId() };
		return jdbcTemplate.update(sql, stuObj) == 1;
	}

	public boolean addAddress(String addr01, String id) {
		String sql = "update student set address=? where id =?";
		return jdbcTemplate.update(sql, addr01, id) == 1;
	}

}
