package ru.geekbrain.behavioral.chainofresp;

public class Usage {

    public static void main(String[] args) {
        Account bank = new Bank(100);          // Bank with balance 100
        Account paypal = new Paypal(200);      // Paypal with balance 200
        Account bitcoin = new Bitcoin(300);    // Bitcoin with balance 300

        bank.setNext(paypal);
        paypal.setNext(bitcoin);

        try {
            bank.pay(50f);
            bank.pay(150f);
            bank.pay(250f);
            bank.pay(350f);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

    }
}
