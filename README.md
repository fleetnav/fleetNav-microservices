
# fleetNav
date: 26/4/2024
created for

Hector Montaña
Juan Pablo Regino
Sebastián Moreno
Julián Roman
Camila
1. Introduction
1.1 Purpose
The purpose of this document is to define the requirements and scope of the project to develop a management tool for bus owners and drivers. The application will allow owners to manage their buses, drivers, and routes, and facilitate communication between owners and drivers through an integrated chat system.

1.2 Scope
The scope of the project includes developing a web application with a backend divided into three microservices and a frontend built using React and Next.js. Owners will be able to register buses, drivers, and routes, view financial and operational information through a dashboard, and communicate with drivers via an integrated chat system. Drivers will access information on earnings, available routes, and communicate with owners through the chat system.

1.3 Definitions, Acronyms, and Abbreviations
Backend: The part of the system responsible for data processing, storage, and business logic.
Frontend: The part of the system that interacts with the end-user.
Microservices: An architectural approach to software development where an application is divided into independent and autonomous services.
Dashboard: A user interface that provides a summarized view of relevant information.
API: Application Programming Interface, used to enable communication between different software components.
NoSQL: Non-relational database management system like MongoDB.
WebSocket: A bidirectional communication protocol between a web browser and a web server.
PostgreSQL: An open-source relational database management system.
JWT: JSON Web Token, a standard for securely transmitting information between parties as a JSON object.
CSS: Cascading Style Sheets, a graphic design language for defining the look and formatting of a document written in a markup language.
REST API: A type of API that follows the principles of Representational State Transfer architecture.
React: A JavaScript library for building user interfaces.
Next.js: A React framework that enables server-side rendering and generation of static websites for web applications.
TypeScript: A superset of JavaScript that adds optional static typing to the language.
Tailwind CSS: A low-level utility CSS framework that provides pre-defined classes for designing user interfaces.
Framer Motion: An animation library for React.
Mapbox: An online mapping platform.
1.4 References
Nest.js Documentation: https://nestjs.com/
Spring Framework Documentation: https://spring.io/
PostgreSQL Documentation: https://www.postgresql.org/docs/
MongoDB Documentation: https://docs.mongodb.com/
React Documentation: https://reactjs.org/docs/getting-started.html
Next.js Documentation: https://nextjs.org/docs
Mapbox Documentation: https://docs.mapbox.com/
Zustand Documentation: https://zustand.surge.sh/
Multitenancy Architecture Documentation: https://www.ibm.com/cloud/learn/multitenancy
2. General Description
2.1 Product Perspective
The management tool for bus owners and drivers will be a web application providing an intuitive and user-friendly interface for owners to manage their buses, drivers, and routes, and for drivers to access relevant information about earnings and available routes.

2.2 Product Functions
The main functions of the product will include:

User registration and authentication for owners and drivers.
Registration and management of buses, drivers, and routes by owners.
Display of financial and operational information through a dashboard for owners.
Communication between owners and drivers through an integrated chat system.
Display of financial and operational information for drivers, including earnings and available routes.
2.3 Users and Characteristics
Bus Owners
Bus owners will have access to the following features:

Registration and management of buses, drivers, and routes.
Display of detailed information through a dashboard.
Communication with drivers via the integrated chat system.
User profile configuration.
Drivers
Drivers will have access to the following features:

Display of earnings and available routes.
Communication with owners via the integrated chat system.
User profile configuration.
3. Specific Requirements
3.1 Functional Requirements
User Authentication:

Users should be able to register and log in to the application.
Authentication credentials provided by users must be validated.
Registration and Management of Buses, Drivers, and Routes:

Owners should be able to register and manage information about their buses, drivers, and routes via application forms.
Ensure accuracy and completeness of registered information.
Dashboard for Owners:

Provide a dashboard displaying detailed information on earnings, expenses, and other relevant metrics for owners.
The dashboard should be interactive, allowing owners to perform actions like viewing specific details and generating reports.
Communication between Owners and Drivers:

Implement a chat system that enables bidirectional communication between owners and drivers.
Owners and drivers should be able to view message history and send new messages.
3.2 Non-Functional Requirements
Security:

Implement a secure authentication and authorization system to protect users' confidential information.
Apply best security practices to prevent vulnerabilities such as SQL injection attacks and denial-of-service attacks.
Performance:

The application must be capable of handling a large volume of users and simultaneous transactions without significantly slow response times.
Optimize database queries and frontend performance to ensure a smooth user experience.
3.3 Interface Requirements
Intuitive User Interface:
The user interface should be intuitive and easy to use for both owners and drivers, with clear navigation and understandable visual elements.
Use responsive design elements to ensure a consistent experience across different devices and screen sizes.
3.4 Performance Requirements
Scalability:
The application architecture should be scalable to accommodate future growth in the number of users and data volume.
Implement measures to ensure that application performance does not significantly degrade under increased load.
3.5 Design Constraints
Browser Compatibility:
The application must be compatible with a wide range of modern web browsers, including Chrome, Firefox, Safari, and Edge.
Conduct comprehensive compatibility testing to ensure the application functions correctly on different browsers and versions.
