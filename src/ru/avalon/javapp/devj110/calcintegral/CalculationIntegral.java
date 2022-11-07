package ru.avalon.javapp.devj110.calcintegral;

public class CalculationIntegral {
    public static final int INTERVAL = 100;

    public static double integrate(Function function, double a, double b) {
        double h = (b - a) / INTERVAL;
        double res = 0;
        for(int i = 1; i < INTERVAL; i++) {
            double x2 = a + i * h;
            res += function.f(x2) * h;
        }
        return res;
    }

    public static void main(String[] args) {
        Function f1 = new Logarithm();
        System.out.println(integrate(f1, 2, 5));

        // Анонимный класс, реализующий интерфейс Function
        Function f2 = new Function() {
            @Override
            public double f(double x) {
                return Math.pow(x, 2) + x;
            }
        };
        System.out.println(integrate(f2,3,4));

        // ссылка на статический метод
        Function f3 = CalculationIntegral::exp;
        System.out.println(integrate(f3, 0.01, 2));

        // ссылка на метод экземпляра
        Squaring sq = new Squaring();
        Function f4 = sq::pow;
        System.out.println(integrate(f4,1,3));

        // лямбда выражение
        Function f5 = x -> x * Math.sin(x);
        System.out.println(integrate(f5, 0, 1));
    }

    // вложенный класс, реализующий интерфейс Function
    private static class Logarithm implements Function {
        @Override
        public double f(double x) {
            return Math.log(x);
        }
    }

    private static double exp(double x) {
            return Math.exp(-x);
        }

    private static class Squaring {
        double pow(double x) {
            return Math.pow(x, 2);
        }
    }
}
