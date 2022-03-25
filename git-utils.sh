# CREATE PUB GIT WITH CONTENT IN FOLDER
# CREATING MICROSERVICES

# SETUP YOUR : ~/.config/hub.
# github.com:
# - user: xotosphere
#   oauth_token: {}
#   protocol: https

function serviceRepoCreate() {
	CURRENT=$(pwd)
	BASENAME=$(basename "$CURRENT")
	hub create xotomicro/${BASENAME}
	git init
	git remote add origin git@github.com:xotomicro/${BASENAME}.git
	git branch -m development
	git add . && git commit -m "🚀 blast off"
	echo "creating repo ${BASENAME}"
	hub create -d "${BASENAME}"
	git push origin development
}

$1

