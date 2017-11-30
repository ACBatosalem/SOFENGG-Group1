// USED MODULES
var path        = require("path");
var session     = require("express-session");

// PERSONAL MODULES
var fbModules    = require("./../models/firebase");
var utils       = require("./../utils/utils");

// VARIABLES
var execute     = { };
var context     = "/APS_Dashboard";

var database    = fbModules.firebase.database();
var firebase    = fbModules.firebase;

// ACTION HANDLER MAP
execute[context+"/home"] = home;
execute[context+"/org"] = home_org;
execute[context+"/aps"] = home_aps;
execute[context+"/login"] = login;
execute[context+"/logout"] = logout;

execute[context+"/modalData"] = modalData;

// ACTION HANDLERS
function home (request, response) {  
    if(request.session.uid == null)
        response.render(path.join(__dirname, "./../web/index.ejs"));
    else {
        database.ref("users").child(request.session.uid).once("value", function(snapshot){
            database.ref("orgs").child(snapshot.val().org_id).once("value", function(snapshot){
                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
                    response.redirect(context+"/aps");
                } else {
                    response.redirect(context+"/org");
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
            response.redirect(context+"/home");
        else {         
            database.ref("users").once("value", function(snapshot) {
                console.log("[" + utils.toUTC(new Date()) + "] Server: Logging in " + username);
                var users =  snapshot.val();

                for(key in users) {
                    var user = users[key];
                    if (username == user.username) {
                        if(password == user.password) {
                            request.session.uid = key;
                            database.ref("orgs").child(user.org_id).once("value", function(snapshot){
                                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
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
        database.ref("orgs").child(user.org_id).once("value", function(snapshot){
            if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
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
    response.redirect(context+ "/home");
}

function home_aps (request, response) {
    if(request.session.uid == null)
        response.redirect(context + "/home");
    else {
        database.ref("users").once("value", function(users){
            database.ref("orgs").once("value", function(orgs){
                if(orgs.val()[users.val()[request.session.uid].org_id].privilege.toUpperCase() == "ADMIN") {
                    database.ref("submissions").once("value", function(submissions){
                        response.render(path.join(__dirname, "./../web/submissions.ejs"), {
                            orgs: orgs.val(), 
                            database: database, 
                            context: context, 
                            users: users.val(),
                            user: users.val()[request.session.uid],
                            submissions: submissions.val()});
                        });
                } else {
                    response.redirect(context+"/org");
                }
            });
        });
    }
}

function home_org (request, response) {
    if(request.session.uid == null)
        response.redirect(context + "/home");
    else {
        database.ref("users").child(request.session.uid).once("value", function(snapshot){
            database.ref("orgs").child(snapshot.val().org_id).on("value", function(snapshot){
                if(snapshot.val().privilege.toUpperCase() == "ADMIN") {
                    response.redirect(context+"/aps");
                } else {
                    response.render(path.join(__dirname, "./../web/submissions.ejs"), {submissions: ["Welcome ORG", request.session.uid]});
                }
            });
        });
    }
}

function modalData(request, response) {
    var key = request.body.docuID;
    database.ref('submissions').child(key).once("value", function(docu){
        database.ref('users').once("value", function(users){
            database.ref('orgs').child(users.val()[docu.val().user_id_org].org_id).once("value", function(org){
                var custom = docu.val();
                custom.org_name = org.val().name;
                custom.name = users.val()[docu.val().user_id_org].name;
                custom.checker = users.val()[docu.val().user_id_checker].name;
                response.setHeader('Content-Type', 'application/json');
                response.send(custom);
            });
        });
    });
}

exports.execute = execute;