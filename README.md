# ProNa

The ProNa application is a software written in Java language for academic management and it is developed using the JavaFX library for the graphical user interface. The application is structured as a series of Java classes, each of which represents a different aspect of the application.

The main class is ProNaApp.java, which serves as the entry point of the application and sets up the graphical user interface. It creates and sets up the main window of the application and sets up a menu bar with menu items that link to other parts of the application.

DefKolegijaCTRL.java and DefKolegijaView.java are two classes that work together to provide the functionality for defining courses. DefKolegijaCTRL acts as a controller class, handling user interactions and updating the KolegijModel data model, while DefKolegijaView represents the graphical user interface for this functionality.

DefKritOcjeneCTRL.java and DefKritOcjeneView.java work similarly to DefKolegijaCTRL and DefKolegijaView, but for defining grades criteria.

KolegijVrstaNastaveCTRL.java, KolegijVrstaNastaveModel.java, and KolegijVrstaNastaveView.java provide the functionality for defining course types of education. KolegijVrstaNastaveCTRL acts as a controller, KolegijVrstaNastaveModel represents the data model, and KolegijVrstaNastaveView provides the graphical user interface.

VrstaNastaveModel.java represents the data model for education types.

In terms of improvements, it could be helpful to add more detailed error handling and input validation to ensure the consistency and accuracy of data entered into the application. Additionally, consider implementing additional features, such as the ability to view and edit data, which would improve the functionality of the application.
