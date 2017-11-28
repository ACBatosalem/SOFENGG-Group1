// USED MODULES
var path        = require("path");
var session     = require('express-session');

// PERSONAL MODULES
var fbModules    = require('./../models/firebase');
var model        = require('./../models/models');
var utils       = require('./../utils/utils');

// VARIABLES
var execute     = {};
var context     = "/APS_Dashboard";

var database    = fbModules.firebase.database();
var firebase    = fbModules.firebase;

// ACTION HANDLER MAP
execute[context+"/home"] = home;
execute[context+"/org"] = home_org;
execute[context+"/aps"] = home_aps;
execute[context+"/login"] = login;
execute[context+"/logout"] = logout;

// ACTION HANDLERS
function home (request, response) {  
    if(request.session.uid == null)
        response.render(path.join(__dirname, './../web/index.ejs'));
    else {
        database.ref('users').child(request.session.uid).once('value', function(snapshot){
            database.ref('orgs').child(snapshot.val().orgUID).once('value', function(snapshot){
                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
                    response.redirect(context+'/aps');
                } else {
                    response.redirect(context+'/org');
                }
                return true;
            });
            return true;
        });
    }
}

function login (request, response) {     
    if(request.session.uid == null) { 
        var username = request.body.username;  
        var password = request.body.password;

        if(username == "" || username == undefined || password == "" || password == undefined)
            response.redirect(context+'/home');
        else {         
            database.ref('users').once('value', function(snapshot) {
                console.log("[" + utils.toUTC(new Date()) + "] Server: Logging in " + username);
                var users =  snapshot.val();

                for(key in users) {
                    var user = users[key];
                    if (username == user.username) {
                        if(password == user.password) {
                            request.session.uid = key;
                            database.ref('orgs').child(user.orgUID).once('value', function(snapshot){
                                if(snapshot.val().privilege.toUpperCase() == 'ADMIN') {
                                    response.send("aps");
                                } else {
                                    response.send("org");
                                }
                                return true;
                            });
                            return true;
                        } else {
                            response.send("Password incorrect!");
                            return false;
                        }
                    }
                }
                response.send("Username does not exist!");
                return false;
            })
        }
    } else {
        var key = request.session.uid;
        database.ref('orgs').child(user.orgUID).once('value', function(snapshot){
            if(snapshot.val().privilege.toUpperCase() == 'ADMIN') {
                response.send("aps");
            } else {
                response.send("org");
            }
            return true;
        });
        return false;
    }
}

function logout (request, response) {
    request.session.uid = null;
    response.redirect(context+ '/home');
}

function home_aps (request, response) {
    if(request.session.uid == null)
        response.redirect(context + '/home');
    else {
        database.ref('users').child(request.session.uid).once('value', function(snapshot){
            database.ref('orgs').child(snapshot.val().orgUID).once('value', function(snapshot){
                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
                    response.render(path.join(__dirname, "./../web/submissions.ejs"), {submissions: ["Welcome APS", request.session.uid]});
                } else {
                    response.redirect(context+'/org');
                }
            });
        });
    }
}

function home_org (request, response) {
    if(request.session.uid == null)
        response.redirect(context + '/home');
    else {
        database.ref('users').child(request.session.uid).once('value', function(snapshot){
            database.ref('orgs').child(snapshot.val().orgUID).on('value', function(snapshot){
                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
                    response.redirect(context+'/aps');
                } else {
                    response.render(path.join(__dirname, "./../web/submissions.ejs"), {submissions: ["Welcome ORG", request.session.uid]});
                }
            });
        });
    }
}

exports.execute = execute;