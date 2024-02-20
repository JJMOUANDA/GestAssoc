create:
	@docker compose up -d --build
	@make import

import:
	@docker exec mongodb mongoimport --host mongodb --db gestassoc --collection commentaires --type json --file /init.json --jsonArray

start:
	@docker compose up -d

stop:
	@docker compose down

restart : stop start

shell:
	@docker exec -it mongodb /bin/bash