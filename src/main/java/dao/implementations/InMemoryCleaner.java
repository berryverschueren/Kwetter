package dao.implementations;

/**
 * Created by Berry-PC on 07/03/2017.
 */
public class InMemoryCleaner {

    InMemoryCollectionObject im = InMemoryCollectionObject.getInstance();

    public void clearMemory() {
        im.clearMemory();
    }
}
