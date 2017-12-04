var firebase    = require('firebase');
var path        = require("path");

var serviceAccount  = require('../config/service-account-key.json');
var dbConfig        = require('../config/database.json');

var utils       = require("./../utils/utils");

firebase.initializeApp(dbConfig);
var orgs = null;
var orgsTS = null;
var orgsNum;
var users = null;
var usersTS = null;
var usersNum;
var submissions = null;
var subsNum;
var subsTS = null;

var database    = firebase.database();
exports.firebase = firebase;
exports.service = [];

exports.initialize = initialize;

function initialize () {    
    database.ref('orgs').on('value', function(snapshot){
        if(snapshot.val() != undefined || snapshot.val() != null) {
            console.log("[" + utils.toUTC(new Date()) + "] Updated the orgs.");
            orgs = snapshot.val();
            orgsTS = utils.toUTC(new Date());
            orgsNum = snapshot.numChildren();
        } else {
            console.log("[" + utils.toUTC(new Date()) + "] Orgs Access Error. " + 
            orgsTS==undefined||orgsTS==null?"No data will be loaded.":"Accessing data last " + orgsTS);
        }
    });

    database.ref('users').on('value', function(snapshot){
        if(snapshot.val() != undefined || snapshot.val() != null) {
            console.log("[" + utils.toUTC(new Date()) + "] Updated the users.");
            users = snapshot.val();
            usersTS = utils.toUTC(new Date());
            usersNum = snapshot.numChildren();
        } else {
            console.log("[" + utils.toUTC(new Date()) + "] Users Access Error. " +
            usersTS==undefined||usersTS==null?"No data will be loaded.":"Accessing data last " + usersTS);
        }
    });

    database.ref('submissions').on('value', function(snapshot){
        if(snapshot.val() != undefined || snapshot.val() != null) {
            console.log("[" + utils.toUTC(new Date()) + "] Updated the submissions.");
            submissions = snapshot.val();
            subsTS = utils.toUTC(new Date());
            subsNum = snapshot.numChildren();
        } else {
            console.log("[" + utils.toUTC(new Date()) + "] Submissions Access Error. " +
            subsTS==undefined||subsTS==null?"No data will be loaded.":"Accessing data last " + subsTS);
        }
    });
}

exports.service.getUserWithOrganization = getUserWithOrganization;

function getUserWithOrganization (userid) {
    var user = users[userid];
    user.user_id = userid;
    user.org = getOrganization(user.org_id);
    return user;
}

exports.service.getAllUsersWithOrganizations = getAllUsersWithOrganizations;

function getAllUsersWithOrganizations () {
    var usersT = users;
    
    for (key in usersT)
        usersT[key] = getUserWithOrganization(key);
    
    return usersT;
}

exports.service.getAllOrganizationsWithUsers = getAllOrganizationsWithUsers;

function getAllOrganizationsWithUsers () {
    var orgsT = orgs;
    
    for (key in orgsT)
        orgsT[key] = getOrganizationsWithUsers(key);
    
    return orgsT;
}

exports.service.getAllUsers = getAllUsers;

function getAllUsers () {
    return users;
}

exports.service.getUser = getUser;

function getUser (userid) {
    var user = users[userid];
    return user;
}

exports.service.getSubmission = getSubmission;

function getSubmission (docuID) {
    var submission = submissions[docuID];
    return submission;
}

exports.service.getCompleteSubmission = getCompleteSubmission;

function getCompleteSubmission (docuID) {
    var submission = submissions[docuID];
    submission.submittedBy = getUserWithOrganization(submission.user_id_org); 
    submission.checker = getUser(submission.user_id_checker); 
    return submission;
}

exports.service.getAllCompleteSubmissions = getAllCompleteSubmissions;

function getAllCompleteSubmissions () {
    var completeSubmissions = {};
    for (key in submissions) {
        completeSubmissions[key] = getCompleteSubmission(key);
    }

    return completeSubmissions;
}

exports.service.getCompleteSubmissionsByOrg = getCompleteSubmissionsByOrg;

function getCompleteSubmissionsByOrg (orgid) {
    var completeSubmissions = {};
    for (key in submissions) {
        var sub = getCompleteSubmission(key);
        if(sub.submittedBy.org_id == orgid)
            completeSubmissions[key] = sub;
    }

    return completeSubmissions;
}

exports.service.getOrganizationsWithUsers = getOrganizationsWithUsers;

function getOrganizationsWithUsers(orgid) {
    var org = orgs[orgid];
    var count = 0;
    
    for (key in users) {
        if(users[key].org_id == orgid) {
            var user = users[key];
            count ++;
        }
    }
    
    org.user_count = count

    return org;
}

exports.service.getAllOrganizations = getAllOrganizations;

function getAllOrganizations() {
    return orgs;
}

exports.service.getOrganization = getOrganization;

function getOrganization (orgid) {
    var org = orgs[orgid];
    return org;
}

exports.service.loginUser = loginUser;

function loginUser (username, password) {
    var userTest = false, passTest = false;
    for (key in users) {
        if(users[key].username == username) {
            userTest = true;
            if(users[key].password == password) {
                return {test:true, user:getUserWithOrganization(key)};
            } return {test:true, user:null};
        }
    }

    return {test:false, user:null};
}

exports.service.countAcademic = countAcademic

function countAcademic (orgID) {
    var orgID = orgID || null, count = 0;
    
    for (key in submissions) {
        if(orgID == null || orgID == getUserWithOrganization(submissions[key].user_id_org).org_id) {
            if(submissions[key].act_nature.toUpperCase() == "ACADEMIC") {
                count++;
            }
        }
    }

    return count;
}
exports.service.countNonacademic = countNonacademic

function countNonacademic (orgID) {
    var orgID = orgID || null, count = 0;
    
    for (key in submissions) {
        if(orgID == null || orgID == getUserWithOrganization(submissions[key].user_id_org).org_id) {
            if(submissions[key].act_nature.toUpperCase() != "ACADEMIC") {
                count++;
            }
        }
    }

    return count;
}

exports.service.addOrganization = addOrganization;

function addOrganization(orgKey, orgDetails) {
    database.ref("orgs").child(orgKey).set({
        name: orgDetails.name,
        username: orgDetails.username,
        status: orgDetails.status,
        privilege: orgDetails.privilege
      });
}

exports.service.addUser = addUser;

function addUser (userDetails) {
    console.log("here");
    database.ref("users").child("user_"+(usersNum+1)).set({
        name: userDetails.name,
        username: userDetails.username,
        email: userDetails.email,
        contact: userDetails.contact,
        org_id: userDetails.org_id,
        password: userDetails.password
    });
}

exports.service.editUser = editUser;

function editUser (userID, userDetails) {
    database.ref("users").child(userID).update({
        name: userDetails.name,
        username: userDetails.username,
        email: userDetails.email,
        contact: userDetails.contact
    });
}

exports.service.deleteUser = deleteUser;

function deleteUser (userID) {
    console.log("here delete");
    database.ref("users").child(userID).remove();
}

exports.service.countOrgs = countOrgs;

function countOrgs() {
    return orgsNum;
}

exports.service.countSubs = countSubs;

function countSubs() {
    return subsNum;
}

exports.service.changeOrgStatus = changeOrgStatus;

function changeOrgStatus(orgID, status) {
    var newStatus;
    if(status == 'inactive')
        newStatus = 'active';
    else if (status == 'active')
        newStatus = 'inactive';
    database.ref("orgs").child(orgID).update({
        status: newStatus
    });
}

exports.service.addSubmission = addSubmission;

function addSubmission(subKey, subDetails) {
    database.ref("submissions").child(subKey).set({
        act_date: subDetails.act_date,
        act_nature: subDetails.act_nature,
        act_time: subDetails.act_time,
        act_type: subDetails.act_type,
        act_venue: subDetails.act_venue,
        datetimechecked: "-",
        status: "-",
        sub_type: subDetails.sub_type,
        term: subDetails.term,
        timestamp: subDetails.timestamp,
        title: subDetails.act_title,
        type_sas: subDetails.type_sas,
        user_id_checker: "-",
        user_id_org: subDetails.user_id_org
      });

}