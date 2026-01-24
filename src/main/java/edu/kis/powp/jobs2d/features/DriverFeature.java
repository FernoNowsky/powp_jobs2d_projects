package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.visitor.VisitableJob2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.CanvasBoundaryDriverDecorator;

public class DriverFeature {

    private static DriverManager driverManager = new DriverManager();
    private static Application app;

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     * 
     * @param application Application context.
     */
    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(DriverFeature.class, "Drivers");
    }

    /**
     * Add driver to context, create button in driver menu.
     * 
     * @param name   Button name.
     * @param driver VisitableJob2dDriver object.
     */
    public static void addDriver(String name, VisitableJob2dDriver driver) {
        // CanvasBoundaryDriverDecorator adds canvas boundary validation to the selected driver from the list.
        CanvasBoundaryDriverDecorator wrappedDriver = new CanvasBoundaryDriverDecorator(driver);
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(wrappedDriver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    /**
     * Update driver info.
     */
    public static void updateDriverInfo() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }

}
