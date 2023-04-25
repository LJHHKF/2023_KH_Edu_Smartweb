package dao;

import java.sql.PreparedStatement;

public class BoardDAO {
	public int insertBoard(Board board) {
		int result = 0;
		//BoardNo, title, contente, date(sysdate), readcount(0), member_no
		String query = "insert into board values(seq_bno.nextval, ?, ?,default, default, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		}
	}
}
