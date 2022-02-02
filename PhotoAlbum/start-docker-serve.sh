export ROOT=/mnt/c/Users/alexandru.sabou/Desktop/TPJAD/PhotoAlbum
echo $ROOT
docker-compose -f $ROOT/docker/docker-compose.serve.yml up --remove-orphans