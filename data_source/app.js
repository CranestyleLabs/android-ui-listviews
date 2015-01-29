var express = require('express')
var app = express()

var json_data = {
	"valid":true,
	"data":[
		"Randy","Zach","Jen","Dan","Ryan","Chi","Karl","Larry","Moe","Curly"
	]
}

app.get('/', function (req, res) {
  res.send(JSON.stringify(json_data))
})

var server = app.listen(3000, function () {

  var host = server.address().address
  var port = server.address().port

  console.log('Example app listening at http://%s:%s', host, port)

})