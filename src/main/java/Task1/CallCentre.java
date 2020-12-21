package Task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CallCentre {
    private int counter;
    private static final int DELAY = 1000;
    private static final int WORKING_PROCESS = 3000;

//    Я принял решение использовать реализацию ArrayBlockingQueue из-за возможности соблюдения честной блокировки
//    Данный колл-центр работает по принципу многих серверов, которые предустанавливают лимит на
//    максимальное количество пользователей, которые могут находиться в режиме ожидания

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public CallCentre(int counter) {
        this.counter = counter;
    }

    public void handleCall() {
        while (counter != 0 || !queue.isEmpty()) {
            try {
                String person = queue.take();
                System.out.printf("%s начал обработку звонка %s\n", Thread.currentThread().getName(), person);
                Thread.sleep(WORKING_PROCESS);
                System.out.printf("%s закончил обработку звонка %s\n", Thread.currentThread().getName(), person);
            } catch (InterruptedException e) {
                System.out.printf("%s ушел домой\n", Thread.currentThread().getName());
            }
        }
    }

    public void generateCall() {
        while (counter != 0) {
            try {
                Thread.sleep(DELAY);
                String personName = "Абонент " + counter;
                System.out.println(personName + " совершает звонок");
                queue.put(personName);
                counter--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

