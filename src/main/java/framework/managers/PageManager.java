package framework.managers;

import java.util.HashMap;
import java.util.Map;

public class PageManager {



    private static PageManager INSTANCE = null;

    private static Map<Class, Object> pageSet = new HashMap<>();

    private PageManager() {
    }

    public static PageManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

}
