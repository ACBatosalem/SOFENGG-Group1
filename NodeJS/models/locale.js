var firebase    = require('firebase');
var dbConfig        = require('../config/database.json');

firebase.initializeApp(dbConfig);

firebase.database().ref('orgs').child('HELLO').set({
    name: "ANGE MOVE ON NA",
    privilege: "PRIORITY",
    status: "active",
    username: "AMON"
})