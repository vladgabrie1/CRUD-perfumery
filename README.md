# Welcome to My Little CRUD Website 🌐
###Name: Buinceanu Vlad-Gabriel

**Disclaimer:** I have chosen my approach to this project while respecting the requirements. The most significant differences are the use of cards instead of tables and incorporating photos for each entity. Additionally, I seized the opportunity to delve into front-end and UI design. An extra feature I added is the ability to upload a photo when creating an entity.

## Project Overview 🚀

In this project, I have focused on three primary entities: Perfume, Note, and Brand. The relationships among them are defined as follows:

- **Perfume <--> Note:** Many-to-Many (Multiple perfumes can have many notes).
- **Perfume --> Brand:** One-to-One (A perfume has a single brand).
- **Brand --> Perfume:** One-to-Many (A brand can have multiple perfumes). *Note: This relationship is not currently utilized.*

## Profiles 🛠️

### Development Profile (`dev`)

- Utilizes an H2 in-memory database.
- Implements the repository layer using JDBC template.
- Provides full functionality.

### Production Profile (`prod`)

- Utilizes a local PostgreSQL database.
- Implements the repository using JPA with Entity Manager.
- To use this profile, ensure you have a database named "perfumes," or modify the `spring.datasource.url` to match your database.
- Full functionality is available.

### Spring Data Profile (`springdata`)

- Utilizes a local PostgreSQL database.
- Implements the repository using JpaRepository (my favorite).
- To use this profile, make sure you have a database named "perfumes," or modify the `spring.datasource.url` to match your database.
- Full functionality is available.

