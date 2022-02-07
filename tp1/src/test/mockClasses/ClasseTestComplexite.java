package mockClasses;

public class ClasseTestComplexite {

    public ClasseTestComplexite(int i) {
        //compter if, while, do...while, switch (nb of cases/default), for, foreach, catch

        for (int j = 0; j <= i; j++)
            doSomething(i);

        try{
            switch(i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    doSomethingElse();
            }
        }
        catch (Exception e)
        {

        }
    }

    private void doSomething(int i) {

        if (i >= 5) {
            doSomethingElse();

            for (String j: this.toString().split(",")) {

            }
        }
    }

    private void doSomethingElse() {

        do {

        } while(true);
    }

    private void doAnothingThing() {

        while(true) {}
    }
}
