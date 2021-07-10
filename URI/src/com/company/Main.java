package com.company;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        try {
            MyURI uri = new MyURI("foo://username:password@www.example.com:8000/he50llo/index.html?arg=val&arg2=nval2#fragment");
            System.out.println(uri.getPath());
        } catch (URISyntaxException e) {
            System.out.println(e.getIndex());
        }
    }
}
