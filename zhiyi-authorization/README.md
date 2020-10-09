```shell script
curl --location --request POST 'localhost:6628/oauth/token' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--form 'username=user' \
--form 'password=password' \
--form 'grant_type=password'
```
