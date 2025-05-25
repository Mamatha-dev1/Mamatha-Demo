package FMClient;

import FMClient.Actionscapture;
import Mamatha.LoginManager;
import org.testng.IClassListener;
import org.testng.ITestClass;

public class ActionCaptureListener implements IClassListener {

    @Override
    public void onAfterClass(ITestClass testClass) {
        try {
            Actionscapture actionscapture = new Actionscapture();
            actionscapture.testglobalexception(); // call your method after every class
        } catch (Exception e) {
            System.err.println("Error executing Actionscapture after class: " + e.getMessage());
        }
    }
}