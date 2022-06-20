package server;

public class Program {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> System.out.println("Thread name = " +
                Thread.currentThread().getName()));

        Thread t2 = new Thread(()-> System.out.println("JVM Process ID = " +
                ProcessHandle.current().pid()));
    }
}
