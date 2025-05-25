package FMClient;


import org.testng.IExecutionListener;

public class WebDriverListener implements IExecutionListener {
    
    private LoginManager loginManager;

    @Override
    public void onExecutionStart() {
        System.out.println("Test Suite started.");
        setUp();  // Call setUp to initialize LoginManager
    }

    public void setUp() {
        String baseDir = System.getProperty("user.dir");
        
        // Get the singleton instance of LoginManager
        loginManager = LoginManager.getInstance(baseDir);
    }
    
    @Override
    public void onExecutionFinish() {
        System.out.println("Test Suite finished. Quitting WebDriver.");
        if (loginManager != null && loginManager.getDriver() != null) {
            loginManager.quitDriver();  // Call the method to quit the driver and reset the instance
        }
    }
}
