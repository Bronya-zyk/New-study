package glimmer.T3;

import java.math.BigDecimal;

public class BankAccount {
    protected String accountNumber;//账号
    private String accountHolder;//户名
    private double balance;//余额
    private String password;//密码

    BankAccount(String accountNumber, String accountHolder, double initialBalance, String password) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance; 
        this.password = password;
    }

    public void deposit(double amount) {//存款
        if(validateAmount(amount) == false) {
            System.out.println("存款失败，金额不合法");
            return;
        }
        else {
            this.balance += amount;
            System.out.println("存款成功，存入金额为：" + balance);
        }
    }

    public boolean withdraw(double amount, String inputPassword) {
        if(validateAmount(amount) == false) {
            System.out.println("取款失败，金额不合法");
            return false;
        }
        else if(inputPassword.equals(password)) {
            if(amount <= balance) {
                balance -= amount;
                System.out.println("取款成功，取出金额为：" + amount);
                return true;
            } else {
                System.out.println("取款失败，余额不足");
                return false;
            }
        } else {
            System.out.println("取款失败，密码错误");
            return false;
        }
    }

    public boolean transfer(BankAccount recipient, double amount, String inputPassword) {
        if(validateAmount(amount) == false) {
            System.out.println("转账失败，金额不合法");
            return false;
        }
        else if(inputPassword.equals(password)) {
            if(amount <= balance) {
                balance -= amount;
                recipient.balance += amount;
                System.out.println("转账成功，转出金额为：" + amount);
                return true;
            } else {
                System.out.println("转账失败，余额不足");
                return false;
            }
        } else {
            System.out.println("转账失败，密码错误");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountInfo() {
        return "账号：" + accountNumber + ", 户名：" + accountHolder + ", 余额：" + balance;
    }

    // 只需修改可见性
    public boolean validatePassword(String inputPassword) {
        return inputPassword.equals(password);
    }

    // 只需修改可见性
    public boolean validateAmount(double amount) {
        if(amount > 0) {
            if(BigDecimal.valueOf(amount).scale()<=2) {
                return true;
            }
            else return false;
        }
        return false;
    }
}