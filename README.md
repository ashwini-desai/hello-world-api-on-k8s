# hello-world-api-on-k8s
Simple Kotlin Ktor based API service that can be deployed on Kubernetes.

## Tech stack:
Kotlin, Ktor, kotest
Gradle
Docker, Helm, Kubernetes cluster on Docker Desktop (for local), NodePort
Used Codeium Sonnet 3.5 integrated with IntelliJ’s AI assistant for helm chart code and tests auto generation. Also used for troubleshooting.


## Pre requisites:
1. JDK 23
2. Docker for Mac
3. [Enabling Kubernetes on docker for Mac](https://docs.docker.com/desktop/features/kubernetes/)
    

## Steps to run locally:
1. Start docker desktop (unless starts on machine boot by default)
2. Ensure default cluster with docker desktop is running fine - `kubectl get pods`
3. Set kubectl env to docker desktop - `kubectl config use-context docker-desktop`
4. Build jar - `./gradlew build`
5. Build docker image - `docker build -t ":1.0-SNAPSHOT" ":latest" .` or `./gradlew buildDockerImage`
6. Install helm chart in default cluster for this service - `helm install ./helm-chart -f ./helm-chart/values-docker-desktop.yaml`
7. Access service on localhost using NodePort’s port configured in values yaml - `http://localhost:30080/hello-world/v1`


## To do:
1. Fix build and push docker image steps in gradle
2. Sample eks values.yaml auto population
