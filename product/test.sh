#!/bin/sh

echo "Insert one Product:" && \
curl --request POST \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
    "name": "Cesta Basica",
    "description": "Cesta Basica BH",
    "images":[
        {"type":"jpg"}
    ]
}' && echo "" && \

echo "Insert another product that have a parent product:" && \
curl --request POST \
  --url http://localhost:8070/product \
  --header 'content-type: application/json' \
  --data '{
    "name": "Feijao",
    "description": "Feijao Carioca 5kg",
    "images":[
        {"type":"jpg"}
    ],
  "parentProduct":{
        "id": 1
    }   

}' && echo "" && \

echo "Get all products including specified relationships (child product and/or images)" && \
curl --request GET \
  --url 'http://localhost:8070/product?all=true' \
  --header 'content-type: application/json'
