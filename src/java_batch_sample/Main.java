package java_batch_sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String [] args) {
		try {
			System.out.print("バッチ処理を実行します");
			// DAOクラスのインスタンスを生成します
            EmployeeDao employeeDao = new EmployeeDao();

            // CSVファイルを読み込みます
            File f = new File("C:\\Users\\bb15118079\\Documents\\java_batch_sample\\resources\\sample.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));

            // ファイル内の１行のテキストを一時保存する変数
            String line;
            // データ挿入件数
            int count = 0;

            // ファイルを１行ずつ読み込みます
            while ((line = br.readLine()) != null) {
                // 読み込んだ行をカンマ区切りで分割し、配列に変換します
                String [] row = line.split(",");
                // DAOクラスのINSERT用メソッドを呼び出し、読み込んだテキストを引数として渡します
                employeeDao.insert(row[0], row[1]);
                // 挿入件数のインクリメント
                count++;
            }
            // ファイル読み込みの終了
            br.close();
            // データ挿入件数の出力
            System.out.println("\n" + count + "件のデータ保存に成功しました");
        } catch(FileNotFoundException e) {
            System.out.println("ファイルがありません");
        } catch(IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
