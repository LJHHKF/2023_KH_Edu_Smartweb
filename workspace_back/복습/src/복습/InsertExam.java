package 복습;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class InsertExam{
	public int insertsContatacts(TeamDAO dao_team) throws Exception {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		
		String name = "이주화";
		int age = 28;
		String contact = "01041228264";
		Timestamp birthday = new Timestamp(sdf.parse("19950331").getTime());
		TeamDTO dto = new TeamDTO(0, name, age, contact, birthday);
		result += dao_team.insert(dto);
		
		name = "홍성준";
		age = 27;
		contact = "01048475825";
		birthday = new Timestamp(sdf.parse("19961015").getTime());
		dto = new TeamDTO(0, name, age, contact, birthday);
		result += dao_team.insert(dto);
		
		name = "김성하";
		age = 23;
		contact = "01062079268";
		birthday = new Timestamp(sdf.parse("20000607").getTime());
		dto = new TeamDTO(0, name, age, contact, birthday );
		result += dao_team.insert(dto);
		
		name = "지세영";
		age = 29;
		contact = "01071503677";
		birthday = new Timestamp(sdf.parse("19940325").getTime());
		dto = new TeamDTO(0, name, age, contact,birthday);
		result += dao_team.insert(dto);
		
		name = "이건희";
		age = 33;
		contact = "01024849975";
		birthday = new Timestamp(sdf.parse("19900101").getTime());
		dto = new TeamDTO(0, name, age, contact, birthday);
		result += dao_team.insert(dto);
		
		name = "이상우";
		age = 26;
		contact = "01021706624";
		birthday = new Timestamp(sdf.parse("19970408").getTime());
		dto = new TeamDTO(0, name, age, contact, birthday);
		result += dao_team.insert(dto);
		return result;
	}
}
