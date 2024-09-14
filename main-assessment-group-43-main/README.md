Furniture Shop Management and Design Application
Overview
This desktop application is designed for furniture shops, providing the ability to create stunning 2D/3D room designs, manage staff details, handle orders, and track inventory seamlessly. Built with a combination of powerful tools, this application aims to streamline the management and design process for furniture businesses.

Features
2D/3D Room Design
Easily create and visualize rooms in both 2D and 3D using an intuitive design interface.

Drag-and-drop functionality for placing furniture.
Real-time rendering of room layouts using OpenGL and LWJGL.
Staff Management
Add, update, and manage staff details such as contact information, roles, and working schedules.

Order Management
Keep track of customer orders, view order histories, and manage the order pipeline from pending to completed.

Inventory Tracking
Monitor inventory levels for furniture and other items, get low-stock alerts, and update stock when necessary.

Tools and Technologies
Java: The core programming language used to build the application.
Java Swing: Utilized for the GUI components, creating a user-friendly interface.
OpenGL: Provides advanced 2D and 3D rendering capabilities for room design.
LWJGL: Lightweight Java Game Library used alongside OpenGL for smooth 3D rendering.
Installation
Prerequisites
Ensure you have the following installed:

Java Development Kit (JDK 8 or above)
OpenGL libraries and LWJGL for rendering (instructions for installation provided below)
Clone the Repository

bash
Copy code
git clone https://github.com/your-repo/furniture-shop-app.git
Build the Project Navigate to the project directory and run the following command to compile the project:

bash
Copy code
javac -classpath .:path/to/lwjgl.jar Main.java
Run the Application Execute the main application file:

bash
Copy code
java -classpath .:path/to/lwjgl.jar Main
Usage
Designing a Room
Open the app and navigate to the "Room Design" tab. You can select 2D or 3D mode to start designing. Use drag-and-drop to add furniture pieces, customize room dimensions, and apply textures.

Managing Staff
Go to the "Staff" section where you can add new staff members, update existing details, or delete staff records.

Order Management
View orders in the "Orders" tab. You can check the status of orders, update their progress, and manage order details for each customer.

Inventory Tracking
The "Inventory" tab provides an overview of current stock levels. Use this section to add new inventory items, update quantities, and track item movements.
