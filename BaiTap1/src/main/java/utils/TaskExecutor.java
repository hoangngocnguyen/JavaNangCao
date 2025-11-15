package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Lớp quản lý tác vụ ngầm (gửi email,
public class TaskExecutor {
	// Khởi tạo một Thread Pool với số lượng thread cố định
	private static final ExecutorService mailExecutor = Executors.newFixedThreadPool(5);
	
	/**
     * Chấp nhận một tác vụ (Runnable) và gửi nó vào hàng đợi của Thread Pool.
     * @param task Tác vụ cần thực hiện bất đồng bộ (ví dụ: hàm gửi email).
     */
	public static void submitMailTask(Runnable task) {
		mailExecutor.submit(task);
	}
	
	/**
     * Hàm dùng để đóng Thread Pool khi ứng dụng tắt (Rất quan trọng).
     * Bạn nên gọi hàm này trong sự kiện ContextListener khi ứng dụng web shutdown.
     */
    public static void shutdown() {
        mailExecutor.shutdown();
    }
}	
