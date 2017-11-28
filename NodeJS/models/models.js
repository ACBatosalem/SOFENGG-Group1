var fbModules    = require('./firebase');
var utils       = require('./../utils/utils');

var database    = fbModules.firebase.database();
var firebase    = fbModules.firebase;

var users = [ ];
var orgs = [ ];

exports.User = User;
exports.Organization = Organization;

function User (orgID, name, email, password, uid) {
    this.orgID = orgID;
    this.username = name;
    this.email = email;
    this.password = password;
    this.uid = uid;
}

function Organization (orgUID, orgName, priveleges) {
    this.orgID = orgUID;
    this.orgName = orgName;
    this.priveleges = priveleges;
}
