
<p align="center">
  <h1 align="center">Nutrisq</h1>
  <p align="center">Web App to track nutrition intake and stay informed about your diet.</p>
</p>

---

> [!NOTE]
> **Nutrisq is your ultimate companion for tracking nutrition intake and maintaining a healthy diet. Stay on top of your dietary habits and make informed decisions with ease.**

## Table of Contents

---

* [API Documentation](#-api-documentation)
* [Data Models](#-data-models)


## 📖 API Documentation

---

## User API's

### Get All Users

- **Endpoint:** `GET /api/users`
- **Description:** Retrieves a list of all registered users with their details.
- **Response:** `200 OK` with a list of user objects.

### Edit User Information

- **Endpoint:** `PUT /user/{username}`
- **Description:** Updates the information of a specified user.
- **Request Body:** `User` object.
- **Response:** `200 OK` with the updated user object.

### Edit User Role

- **Endpoint:** `PUT /api/role/{username}`
- **Description:** Updates the role of a specified user. Only accessible by users with admin authority.
- **Request Body:** `User` object.
- **Response:** `200 OK` with the updated user object.
- **Authorization:** Requires `admin` authority.

### Edit User Specifics

- **Endpoint:** `PUT /api/userSpecifics/{username}`
- **Description:** Updates the specific details of a specified user.
- **Request Body:** `UserSpecifics` object.
- **Response:** `200 OK` with the updated `UserSpecifics` object.

### Delete User

- **Endpoint:** `DELETE /api/user/{username}`
- **Description:** Deletes a specified user.
- **Response:** `200 OK` with a confirmation message.

## Authentication API's


### Register New User

- **Endpoint:** `POST /auth/register`
- **Description:** Registers a new user.
- **Request Body:** `User` object.
- **Response:** `201 Created` with a confirmation message.

### Authenticate and Get Token

- **Endpoint:** `POST /auth/login`
- **Description:** Authenticates a user and provides a JWT token if successful.
- **Request Body:** `AuthRequest` object.
- **Response:** `200 OK` with a JWT token or an error message if authentication fails.

## 🗂️ Data Models

### User Object

The `User` object contains the following fields:

-  `username`: `String` - The unique username of the user.
-  `password`: `String` - The password for the user's account.
-  `email`: `String` - The email address of the user.
-  `role`: `String` - The role of the user ("user", "admin").
-  `created`: `Timestamp` - The date and time of user creation.

```json
{
  "username": "john_doe",
  "password": "password123",
  "email": "john.doe@example.com",
  "role": "user",
  "created": "2024-05-18 21:40:15"
}
```

### UserSpecifics Object

The `UserSpecifics` object contains the following fields:

- `firstname`: `String` - The first name of the user.
- `lastname`: `String` - The last name of the user.
- `born`: `Date` - The birthdate of the user.
- `weight`: `Double` - The weight of the user in kilograms.
- `height`: `Double` - The height of the user in centimeters.
- `sex`: `String` - The sex of the user ("male", "female", "other").

```json
{
  "firstname": "John",
  "lastname": "Doe",
  "born": "1990-01-01",
  "weight": 70.5,
  "height": 175.5,
  "sex": "male"
}
```

### Auth Object

The `User` object contains the following fields:

-  `username`: `String` - The unique username of the user.
-  `password`: `String` - The password for the user's account.

```json
{
  "username": "john_doe",
  "password": "password123"
}
```