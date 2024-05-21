<p align="center">
  <h1 align="center">Nutrisq</h1>
  <p align="center">Web App to track nutrition intake and stay informed about your diet.</p>
</p>

---

> [!NOTE]
> **Nutrisq is your ultimate companion for tracking nutrition intake and maintaining a healthy diet. Stay on top of your dietary habits and make informed decisions with ease.**

## Table of Contents

* ### [API Documentation](#-api-documentation)
  * [User APIs](#user-apis)
  * [Authentication APIs](#authentication-apis)
  * [Product APIs](#product-apis)

* ### [Data Models](#-data-models)
  * [User Object](#user-object)
  * [UserSpecifics Object](#userspecifics-object)
  * [Auth Object](#auth-object)
  * [ProductDetailsDto Object](#productdetailsdto-object)

## 📖 API Documentation

## User APIs

### Get All Users

- **Endpoint:** `GET /api/users`
- **Description:** Retrieves a list of all registered users with their details.
- **Response:** `200 OK` with a list of user objects.
- **Authorization:** Requires `admin` authority.

### Edit User Information

- **Endpoint:** `PUT /user/{username}`
- **Description:** Updates the information of a specified user.
- **Request Body:** `User` object.
- **Response:** `200 OK` with the updated user object.
- **Authorization:** Requires `user` authority.

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
- **Authorization:** Requires `user` authority.

### Delete User

- **Endpoint:** `DELETE /api/user/{username}`
- **Description:** Deletes a specified user.
- **Response:** `200 OK` with a confirmation message.
- **Authorization:** Requires `user` authority.

## Authentication APIs

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

## Product APIs

### Get Products by Name

- **Endpoint:** `GET /api/products/name/{name}`
- **Description:** Retrieves a list of products by name from the external  <b>[Open Food Facts](https://world.openfoodfacts.org/data)</b> API.
- **Response:** `200 OK` with a list of product details.

### Get Products by Barcode

- **Endpoint:** `GET /api/products/barcode/{name}`
- **Description:** Retrieves product by barcode from the external  <b>[Open Food Facts](https://world.openfoodfacts.org/data)</b> API.
- **Response:** `200 OK` with product details.

## 📦 Data Models

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

### ProductDetailsDto Object

The `ProductDetailsDto` object contains the following fields:

- `count`: `Integer` - The number of products found.
- `page`: `Integer` - The current page of results.
- `page_count`: `Integer` - The total number of pages.
- `page_size`: `Integer` - The number of products per page.
- `skip`: `Integer` - The number of products skipped.
- `products`: `List<Product>` - A list of product objects.

```json
{
  "count": 1,
  "page": 1,
  "page_count": 24,
  "page_size": 24,
  "skip": 0,
  "products": [
    {
      "code": "20012182",
      "brands": "Lidl, Belbake",
      "quantity": "400g",
      "nutriments": {
        "fat": 0,
        "proteins": 0,
        "carbohydrates": 0,
        "sugars": 0,
        "fiber": 0,
        "salt": 0,
        "energy-kcal": 0,
        "saturated-fat": 0
      },
      "nutriments_estimated": {
        "cholesterol_100g": 0,
        "vitamin-a_100g": 0,
        "vitamin-b1_100g": 0,
        "vitamin-pp_100g": 0,
        "pantothenic-acid_100g": 0,
        "vitamin-b6_100g": 0,
        "vitamin-b9_100g": 0,
        "vitamin-b12_100g": 0,
        "vitamin-c_100g": 0,
        "vitamin-d_100g": 0,
        "vitamin-e_100g": 0,
        "phylloquinone_100g": 0,
        "zinc_100g": 0,
        "phosphorus_100g": 0,
        "iodine_100g": 0,
        "magnesium_100g": 0,
        "copper_100g": 0,
        "potassium_100g": 0,
        "selenium_100g": 0,
        "sodium_100g": 0,
        "calcium_100g": 0,
        "iron_100g": 0
      },
      "product_name": "Flour",
      "image_url": "https://images.openfoodfacts.org/images/products/20012182/front_en.117.400.jpg"
    }
  ]
}
```