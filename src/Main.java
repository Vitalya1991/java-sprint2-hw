
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        // Поехали!
    Scanner scanner = new Scanner(System.in);
    MonthReader monthData =new MonthReader();
    YearReader yearData = new YearReader();
    while (true){
    printMenu();
    String userInput = scanner.next();
    if(userInput.equals("1")){
    monthData.readMonthReport();
    System.out.println("Месячные отчеты считаны");
    }else if(userInput.equals("2")) {
        yearData.readYearReport();
        System.out.println("Годовой отчет считан");
    } else if(userInput.equals("3")){
        if((monthData.totalMonth.isEmpty()) || (yearData.linesOfYear.isEmpty())){
            System.out.println("Отчеты не считаны");
        } else {
            for(int i = 0; i < 3; i++){
                if (monthData.getExpense(i) == yearData.getExpense(i)) {
                    System.out.println("Сверка по расходам за " + (i + 1) + " месяцев верна");
                } else{
                    System.out.println("Сверка по расходам за " + (i + 1) + " месяцев Не верна");
                }
                if (monthData.getProfit(i) == yearData.getProfit(i)) {
                    System.out.println("Сверка доходов за " + (i + 1) + " месяцев верна");
                }else{
                    System.out.println("Сверка доходов за " + (i + 1) + " месяцев Не верна");
                }
            }
        }
        }else if (userInput.equals("4")){
        monthData.getInfoMonth();
    }else if (userInput.equals("5")){
        yearData.getInfoYear();
        }else if (userInput.equals("0")){
            System.out.println("Выход");
            break;
        }else {
            System.out.println("Извените, такой команды пока нет.");
        }
    }
    }
    public static void printMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 -Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0- - Выход");
    }
}


