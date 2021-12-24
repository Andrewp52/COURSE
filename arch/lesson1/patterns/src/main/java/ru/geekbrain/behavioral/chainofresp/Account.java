package ru.geekbrain.behavioral.chainofresp;

public abstract class Account {
    protected Account successor;
    protected float balance;

    public void setNext(Account account)
    {
        this.successor = account;
    }

    public void pay(float amountToPay) throws Exception {
        if (this.canPay(amountToPay)) {
            System.out.printf("Paid %s using %s\r\n", amountToPay, this.getClass().getSimpleName());
        } else if (this.successor != null) {
            System.out.printf("Cannot pay using %s. Proceeding ..", this.getClass().getSimpleName());
            this.successor.pay(amountToPay);
        } else {
            throw new Exception("None of the accounts have enough balance\r\n");
        }
    }

    public boolean canPay(float amount) {
        return this.balance >= amount;
    }
}
