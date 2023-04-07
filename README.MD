### Employee API
Employee API est une API REST pour gérer les employés d'une entreprise. 
L'API permet de créer, lire, mettre à jour et supprimer des employés dans une base de données Postgresql. 
L'API est développée en Java 11+ avec le framework Spring Boot.

#### prérquis
- Java 11 ou version supérieure.
- Base de données PostgreSQL.
- Maven pour la construction du projet.

### Préparation de la base de données avec docker (si postgresql n'est pas installé dans votre machine locale)
Avant de démarrer l'API, vous devez préparer une instance de base de données PostgreSQL. Vous pouvez utiliser Docker Compose pour lancer une instance PostgreSQL localement.

1. Clonez ce repository :
- $ git clone https://github.com/votre-nom/employee-api.git

2. Accédez au dossier du projet :
- $ cd employee-api

- $ docker compose up -d

3. Vérifiez que l'instance est en cours d'exécution en exécutant la commande suivante :
- $ docker ps

### Utilisation de l'API

4. Notez que <adresse_IP> doit être remplacé par l'adresse IP du serveur de base de données PostgreSQL dans le fichier de configuration (src/main/resources/application.properties):
- `spring.datasource.url=jdbc:postgresql://<adresse_IP>:5432/employeedb`

5. Compilez le projet avec la commande suivante :
- $ mvn clean package

6. Démarrez l'API avec la commande suivante :
- $ mvn spring-boot:run
ou 
- $ java -jar target/employee-api-0.0.1-SNAPSHOT.jar

7. L'API est accessible à l'adresse http://localhost:8080
