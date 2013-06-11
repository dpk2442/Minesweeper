package timer;

public class Timer {

	private long startTime;
	
	public Timer() {
		startTime = 0;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public long get() {
		long elapsed = System.currentTimeMillis() - startTime;
		return elapsed/1000;
	}
	
	public static void main(String args[]) {
		Timer time = new Timer();
		time.start();
		
		while (true) {
			long secs = time.get();

			double minutes = Math.floor(secs/60);
			double seconds = secs - minutes*60;

			String dispSeconds = (seconds < 10) ? "0" + (int)seconds: "" + (int)seconds;

			String dispString = (int)minutes + ":" + dispSeconds;

			try { Thread.sleep(500); } catch (InterruptedException e) {}
			System.out.println(dispString);
		}
	}
	
}
