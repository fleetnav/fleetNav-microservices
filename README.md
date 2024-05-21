
# FleetNav

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
<p align="center">
<img align="center" width="400px" src="https://res.cloudinary.com/dxzuncdy9/image/upload/v1716248060/x3unl8usuopidem6gusq.png">
</p>

FleetNav is an application designed to enable fleet owners to efficiently manage their vehicles and drivers. The application provides a view for fleet owners, where they can monitor the status of their vehicles, as well as a view for drivers, where they can see their personal and work information.

## Features

- **Fleet Management**: Owners can view and manage the status of all their vehicles.
- **Trip Information**: Track trips taken by each vehicle.
- **Driver Management**: Drivers can log in to their account to view their work information and assignments.
- **Vehicle Information**: Details on the operational status and maintenance of vehicles.
- **Chat**: Online chat between users

## Installation

Follow these steps to install and run the Tattion project in your local development environment:
### Prerequisites

- Java 17
- Docker

1. Clone the repository:
```bash
git clone https://github.com/fleetnav/fleetNav-microservices.git
```
2. Init Docker
```bash
docker-compose up --build
```

3. Run the project in development mode:

```bash
mvn clean install
```
## Open
**Local**
- Eureka-server: http://localhost:8761/
- config server: http://localhost:8085
- api gateway: http://localhost:8088
- multitenant-service: http://localhost:8089/api/v1
- chat service: http://localhost:8083/socket/v1

## Documentation
The documentation can be found in the [wiki](https://github.com/fleetnav/fleetNav-microservices/wiki)

## Deploy
- Eureka-server: https://fleetnav-eureka-server-0-0-1.onrender.com
- config server: https://fleetnav-config-server-0-0-2.onrender.com
- api gateway: https://fleetnav-api-gateway-0-0-2.onrender.com
- multitenant-service: https://fleetnav-multitenant-service-0-0-3.onrender.com
- chat-service: https://fleetnav-chat-service-0-0-3.onrender.com

## This project was developed with:

- ![Static Badge](https://img.shields.io/badge/Spring-%236DB33F?logo=spring&logoColor=white)
- ![Static Badge](https://img.shields.io/badge/Docker-%232496ED?logo=docker&logoColor=white)
 
- ![Static Badge](https://img.shields.io/badge/PostgreSQL-%234169E1?logo=postgresql&logoColor=white)

- ![Static Badge](https://img.shields.io/badge/Spring%20Security%20-%20%236DB33F?logo=springSecurity&logoColor=white)

- ![Static Badge](https://img.shields.io/badge/Spring%20Boot%20-%20%236DB33F?logo=springBoot&logoColor=white)
- ![Static Badge](https://img.shields.io/badge/Render-%2346E3B7?logo=render&logoColor=white)





# Contribution
We appreciate contributions to Tattion! If you would like to contribute to the project, follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix: ```git checkout -b feature/my-new-feature or git checkout -b bugfix/my-fix.```
3. Make your changes and commit the files: ```git commit -m 'Add my new feature'.```
4. Push the created branch: ```git push origin feature/my-new-feature.```
5. Open a Pull Request on GitHub.
6. Make sure to follow our contribution guidelines and respect the project's code of conduct.

# Contact
- *Juan Pablo Regino* - *juanregino@gmail.com*
- *Sebastian Moreno* - *Sebastianmorenoecheverri@gmail.com*
- *Hector Montaña* - *ralexale@gmail.com*

## Authors

*Juan Pablo Regino* <img align='center' src="https://media.giphy.com/media/12oufCB0MyZ1Go/giphy.gif" width="50"></img>   *Sebastian Moreno* <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExYnppb3EwNGU0NWY2bG1zYWJiNGtrMDdjejMzcWM3c2dzaWs2b2l2ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/xRJinOH44eOd2/giphy.gif" width="50" height= "35" align='center'>  *Hector Montaña* <img src="https://media.giphy.com/media/BHCFcibksBxAV0FDoL/giphy.gif" width="50" align='center' /> 
