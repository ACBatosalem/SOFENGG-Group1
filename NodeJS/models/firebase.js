var firebase    = require('firebase');
var path        = require("path");

var serviceAccount  = require('../config/service-account-key.json');
var dbConfig        = require('../config/database.json');

firebase.initializeApp(dbConfig);

exports.firebase = firebase;