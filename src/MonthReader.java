import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
public class MonthReader {

    ArrayList<MonthlyReportRecord> linesOfMonth;
    ArrayList<Integer> expenses = new ArrayList<>();
    ArrayList<Integer> profits = new ArrayList<>();
    HashMap<Integer, ArrayList<MonthlyReportRecord>> totalMonth = new HashMap<>();


    void readMonthReport() {
        File f = new File("./");
        System.out.println("cur dir: " + f.getAbsolutePath());
        for (int monthIndex = 1; monthIndex <= 3; monthIndex++) {
            String content = f.getAbsolutePath() + "/resources/m.20210" + monthIndex + ".csv";
            String[] lines = content.split("\n");
            linesOfMonth = new ArrayList<>();
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] parts = line.split(",");
                String itemName = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);
                MonthlyReportRecord record = new MonthlyReportRecord(itemName, isExpense, quantity, sumOfOne);
                linesOfMonth.add(record);
            }
            totalMonth.put(monthIndex, linesOfMonth);
        }
        calculateTotalExpense();
        calculateTotalProfit();
    }

    void getInfoMonth() {
        if (totalMonth.isEmpty()) {
            System.out.println("Месячные отчеты ещё считаны");
        } else {
            printMostExpenseAndMostProfiltItemName();
        }
    }

    void calculateTotalExpense() {
        for (int monthIndex : totalMonth.keySet()) {
            int totalExpense = 0;
            for (int i = 0; i < totalMonth.get(monthIndex).size(); i++) {
                if (totalMonth.get(monthIndex).get(i).isExpense) {
                    totalExpense += totalMonth.get(monthIndex).get(i).quantity * totalMonth.get(monthIndex).get(i).sumOfOne;
                }
            }
            expenses.add(totalExpense);
        }
    }

    void calculateTotalProfit() {
        for (int monthIndex : totalMonth.keySet()) {
            int totalProfit = 0;
            for (int i = 0; i < totalMonth.get(monthIndex).size(); i++) {
                if (totalMonth.get(monthIndex).get(i).isExpense) {
                    totalProfit += totalMonth.get(monthIndex).get(i).quantity * totalMonth.get(monthIndex).get(i).sumOfOne;
                }
            }
            profits.add(totalProfit);
        }
    }

    void printMostExpenseAndMostProfiltItemName() {
        for (int monthIndex : totalMonth.keySet()) {
            int mostProfitItem = 0;
            String mostProfitItemName = "";
            int mostExpenseItem = 0;
            String mostExpenseItemName = "";
            for (int i = 0; i < totalMonth.get(monthIndex).size(); i++) {
                if (!totalMonth.get(monthIndex).get(i).isExpense) {
                    int itemProfit = totalMonth.get(monthIndex).get(i).quantity * totalMonth.get(monthIndex).get(i).sumOfOne;
                    if (itemProfit > mostProfitItem) {
                        mostProfitItem = totalMonth.get(monthIndex).get(i).quantity * totalMonth.get(monthIndex).get(i).sumOfOne;
                        mostProfitItemName = totalMonth.get(monthIndex).get(i).itemName;
                    } else if ((totalMonth.get(monthIndex).get(i).isExpense) && ((totalMonth.get(monthIndex).get(i).quantity) * (totalMonth.get(monthIndex).get(i).sumOfOne)) > mostExpenseItem) {
                        mostExpenseItem = totalMonth.get(monthIndex).get(i).quantity * totalMonth.get(monthIndex).get(i).sumOfOne;
                        mostExpenseItemName = totalMonth.get(monthIndex).get(i).itemName;
                    }
                }
                System.out.println("Месяц " + monthIndex);
                System.out.println("Самая доходная позиция - " + mostProfitItemName + " Принесла общий доход в размере - " + mostProfitItem);
                System.out.println("Самая затратная позиция - " + mostExpenseItemName + " Общие затраты в размере - " + mostExpenseItem);
            }
        }
    }

    int getExpense(int month) {
        return expenses.get(month);
    }

    int getProfit(int month) {

        return profits.get(month);
    }
}

