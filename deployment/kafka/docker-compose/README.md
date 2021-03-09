### Usage

```zsh
$ docker-compose up -d
$ nc -zvw10 127.0.0.1 29092
```

```zsh
$ docker ps

$ docker exec -it $ID bash

$ cd /bin

$ kafka-topics --create \
  --bootstrap-server localhost:9092 \
  --topic clivern

$ kafka-console-consumer \
    --bootstrap-server localhost:9092 \
    --topic clivern

$ kafka-console-producer \
    --bootstrap-server localhost:9092 \
    --topic clivern

$ kafka-topics --bootstrap-server localhost:9092 --list
```
