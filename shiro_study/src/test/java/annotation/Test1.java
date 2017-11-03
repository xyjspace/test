package annotation;


/**
 * Created by banma on 2017/10/27.
 */
public class Test1 {
    public static void main(String[] args) {

    }

    public void execte(){
        System.out.println("hahahah");
    }

    @Test void testExecute(){
        execte();
    }
}

