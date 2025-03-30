package runners;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTests {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        LoginTest test1 = new LoginTest();
        StockCardManagementTest test2 = new StockCardManagementTest();
        InvoiceManagementTest test3 = new InvoiceManagementTest();

        // Her testi ayrı bir thread üzerinde çalıştır
        executor.submit(() -> {
            try {
                test1.runTest();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executor.submit(() -> {
            try {
                test2.runTest();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executor.submit(() -> test3.runTest());

        // ExecutorService'i kapat
        executor.shutdown();
    }
}
