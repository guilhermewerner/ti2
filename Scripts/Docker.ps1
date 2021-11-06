docker run --name PostgreSQL -e "POSTGRES_PASSWORD=1234" -p 5432:5432 -v ${home}/PostgreSQL:/var/lib/postgresql/data -d postgres
