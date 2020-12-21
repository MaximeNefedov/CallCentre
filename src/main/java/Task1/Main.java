package Task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCentre centre = new CallCentre(10);
        Thread generator = new Thread(centre::generateCall);

        Thread operator1 = new Thread(centre::handleCall, "Оператор 1");
        Thread operator2 = new Thread(centre::handleCall, "Оператор 2");
        Thread operator3 = new Thread(centre::handleCall, "Оператор 3");

        generator.start();
        operator1.start();
        operator2.start();
        operator3.start();

        generator.join();
        operator1.join();
        operator2.join();
        operator3.join();
    }
}