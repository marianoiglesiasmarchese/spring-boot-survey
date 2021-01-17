# spring-boot-survey
Simple spring boot survey service

## links of interest
* [**Survey template generator**](https://docs.google.com/spreadsheets/d/1l1v_3iXLzMXF6hJg1Y8kD4F584059_5jh-gz6_-ETsw/edit?usp=sharing)
* [**Exporting google spreadsheet to JSON**](http://blog.pamelafox.org/2013/06/exporting-google-spreadsheet-as-json.html)
* [**Creating JSON from spreadsheet tutorial**](https://www.youtube.com/watch?v=uJDLT8nh2ps)

## workflow

1. obtain user
2. get assessment
3. send answers
4. update assessment

## API
```
POST /users/login

POST /assessments/{user_id}
GET /assessments/{id}
PUT /assessments
```