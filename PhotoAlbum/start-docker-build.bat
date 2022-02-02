#create new project
#set ACTION=new 

#build photo-album projects
#set ACTION=build

#install dependency
#set ACTION=dep

#install all dependencies
set ACTION=all_dep

#action
#set ACTION=dep

#name of the component
#set COMPONENT=login

#name of dependency
#set DEPENDENCY=@angular/material

set ROOT=C:/Users/alexandru.sabou/Desktop/TPJAD/PhotoAlbum
docker-compose -f ./docker/docker-compose.build.yml up --remove-orphans