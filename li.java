import java.util.Scanner;
import java.util.Random;
import point.*;
import java.util.*;

public class Li {
    public static void main(String[] args) {
        Queue<point> q = new LinkedList<point>();
        int [][] Matrix = new int [10][10];  // Создание матрицы, можно реализовать ввод размера с консоли
        Scanner Sc = new Scanner(System.in);
        System.out.println("Задайте координату Х для входа в матрицу: ");   // Создание точки старта
        int enterX = Sc.nextInt();
        System.out.println("Задайте координату Y для входа в матрицу: ");
        int enterY = Sc.nextInt();
        System.out.println("Задайте координату Х для выхода из матрицы: ");  // Создание точки выхода
        int exitX = Sc.nextInt();
        System.out.println("Задайте координату Y для выхода из матрицы: ");
        int exitY = Sc.nextInt();
        System.out.println("Введите колличество клеток-препятствий для матрицы: "); // Создение стен в матрице 
        int wallAmount = Sc.nextInt();
        for( int C =0; C<wallAmount; C++){
            System.out.print("Задайте координату Х для стены:  ");
            int wallX = Sc.nextInt();
            System.out.print("Задайте координату Y для стены:  ");
            int wallY = Sc.nextInt();
            Matrix[wallY][wallX] = -1;  // "-1" обозначение для непроходимой области
        }
        point buff = new point(enterX,enterY); // Переменная для "Запоминания" координат
        Matrix[buff.getX()][buff.getY()] = 1;  // Пометка первого шага
        q.add(buff); // добавление в очередь точки начала
        while(Matrix[exitX][exitY] == 0){   // пока точка выхода не помечена
            buff = q.poll(); // забор из очереди  клетки для поиска последущих
            int y = buff.getY(); // координата Y
            int x = buff.getX(); // Координата X
            if((x-1)>=0 && Matrix[x-1][y] == 0){  // Пометка клетки сверху, если она еще не помечена//
                    Matrix[x-1][y]  = Matrix[x][y]+1; // пометка шагов в матрице
                    buff = new point(x-1,y); // Формирование новых позиций 
                    q.add(buff); // Добавление отмеченных точек в очередь
                }
            if((y+1)<=Matrix.length-1 && Matrix[x][y+1] == 0 ){  //Пометка клетки справа, если она еще не помечена
                    Matrix[x][y+1] = Matrix[x][y]+1; // пометка шагов в матрице
                    buff = new point(x,y+1); // Формирование новых позиций 
                    q.add(buff); // Добавление отмеченных точек в очередь
                }
            if((x+1)<=Matrix[0].length-1 && Matrix[x+1][y] == 0){  //Пометка клетки снизу, если она еще не помечена
                    Matrix[x+1][y]  = Matrix[x][y]+1; // пометка шагов в матрице
                    buff = new point(x+1,y); // Формирование новых позиций 
                    q.add(buff); // Добавление отмеченных точек в очередь
                }
            if((y-1)>=0 && Matrix[x][y-1] == 0){  //Пометка клетки слева, если она еще не помечена
                    Matrix[x][y-1]  = Matrix[x][y]+1; // пометка шагов в матрице
                    buff = new point(x,y-1); // Формирование новых позиций 
                    q.add(buff); // Добавление отмеченных точек в очередь
                } 
            }        
            for (int k = 0; k < Matrix.length; k++) {         // печать помеченной матрицы
                for (int j = 0; j < Matrix[k].length; j++) {
                    System.out.print(Matrix[k][j] + "\t");
                    }
                System.out.println();
                }        
        List<point> Path = new ArrayList<point>();  // создание списка для внесения построенного маршрута
        buff = new point(exitX, exitY); // Перенос точки в точку выхода
        Path.add(buff); // Добавление в маршрут начальной точки от выхода
        boolean isExists = false; // Переменная для пометки готовности пути 
        while(!isExists){  // Пока путь не готов
            buff = Path.get(Path.size()-1); // Забор точки для анализа
            int y = buff.getY(); // Переменная Y
            int x = buff.getX(); // Переменная Х
            if(y == enterY && x == enterX){ // Если последние точки в построенном маршруте совпадают с точками входа
                isExists = true; // отметка для завершения цикла
            } else { // Иначе проверка клеток по приоритету Вверх -> Вправо -> Вниз -> Влево
                if((x-1)>=0 && Matrix[x-1][y] == (Matrix[x][y]-1) ){  //Вверх             
                        buff = new point(x-1,y);
                        Path.add(buff);             
                } else if((y+1)<=Matrix.length-1 && Matrix[x][y+1] == (Matrix[x][y]-1)){  //Вправо
                        buff = new point(x,y+1);
                        Path.add(buff);
                } else if((x+1)<=Matrix[0].length-1 && Matrix[x+1][y] == (Matrix[x][y]-1)){  //Вниз
                        buff = new point(x+1,y);
                        Path.add(buff);
                } else if((y-1)>=0 && Matrix[x][y-1] == (Matrix[x][y]-1)){  //Влево
                        buff = new point(x,y-1);
                        Path.add(buff);            
                }
            }  
        }
        System.out.println(Path); // Более явная пометка проложенного маршрута 
        for( int H = 0; H<Path.size();H++){// Более явная пометка проложенного маршрута 
            buff = Path.get(H);
            Matrix[buff.getX()][buff.getY()] = 111111; // Пометка ячейки, по которой идет путь
        }
        for (int k = 0; k < Matrix.length; k++) {         // печать помеченной матрицы
            for (int j = 0; j < Matrix[k].length; j++) {
                System.out.print(Matrix[k][j] + "\t");
                }
            System.out.println();
        }        
    }
}