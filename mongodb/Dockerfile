FROM mongo:7.0.5-jammy

COPY ./init.json /init.json

#CMD mongoimport --host mongodb --db gestassoc --collection commentaires --type json --file /init.json --jsonArray