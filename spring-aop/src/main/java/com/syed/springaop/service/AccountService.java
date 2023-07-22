package com.syed.springaop.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {

    public void addAccount() {
        System.out.println("adding account..");
    }

    public List<String> getAllAccounts() {
        System.out.println("getting all account..");
        List<String> list = new ArrayList<>();
        list.add("a");
        return list;
    }

    public void simulateException() {
        throw new RuntimeException("stimulated a exception");
    }

    public void timeMethod() throws InterruptedException {
        System.out.println("timed method..");
        TimeUnit.SECONDS.sleep(3);
    }

    public void getAccount() {
        System.out.println("getting account..");
    }

    public void setAccount() {
        System.out.println("setting account..");
    }

}
