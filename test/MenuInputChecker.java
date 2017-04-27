package com.company;

import java.util.Scanner;

public class MenuInputChecker {
    public void tester(){
    String input = "nothing";

    while(!input.matches("[0-9]+"))
    {
        System.out.print("Please Enter your Choice (Numbers Only):  ");
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

    }
}
}
