It necessary to install localstack. You can use docker compose: https://docs.localstack.cloud/getting-started/installation/

At the root of the proyect there is a file called commands where it permits to create sns,sqs, and dynamodb table

The curl to create a sns message:
curl --request POST \
--url http://localhost:8080/payments/publishing \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/9.2.0' \
--data '{
"paymentId": "1718554274",
"paymentStatus": "pending"
}'

Basically, it simulates to create a message payment and then it is saved on a SQS.
When the status is gotten, it is saved on dynamodb to prevents to process the same 
payment status twice,