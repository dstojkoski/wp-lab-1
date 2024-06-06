# Running docker

* `docker compose up`

# Running Kubernetes

* `k3d cluster create wp-app-cl -p "8080:80@loadbalancer`
* `cd kubernetes`
* `kubectl apply -f namespace.yaml -f postgres-secret.yaml -f pvc.yaml -f db.yaml
   -f deployment.yaml -f service.yaml -f ingress.yaml`
