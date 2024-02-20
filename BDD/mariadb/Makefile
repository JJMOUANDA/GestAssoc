.PHONY: backup

include .env.test

create:
	docker compose up -d --build

start:
	docker compose up -d

stop:
	docker compose down

restart: stop start

shell:
	@docker exec -it mariadb_gestassoc /bin/bash

test :
	@latest_backup=$$(ls -t backup/*.sql | head -n1); \
	echo "Restoring from backup $${latest_backup}";

backup:
	@current_user=$$(git config user.name); \
	docker exec -it mariadb_gestassoc /bin/bash -c "mysqldump -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE) > /backup/backup_$${current_user}_$$(date +'%Y-%m-%d_%H-%M-%S').sql"

restore:
	@docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -pdev gestion_association" < backup/$(f)

restore_latest:
	@latest_backup=$$(ls -t backup/*.sql | head -n1); \
	echo "Restoring from backup $${latest_backup}"; \
	docker exec -i mariadb_gestassoc /bin/bash -c "mysql -u root -p$(MYSQL_ROOT_PASSWORD) $(MYSQL_DATABASE)" < $${latest_backup}

remove :
	docker compose down --remove-orphans
