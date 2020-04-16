import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MainClass {

    public static void main(String[] args) {

//        BiFunction<String,String,Boolean> function1 = (s, s2) -> s.equals(s2) && s.equals("sogol");
//
//        BiFunction<String,String,Boolean> function2 = new BiFunction<String, String, Boolean>() {
//            @Override
//            public Boolean apply(String s, String s2) {
//                return s.equals(s2) && s.equals("sogol");
//            }
//        };
//
//        validator(function1);
//        validator(function2);


//        SogolFunction<Integer,String> sogolFunction = new SogolFunction<Integer, String>() {
//            @Override
//            public String sadeghi() {
//                return null;
//            }
//
//            @Override
//            public void sogolNaremizanad(String nareh) {
//
//            }
//
//            @Override
//            public String doSomeThing(Integer x) {
//                return null;
//            }
//        };

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Sogol is " + s);
            }
        };
        consumer.accept("Khodshifteh");
    }
//    static void validator(BiFunction<String,String,Boolean> function) {
//        System.out.println(function.apply("sogol", "sogol"));
//    }
}
interface SogolFunction <T,U>{
    String sadeghi();
    void sogolNaremizanad(String nareh);
    U doSomeThing(T x);
}
