import java.security.MessageDigest;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

class BreakThread implements Runnable{
	
	private Thread t;
	private int length;
	private String hash; 
	private int startNum;
	private int endNum;
	BreakThread(String hash, int length,int startNum, int endNum){
		this.length = length;
		this.hash = hash;
		this.startNum = (int)Math.pow(10, startNum);
		this.endNum = (int)Math.pow(10, endNum);
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this);
			t.start();
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		
		for(int i=this.startNum;i<this.endNum;i++){
			String password = String.format("%04d", i);
			md.reset();
				md.update(password.getBytes());
			
				byte[] digest = md.digest();
			String myHash = toHexString(digest);
			//ystem.out.println(password+" "+this.hash+" "+myHash);
			if(myHash.equals(this.hash))System.out.println(password);;
			}
		
		} catch (Exception e) { System.out.println("Can't find MD5"); }

	}
	
	public static String toHexString(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}


public class BreakMd5MultiThreadding {
	public static void main(String [] args){
		// 4 digit numeric password
		//  echo -n "7723" | md5sum
    		// System.out.println(breakMD5("da3fde159d754a2555eaa198d2d105b2",4));
    		// System.out.println(breakMD5("fa246d0262c3925617b0c72bb20eeb1d",4));
    		// System.out.println(breakMD5("fa246d0262c3925617Z0c72bb20eeb1d",4));
		// echo -n '12345678' | md5sum
		//System.out.println(breakMD5("25d55ad283aa400af464c76d713c07ad",8));
		new BreakThread("da3fde159d754a2555eaa198d2d105b2",5, 0, 5).start();;
	}
	
}
