package ����;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class InsertExam {
	public int insertTeamContacts(TeamDAO dao) throws Exception{
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		String name = "����ȭ";
		int age = 28;
		String contact = "01041228264";
		TeamDTO dto = new TeamDTO(0, name, age, contact, new Timestamp());
		result += dao.insert(dto);
		
		name = "ȫ����";
		age = 27;
		contact = "01048475825";
		dto = new TeamDTO(0, name, age, contact, null);
		result += dao.insert(dto);
		
		name = "�輺��";
		age = 23;
		contact = "01062079268";
		dto = new TeamDTO(0, name, age, contact, null);
		result += dao.insert(dto);
		
		name = "������";
		age = 29;
		contact = "01071503677";
		dto = new TeamDTO(0, name, age, contact, null);
		result += dao.insert(dto);
		
		name = "�̰���";
		age = 33;
		contact = "01024849975";
		dto = new TeamDTO(0, name, age, contact, null);
		result += dao.insert(dto);
		
		name = "�̻��";
		age = 26;
		contact = "01021706624";
		dto = new TeamDTO(0, name, age, contact, null);
		result += dao.insert(dto);
		return result;
	}
}
