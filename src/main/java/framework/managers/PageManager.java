package framework.managers;

import framework.pages.FindBlock;
import framework.pages.MortgageForSecondaryPage;

import java.util.HashMap;
import java.util.Map;

public class PageManager {

    private MortgageForSecondaryPage mortgageForSecondaryPage;
    private FindBlock findBlock;
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

    public FindBlock getFindBlock() {
        if(findBlock == null) {
            findBlock = new FindBlock();
        }
        return findBlock;
    }

    public MortgageForSecondaryPage getMortgageForSecondaryPage() {
        if(mortgageForSecondaryPage == null) {
            mortgageForSecondaryPage = new MortgageForSecondaryPage();
        }
        return mortgageForSecondaryPage;
    }


}
