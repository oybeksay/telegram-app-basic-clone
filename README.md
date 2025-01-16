# WebSocket User Status Management API

This project demonstrates a real-time user status management system using WebSocket and Spring Boot. It enables tracking of user online/offline statuses in real-time and provides APIs for interaction.

---

## Features

- **User Management:** CRUD operations for user entities.
- **Message Management:** Create, retrieve, and delete messages.
- **WebSocket Integration:** Real-time updates on user status (online/offline).
- **Security:** Authentication and token-based security with JWT.
- **Redis Integration:** High-performance in-memory data storage for real-time updates.
- **Admin Features:** Suspend users, assign admin roles, and manage user accounts.

---

## Tech Stack

- **Java 17**
- **Spring Boot**
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-data-redis`
  - `spring-boot-starter-websocket`
  - `spring-boot-starter-security`
  - `spring-boot-starter-mail`
  - `spring-boot-starter-freemarker`
- **PostgreSQL** for database
- **Redis** for real-time data storage
- **JWT** for authentication
- **Swagger** for API documentation
- **MapStruct** for object mapping
- **Java Telegram Bot API** for Telegram bot integration

---

## Endpoints

### User Controller
- **PUT** `/users/update/{id}`: Update user information.
- **POST** `/users/register`: Register a new user.
- **POST** `/users/avatar/upload/{userId}`: Upload user avatar.
- **GET** `/users/{id}`: Retrieve user details.
- **DELETE** `/users/{id}`: Delete user by ID.
- **GET** `/users/search`: Search users by username or email.

### Message Controller
- **PUT** `/updateMessage/{messageId}`: Update a message.
- **GET** `/find/messages/{chatId}`: Retrieve messages for a chat.
- **DELETE** `/deleteMessage/{messageId}`: Delete a message.

### Token Controller
- **POST** `/sendActivationCode/{username}`: Send activation code to a user.
- **GET** `/refreshAccessToken`: Refresh JWT access token.
- **GET** `/login`: User login endpoint.
- **GET** `/activation/{token}`: Activate user account.

### Private Chat Controller
- **POST** `/chat/create`: Create a new chat.
- **GET** `/chat/find/{chatId}`: Retrieve chat details.
- **GET** `/chat/find/user/{id}`: Retrieve all chats for a user.
- **DELETE** `/chat/delete/{chatId}`: Delete a chat.

### Admin Controller
- **POST** `/admin/suspendUser/{userId}`: Suspend a user account.
- **POST** `/admin/makeAdmin/{userId}`: Assign admin role to a user.
- **DELETE** `/admin/deleteUser/{userId}`: Delete a user account.

---

## WebSocket Functionality

### User Online/Offline Status
- Real-time updates on user status.
- Integration with Redis for tracking status.
- STOMP endpoints for WebSocket communication.

---

## Prerequisites

1. **Java 17**
2. **PostgreSQL Database**
3. **Redis Server**
4. **Maven** (for building the project)

---

## Setup and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/oybeksay/telegram-app-basic-clone.git
   cd telegram-app-basic-clone
   ```

2. Update `application.properties` with your database and Redis configurations.

3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Access Swagger documentation:
   - URL: `http://localhost:8080/swagger-ui.html`

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

For additional support, contact the maintainer:
- **Oybek Saydaliyev**
- **Email:** saydaliyev.dev@gmail.com
- **Telegram:** [@saydaliyev_dev](https://t.me/saydaliyev_dev)
