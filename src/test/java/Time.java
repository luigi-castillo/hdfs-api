import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Time {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
	public static void main(String[] args) {
		
		System.out.println("snapshot_" + sdf.format(new Date()));
	}
}
