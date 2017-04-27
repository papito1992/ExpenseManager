package com.company;

import java.io.IOException;
import java.util.*;

public class PEMService {
    Repository repo = Repository.getRepository();
    ReportService reportService = new ReportService();
    private Scanner in = new Scanner(System.in);
    private int choice;


    public void showMenu() {

        while (true) {

            printMenu();

            switch (choice) {
                case 1:
                    addCatergory();
                    pressAnyKeyToContinue();
                    break;
                case 2:
                    categoryList();
                    pressAnyKeyToContinue();
                    break;
                case 3:
                    expenseEntry();
                    pressAnyKeyToContinue();
                    break;
                case 4:
                    expenseList();
                    pressAnyKeyToContinue();
                    break;
                case 5:

                    monthlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 6:
                    yearlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 7:
                    expenseCategoryList();
                    pressAnyKeyToContinue();
                    break;
                case 8:

                    exit();
                    break;
            }
        }
    }

    private void exit() {
        System.exit(8);
    }

    String getCategoryNameById(Long categoryId) {
        for (Category c :
                repo.catList) {
            if (c.getCategoryId().equals(categoryId)) {
                return c.getName();
            }
        }
        return null; //invalid cat id present
    }

    private void expenseCategoryList() {
        System.out.println("Categorized expense list");
    }

    private void yearlyExpenseList() {
        System.out.println("yearly expenses");
    }

    private void monthlyExpenseList() {
        System.out.println("Total Monthly expenses");
        Map<String, Float> resultMap = reportService.calculateMonthlyTotal();
        Set<String> keys = resultMap.keySet();
        for (String yearMonth : keys) {
            //2016.01
            String[] arr = yearMonth.split("/");
            String year = arr[0];
            Integer monthNo = new Integer(arr[1]);
            String monthName = DataUtil.getMonthName(monthNo);

            System.out.println(year + ", " + monthName + " : " + resultMap.get(yearMonth));

        }
    }

    private void expenseList() {
        System.out.println("Expense listing");
        List<Expense> expenseList = repo.expList;
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.get(i);
            String catName = getCategoryNameById(expense.getCategoryId());
            String dateAsString = DataUtil.dateToString(expense.getDate());
            System.out.println((i + 1) + ". " + catName + ", " + expense.getAmount() + ", " + expense.getRemark() + ", " + dateAsString);

        }
    }

    private void expenseEntry() {
        System.out.println("Enter Details for expense entry");
        categoryList();
        System.out.println("Choose Category");
        int catChoice = in.nextInt();
        System.out.println("your category choice");
        Category selectedCat = repo.catList.get(catChoice - 1);
        System.out.println("Enter Amount: ");
        float amount = in.nextFloat();
        System.out.println("Enter Remark : ");
        in.nextLine();
        String remark = in.nextLine();
        System.out.print("Enter Date (DD/MM/YYYY): ");
        String dateAsString = in.nextLine();
        Date date = DataUtil.stringToDate(dateAsString);


        // add expense detail in expense object
        Expense exp = new Expense();
        exp.setCategoryId(selectedCat.getCategoryId());
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);
        //store expense object in repository
        repo.expList.add(exp);
        System.out.println("Success: Expense Added");


    }

    public void printMenu() {
        System.out.println("============PEM MENU============");
        System.out.println("1. Add Category");
        System.out.println("2. Category list");
        System.out.println("3. Expense Entry");
        System.out.println("4. Expense list");
        System.out.println("5. Monthly expense list");
        System.out.println("6. Yearly expense list");
        System.out.println("7. Categorized expense list");
        System.out.println("8. Exit");
        System.out.println("================================");

        do {
            System.out.print("Choose your action:  ");
            while (!in.hasNextInt()) {
                System.out.println("That's not a number!");
                in.next(); // this is important!
            }
            choice = in.nextInt();
        } while (choice <= 0);


    }

    public void addCatergory() {
        in.nextLine(); // new line car is read here which is already present in stream and its not in use for now
        System.out.print("Enter category name:  ");
        String catName = in.nextLine();
        Category cat = new Category(catName);
        repo.catList.add(cat);
        System.out.println("success category added");
    }

    public void categoryList() {
        System.out.println("Category List");
        List<Category> clist = repo.catList;
        for (int i = 0; i < clist.size(); i++) {
            Category c = clist.get(i);
            System.out.println((i + 1) + ". " + c.getName() + ", " + c.getCategoryId());
        }
    }

    public void pressAnyKeyToContinue() {

        try {
            System.out.println("Press any key to continue");
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
