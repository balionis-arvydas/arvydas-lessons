.PHONY: help up down restart

COMPONENTS ?= "monitoring service"
COMPONENT_LIST = $(shell echo $(COMPONENTS) | tr " " ",")

help:
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

up: ## Starts containers
	COMPOSE_PROFILES=$(COMPONENT_LIST)  docker-compose --ansi never up -d

down: ## Kills containers started with "up"
	COMPOSE_PROFILES=$(COMPONENT_LIST)  docker-compose --ansi never down

restart: ## Stops containers and starts them again
	COMPOSE_PROFILES=$(COMPONENT_LIST)  docker-compose --ansi never restart
