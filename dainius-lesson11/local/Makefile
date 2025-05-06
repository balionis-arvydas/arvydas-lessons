.PHONY: help up down restart up-monitoring down-monitoring

help:
	@grep -E '^[a-zA-Z0-9_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

up: ## Starts containers
	docker-compose --ansi never up -d

down: ## Kills containers started with "up"
	docker-compose --ansi never down

restart: ## Stops containers and starts them again
	docker-compose --ansi never restart

up-monitoring: ## Starts containers
	docker-compose -f docker-monitoring.yml --ansi never up -d

down-monitoring: ## Kills containers started with "up"
	docker-compose -f docker-monitoring.yml --ansi never down

restart: ## Stops containers and starts them again
	docker-compose --ansi never restart
