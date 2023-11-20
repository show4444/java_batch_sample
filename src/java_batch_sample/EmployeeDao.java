package java_batch_sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDao {

	private String url;
    private String user;
    private String pass;
    private static Connection con;

    public EmployeeDao() {
        // 接続先のURL
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
        // ユーザー名
        //（別のユーザー名に変更している場合はその該当ユーザー名を設定）
        this.user = "MODEL_LESSON";
        // パスワード
        this.pass = "oracle";

        try {
            // 未接続の場合のみ処理を実施します
            if(con == null) {
                // データベースの接続を試みます
                con = DriverManager.getConnection(this.url, this.user, this.pass);
                // オートコミットを設定します
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String name, String department) {
        // INSERT文を生成します
        String sql = "INSERT INTO EMPLOYEES (ID, NAME, DEPARTMENT) VALUES (EMPLOYEE_ID_SEQ.nextval,?,?)";

        // SQL文を準備します
        try(PreparedStatement stmt = con.prepareStatement(sql);) {
            // SQL文に引数をセットします
            stmt.setString(1, name); // nameカラム
            stmt.setString(2, department); // departmentカラム
            // SQL文を実行します
            stmt.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
