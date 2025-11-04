# Microservice Banque - Spring Boot REST API

##  Description
Microservice Spring Boot complet pour la gestion des comptes bancaires. Offre une API RESTful avec support JSON/XML, documentation automatique Swagger et interface web interactive.

##  Fonctionnalités

 **API REST complète (CRUD + opérations métier)**

 **Support JSON & XML**

 **Documentation Swagger/OpenAPI automatique**

 **Base de données H2 en mémoire**

 **Validation des données**

 **Tests unitaires**

 **Logging détaillé**

 **Page d'accueil interactive**

##  Technologies
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistance des données
- **H2 Database** - Base de données en mémoire
- **Lombok** - Réduction du code boilerplate
- **Swagger/OpenAPI 3** - Documentation automatique
- **Jackson XML** - Support de sérialisation XML
- **Maven** - Gestion des dépendances
- **Java 17** - Langage de programmation

##  Structure du Projet
```
ms-banque/
├── src/main/java/ma/rest/spring/
│   ├── entities/
│   │   ├── Compte.java          # Entité compte bancaire
│   │   └── TypeCompte.java      # Enumération types de compte
│   ├── repositories/
│   │   └── CompteRepository.java # Interface Spring Data JPA
│   ├── controllers/
│   │   ├── CompteController.java # Contrôleur REST principal
│   │   └── HomeController.java   # Contrôleur page d'accueil
│   └── config/
│       └── SwaggerConfig.java    # Configuration Swagger
├── src/main/resources/
│   ├── application.properties    # Configuration Spring
│   ├── static/                  # CSS/JS pour l'interface
│   └── templates/               # Page HTML d'accueil
└── pom.xml                      # Dépendances Maven
```

##  Prérequis
- **Java 17** ou supérieur
- **Maven 3.6+** ou utilisation de Maven Wrapper
- **Navigateur web** moderne pour l'interface

## 🎯 Démonstration

### 1. Démarrage de l'application


https://github.com/user-attachments/assets/9e85afcc-8067-4561-be71-7e842d484492


 **🌐 URL de l'application:**  http://localhost:8082



https://github.com/user-attachments/assets/e0b5eeff-6d2b-499d-a761-ebece5c625f0

**📚 Swagger UI:** http://localhost:8082/swagger-ui.html

<img width="949" height="491" alt="im1" src="https://github.com/user-attachments/assets/3907bb42-73b9-4479-bebd-4155b4952c5a" />

**🗄️ Console H2:** http://localhost:8082/h2-console

<img width="959" height="478" alt="im2" src="https://github.com/user-attachments/assets/0fa94eb2-f4e1-43d5-a5d4-200e06a29d4c" />

**API Comptes (JSON/XML) :** http://localhost:8082/api/banque/comptes

<img width="959" height="404" alt="im3" src="https://github.com/user-attachments/assets/3c943bd0-e903-4142-b0c7-31e11bebc47c" />

**Documentation OpenAPI :**  http://localhost:8082/api-docs


<img width="959" height="458" alt="IM4" src="https://github.com/user-attachments/assets/30be9ef6-045f-4db1-92ca-1c1af30b9c4d" />


### 2. Accès aux interfaces
1. **Page d'accueil** : `http://localhost:8082/`
2. **Swagger UI** : `http://localhost:8082/swagger-ui.html`
3. **Console H2** : `http://localhost:8082/h2-console`

