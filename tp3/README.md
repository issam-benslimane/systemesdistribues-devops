# TP3 - Sécurisation avec Keycloak

## Partie 1 : Configuration de Keycloak

1. Démarrer Keycloak :
```bash
docker-compose up -d
```

2. Accéder à la console d'administration :
- URL : http://localhost:8080
- Username : admin
- Password : admin123

3. Créer un nouveau Realm :
- Cliquer sur "Create Realm"
- Nom : "ebank-realm"

4. Créer un client :
- Menu "Clients" > "Create client"
- Client ID : ebank-client
- Client Protocol : openid-connect
- Access Type : confidential
- Valid Redirect URIs : http://localhost:3000/*
- Web Origins : http://localhost:3000

5. Créer des rôles :
- Menu "Roles" > "Create role"
- Créer les rôles : USER, ADMIN

6. Créer des utilisateurs :
- Menu "Users" > "Add user"
- Créer un utilisateur :
  - Username : user1
  - Email : user1@test.com
  - First Name : User
  - Last Name : One
  - Enabled : ON
- Dans l'onglet "Credentials" :
  - Set Password : user123
  - Temporary : OFF
- Dans l'onglet "Role Mappings" :
  - Assigner le rôle USER

7. Tester avec Postman :

a) Obtenir un token avec mot de passe :
```
POST http://localhost:8080/realms/ebank-realm/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

grant_type=password
client_id=ebank-client
client_secret=<votre_client_secret>
username=user1
password=user123
```

b) Rafraîchir le token :
```
POST http://localhost:8080/realms/ebank-realm/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

grant_type=refresh_token
client_id=ebank-client
client_secret=<votre_client_secret>
refresh_token=<votre_refresh_token>
```

c) Obtenir un token avec client credentials :
```
POST http://localhost:8080/realms/ebank-realm/protocol/openid-connect/token
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials
client_id=ebank-client
client_secret=<votre_client_secret>
```

8. Configuration des tokens :
- Dans "Realm Settings" > "Tokens" :
  - Access Token Lifespan : 5 minutes
  - Refresh Token Lifespan : 30 minutes 