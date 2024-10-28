public class ConsoleChart {

    public static void main(String[] args) {
        // Данни за броя на потоците и времената за изпълнение
        int[] threads = {2, 4, 8, 16, 32, 64};
        double[] lockTimes = {25, 28, 32, 56, 74, 105};
        double[] atomicTimes = {18, 20, 22, 28, 35, 42};

        System.out.println("Performance Chart (Locks vs Atomic Operations):");
        System.out.println("Threads | Locks Execution Time | Atomic Operations Execution Time");

        for (int i = 0; i < threads.length; i++) {
            // Извеждане на данни в конзолата
            System.out.printf("%-7d | %-20s | %-30s\n", threads[i],
                    generateBar(lockTimes[i]), generateBar(atomicTimes[i]));
        }
    }

    // Генерира текстова лента (бар) за всяка стойност на времето
    private static String generateBar(double time) {
        StringBuilder bar = new StringBuilder();
        int barLength = (int) time / 2; // Контролира дължината на бара
        for (int i = 0; i < barLength; i++) {
            bar.append("=");
        }
        return bar.toString();
    }
}
