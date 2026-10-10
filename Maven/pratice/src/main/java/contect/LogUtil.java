package contect;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    private static final String LOG_DIR = "log";

    static {
        // 确保 log 文件夹存在
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 根据端口号和时间生成日志文件名
     */
    public static String generateLogFileName(int port) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return LOG_DIR + File.separator + sdf.format(new Date()) + "_port_" + port + ".log";
    }

    /**
     * 追加写入日志
     */
    public static synchronized void writeLog(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println("[" + sdf.format(new Date()) + "] " + content);
        } catch (IOException e) {
            System.err.println("写入日志失败: " + e.getMessage());
        }
    }
}