.PHONY: backup

include .env.test


create_all: create_mariadb create_mongodb create_phpmyadmin
start_all: start_mariadb start_mongodb start_phpmyadmin
stop_all: stop_mariadb stop_mongodb stop_phpmyadmin
restart_all: restart_mariadb restart_mongodb restart_phpmyadmin
remove_all: remove_mariadb remove_mongodb remove_phpmyadmin

################# MARIADB #################

create_mariadb:
	docker compose up -d mariadb

start_mariadb:
	docker compose start mariadb

stop_mariadb:
	docker compose stop mariadb

restart_mariadb: stop_mariadb start_mariadb

remove_mariadb:
	docker compose down mariadb

shell_mariadb:
	docker exec -it mariadb_gestassoc /bin/bash

backup_mariadb:
	@current_user=$$(git config user.name); \
	docker exec -it mariadb_gestassoc /bin/bash -c "mysqldump -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE) > /backup/backup_$${current_user}_$$(date +'%Y-%m-%d_%H-%M-%S').sql"

restore_mariadb:
	@docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -pdev gestion_association" < $(f)

restore_latest_mariadb:
	@latest_backup=$$(ls -t mariadb/backup/*.sql | head -n1); \
	echo "Restoring from backup $${latest_backup}"; \
	make restore_mariadb f=$${latest_backup}

################# MONGODB #################

create_mongodb:
	docker compose up -d mongodb
	docker exec mongodb_gestassoc mongoimport --host mongodb --db $(MONGO_DATABASE) --collection $(MONGO_COLLECTION) --type json --file /init.json --jsonArray

start_mongodb:
	docker compose start mongodb

stop_mongodb:
	docker compose stop mongodb

#restore_mongodb:
	#@docker exec mongodb_gestassoc mongosh --quiet -host mongodb -u $(MONGO_INITDB_ROOT_USERNAME) -p $(MONGO_INITDB_ROOT_PASSWORD) --eval "db.$(MONGO_COLLECTION).drop()"
	#@docker exec mongodb_gestassoc mongoimport -u $(MONGO_INITDB_ROOT_USERNAME) -p $(MONGO_INITDB_ROOT_PASSWORD) --host mongodb --db $(MONGO_DATABASE) --collection $(MONGO_COLLECTION) --type json --file /init.json --jsonArray

remove_mongodb:
	docker compose down mongodb

restart_mongodb: stop_mongodb start_mongodb

shell_mongodb:
	docker exec -it mongodb_gestassoc /bin/bash

################# PHPMYADMIN #################

create_phpmyadmin:
	docker compose up -d phpmyadmin

start_phpmyadmin:
	docker compose start phpmyadmin

stop_phpmyadmin:
	docker compose stop phpmyadmin

restart_phpmyadmin: stop_phpmyadmin start_phpmyadmin

remove_phpmyadmin:
	docker compose down phpmyadmin

