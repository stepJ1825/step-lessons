package by.step;

public class GCRootExample {
    public static void main(String[] args) {
        GCRootExample gcRootExample;
        gcRootExample = new GCRootExample(); //счётчик = 1, ссылается на объект
        GCRootExample gcRootExample2 = gcRootExample; // счётчик = 2
        gcRootExample = null; //счётчик = 1
        gcRootExample2 = null; //счётчик = 0 - объект можно удалить

        GCRootExample gcRootExample3 = new GCRootExample(); // счётчик = 1
        GCRootExample gcRootExample4 = gcRootExample3; // счётчик = 2
        gcRootExample3 = gcRootExample4; // счётчик = 3

        while (true){}
    }
}
