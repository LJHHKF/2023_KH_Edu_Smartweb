package º¹½À;

import java.util.ArrayList;

public class SelectExam {
	public void printSelectAll(TeamDAO dao_team) throws Exception{
		ArrayList<TeamDTO> list_dto = dao_team.selectAll();
		System.out.println("id \t name \t age \t contact \t birthday");
		for(TeamDTO dto : list_dto) {
			System.out.println(dto.getId() +"\t"+dto.getName()+"\t"+dto.getContact()+"\t"+dto.getFormedDate());
		}
	}
}
