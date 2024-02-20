.PHONY: backup

include .env.test

# Créer les containers mariadb et phpmyadmin
create:
	docker compose up -d

# Démarre les containers mariadb et phpmyadmin
start:
	docker compose up -d

# Arrête les containers mariadb et phpmyadmin
stop:
	docker compose down

# Redémarre les containers mariadb et phpmyadmin
restart: stop start

# Ouvre un terminal dans le container mariadb
shell:
	@docker exec -it mariadb_gestassoc /bin/bash

# Créer une backup de la bdd gestion_association
backup:
	@current_user=$$(git config user.name); \
	docker exec -it mariadb_gestassoc /bin/bash -c "mysqldump -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE) > /backup/backup_$${current_user}_$$(date +'%Y-%m-%d_%H-%M-%S').sql"

# Restaure une backup de la bdd gestion_association (fournir le nom du fichier de backup)
# Exemple: make restore f=backup_2021-08-25_14-00-00.sql
restore:
	@docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -pdev gestion_association" < backup/$(f)

# Restaure la dernière backup de la bdd gestion_association
restore_latest:
	@latest_backup=$$(ls -t backup/*.sql | head -n1); \
	echo "Restoring from backup $${latest_backup}"; \
	docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE)" < $${latest_backup}

# Supprime les containers mariadb et phpmyadmin
remove :
	docker compose down --remove-orphans
