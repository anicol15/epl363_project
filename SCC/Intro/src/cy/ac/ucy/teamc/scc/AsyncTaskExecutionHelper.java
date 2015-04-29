package cy.ac.ucy.teamc.scc;

import java.util.concurrent.Executor;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

public class AsyncTaskExecutionHelper {
	
	@SuppressLint("NewApi")
	static class HoneycombExecutionHelper {
		@SafeVarargs
		public static <P> void execute(AsyncTask<P, ?, ?> asyncTask,
				boolean parallel, P... params) {
			Executor executor = parallel ? AsyncTask.THREAD_POOL_EXECUTOR
					: AsyncTask.SERIAL_EXECUTOR;
			asyncTask.executeOnExecutor(executor, params);
		}
	}

	public static <P> void executeParallel(AsyncTask<P, ?, ?> asyncTask,
			P... params) {
		execute(asyncTask, true, params);
	}

	public static <P> void executeSerial(AsyncTask<P, ?, ?> asyncTask,
			P... params) {
		execute(asyncTask, false, params);
	}

	private static <P> void execute(AsyncTask<P, ?, ?> asyncTask,
			boolean parallel, P... params) {
		if (Build.VERSION.SDK_INT >= 11) {
			HoneycombExecutionHelper.execute(asyncTask, parallel, params);
		} else {
			asyncTask.execute(params);
		}
	}
}