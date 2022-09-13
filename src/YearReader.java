
import java.io.File;
import java.util.ArrayList;
public class YearReader {
    ArrayList<YearlyReportRecord> linesOfYear = new ArrayList<>();

    ArrayList<Integer> yearExpense = new ArrayList<>();
    ArrayList<Integer> yearProfit = new ArrayList<>();
    void readYearReport(){
        File f = new File("./");
        String content = f.getAbsolutePath() + "/resources/y.2021.csv";
        String[] lines = content.split("\n");
        for(int i = 1; i < lines.length; i++){
            String line = lines[i];
            String[] parts = line.split(",");
                    int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyReportRecord record = new YearlyReportRecord(month, amount, isExpense);
            linesOfYear.add(record);
        }
        checkExpenseOrProfit();
    }
    void getInfoYear() {
        if (linesOfYear.isEmpty()){
            System.out.println("Годовой отчет еще не считан");
        }else{
            System.out.println("Отчет за 2021 год");
            yearStat();
        }
    }
    void checkExpenseOrProfit(){
        for (YearlyReportRecord yearlyReportRecord : linesOfYear) {
            if (!yearlyReportRecord.isExpense) {
                yearProfit.add(yearlyReportRecord.amount);
            } else {
                yearExpense.add(yearlyReportRecord.amount);
            }
        }
}
void yearStat(){
    int clearProfit = 0;
    int totalProfit = 0;
    int totalExpense = 0;
    for (int month = 0; month < yearExpense.size(); month++) {
        clearProfit = yearProfit.get(month) - yearExpense.get(month);
        totalProfit = totalProfit + yearProfit.get(month);
        totalExpense = totalExpense + yearExpense.get(month);
        System.out.println("Чистая прибыль за " + (month +1) + " месяц составила - " + clearProfit);
}
    System.out.println("Средний расход - " + totalExpense/12);
    System.out.println("Средний доход - " + totalProfit/12);
}
    int getExpense ( int month){
        return yearExpense.get(month);
    }
    int getProfit ( int month){

        return yearProfit.get(month);
    }

}

