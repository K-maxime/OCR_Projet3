# ChaTOP API

API REST développée avec Spring Boot pour la gestion de locations immobilières.

## Prérequis

- Java 21
- Maven
- MySQL 8

## Installation

### 1. Cloner le projet
```bash
git clone https://github.com/K-maxime/OCR_Projet3.git
```

### 2. Créer la base de données MySQL
```sql
CREATE DATABASE chatop_db;
CREATE USER 'chatop_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON chatop_db.* TO 'chatop_user'@'localhost';
```

### 3. Configurer les variables d'environnement
Copier le fichier `.env.example` et le renommer en `.env` :
```bash
cp .env.example .env
```

Remplir les valeurs dans `.env` :
```properties
DB_URL=jdbc:mysql://localhost:3306/chatop_db
DB_USERNAME=ton_utilisateur
DB_PASSWORD=ton_mot_de_passe
SERVER_PORT=3001
JWT_SECRET=ta_clé_secrète
```

## Lancer le projet
```bash
mvn spring-boot:run
```

## Documentation API

Swagger disponible à l'adresse :
```
http://localhost:3001/swagger-ui/index.html
```