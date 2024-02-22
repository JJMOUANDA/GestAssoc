.PHONY: backup_mariadb
.PHONY: backup_mongodb

include .env.test

# Créer les services mariadb, mongodb et phpmyadmin
create_all: create_mariadb create_mongodb create_phpmyadmin

# Démarrer les services mariadb, mongodb et phpmyadmin
start_all: start_mariadb start_mongodb start_phpmyadmin

# Arrêter les services mariadb, mongodb et phpmyadmin
stop_all: stop_mariadb stop_mongodb stop_phpmyadmin

# Redémarrer les services mariadb, mongodb et phpmyadmin
restart_all: restart_mariadb restart_mongodb restart_phpmyadmin

# Supprimer les services mariadb, mongodb et phpmyadmin
remove_all: remove_mariadb remove_mongodb remove_phpmyadmin

################# MARIADB #################

# Créer le service mariadb
create_mariadb:
	docker compose -p gestion_association up -d mariadb

# Démarrer le service mariadb
start_mariadb:
	docker compose start mariadb

# Arrêter le service mariadb
stop_mariadb:
	docker compose stop mariadb

# Redémarrer le service mariadb
restart_mariadb: stop_mariadb start_mariadb

# Supprimer le service mariadb
remove_mariadb:
	docker compose down mariadb --volumes --remove-orphans

# Se connecter au shell du service mariadb
shell_mariadb:
	docker exec -it mariadb_gestassoc /bin/bash

# Créer une backup de la base de données mariadb
backup_mariadb:
	@current_user=$$(git config user.name); \
	docker exec -it mariadb_gestassoc /bin/bash -c "mysqldump -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE) > /backup/backup_$${current_user}_$$(date +'%Y-%m-%d_%H-%M-%S').sql"

# Restaurer une backup de la base de données mariadb (fournir le chemin du fichier)
# exemple : make restore_mariadb f=mariadb/backup/backup_user_2021-08-25_15-00-00.sql
restore_mariadb:
	@docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -pdev gestion_association" < $(f)

# Restaurer la dernière backup de la base de données mariadb
restore_latest_mariadb:
	@latest_backup=$$(ls -t mariadb/backup/*.sql | head -n1); \
	echo "Restoring from backup $${latest_backup}"; \
	make restore_mariadb f=$${latest_backup}

################# MONGODB #################

# Créer le service mongodb
create_mongodb:
	docker compose -p gestion_association up -d mongodb

# Démarrer le service mongodb
start_mongodb:
	docker compose start mongodb

# Arrêter le service mongodb
stop_mongodb:
	docker compose stop mongodb

# Redémarrer le service mongodb
restart_mongodb: stop_mongodb start_mongodb

# Supprimer le service mongodb
remove_mongodb:
	docker compose down mongodb --volumes --remove-orphans

# Se connecter au shell du service mongodb
shell_mongodb:
	docker exec -it mongodb_gestassoc /bin/bash

# Créer une backup de la collection mongodb
backup_mongodb:
	@current_user=$$(git config user.name); \
	docker exec mongodb_gestassoc mongoexport --host localhost --port 27017 --db $(MONGO_DATABASE) --collection $(MONGO_COLLECTION) --out /backup/backup_$${current_user}_$$(date +'%Y-%m-%d_%H-%M-%S').json --jsonArray

# Restaurer une backup de la collection mongodb (fournir le chemin du fichier)
# exemple : make restore_mongodb f=mongodb/backup/backup_user_2021-08-25_15-00-00.json
restore_mongodb:
	@docker exec mongodb_gestassoc mongosh --quiet --host localhost --port 27017 --eval "db.getSiblingDB('$(MONGO_DATABASE)').dropDatabase()"
	@docker exec mongodb_gestassoc mongoimport --host localhost --port 27017 --db $(MONGO_DATABASE) --collection $(MONGO_COLLECTION) --type json --file $(f) --jsonArray

# Restaurer la dernière backup de la collection mongodb
restore_latest_mongodb:
	@latest_backup=$$(ls -t mongodb/backup/*.json | head -n1); \
	latest_backup="backup/$$(basename $$latest_backup)"; \
	echo "Restoring from backup $${latest_backup}"; \
	make restore_mongodb f=$${latest_backup}

################# PHPMYADMIN #################

# Créer le service phpmyadmin
create_phpmyadmin:
	docker compose -p gestion_association up -d phpmyadmin

# Démarrer le service phpmyadmin
start_phpmyadmin:
	docker compose start phpmyadmin

# Arrêter le service phpmyadmin
stop_phpmyadmin:
	docker compose stop phpmyadmin

# Redémarrer le service phpmyadmin
restart_phpmyadmin: stop_phpmyadmin start_phpmyadmin

# Supprimer le service phpmyadmin
remove_phpmyadmin:
	docker compose down phpmyadmin --volumes --remove-orphans
