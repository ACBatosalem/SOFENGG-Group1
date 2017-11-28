var firebase    = require('firebase');
var path        = require("path");

var serviceAccount  = require('./config/service-account-key.json');
var dbConfig        = require('./config/database.json');

firebase.initializeApp(dbConfig);

var email = "jonal_ticug@dlsu.edu.ph";
var password = "password";
firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
    var errorCode = error.code;
    var errorMessage = error.message; 
    console.log(errorCode + ' ' + errorMessage);
});