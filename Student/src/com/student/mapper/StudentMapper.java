package com.student.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.student.bean.Student;

/**
 *
 * @author 让StudentMapper类通过继承的方式获取接口RowMapper＜Student＞里的方法
 */

public class StudentMapper implements RowMapper<Student> {

	@Override
	/**
	 * 通过获取的mapRow的方法，将bean包下的Student类，与数据中student数据表形成映射关系，从而能接收并存储从数据库中获得的数据；
	 */
	public Student mapRow(ResultSet resulSet, int num) throws SQLException {
		// 创建一个学生对象
		Student student = new Student();
		// 创建学生编号
		student.setId(resulSet.getInt("id"));
		// 创建学生姓名
		student.setName(resulSet.getString("name"));
		// 创建学生生日
		student.setBirthday(getStrDate(resulSet.getString("birthday")));
		// 创建学生年龄
		student.setAge(resulSet.getInt("age"));
		// 创建学生成绩
		student.setScore(resulSet.getInt("score"));
		// 创建学生班级编号
		student.setClassid(resulSet.getInt("classid"));
		// 创建学生住址
		student.setAddress(resulSet.getString("address"));

		return student;
	}

	/**
	 * 新建一个方法getStrDate，将数据中的Date型数据，转变为String型数据
	 *
	 * @param birthday
	 * @return
	 */
	private String getStrDate(String birthday) {
		// 创建一个java常用类SimpleDateFormat的对象fommter
		SimpleDateFormat fommter = new SimpleDateFormat("yyyy-mm-dd");
		// 创建一个Date型的对象dateString
		Date dateString = new Date();
		// 创建一个String型的变量strRtnDate，作为返回值
		String strRtnDate = "";

		try {
			dateString = fommter.parse(birthday);
			// 将获取的Date型数据dateString转换为“yyy-mm-dd”的格式，并赋值于strRtnDate
			strRtnDate = new SimpleDateFormat("yyy-mm-dd").format(dateString);

		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return strRtnDate;

	}

}
