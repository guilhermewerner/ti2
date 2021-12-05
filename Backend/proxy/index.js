require('dotenv').config();

const axios = require("axios");
const express = require('express')();

express.get('/featured', function (request, response) {
    axios.get(`https://newsapi.org/v2/everything?q=animais+animal&sortBy=relevancy&pageSize=3&apiKey=${process.env.NEWS_API_KEY}`)
        .then(function (result) {
            response.send(result.data);
        })
        .catch(function (error) {
            response.send(error);
        });
});

express.get('/listing', function (request, response) {
    axios.get(`https://newsapi.org/v2/everything?q=animais+animal&sortBy=relevancy&pageSize=15&apiKey=${process.env.NEWS_API_KEY}`)
        .then(function (result) {
            response.send(result.data);
        })
        .catch(function (error) {
            response.send(error);
        });
});

express.listen(3000);
